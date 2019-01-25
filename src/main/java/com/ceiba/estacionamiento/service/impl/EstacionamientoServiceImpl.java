package com.ceiba.estacionamiento.service.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.estacionamiento.domain.ConfiguracionesCilindraje;
import com.ceiba.estacionamiento.domain.Precios;
import com.ceiba.estacionamiento.domain.Servicios;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.enums.TipoCobro;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.repository.ConfiguracionesCilindrajeRepository;
import com.ceiba.estacionamiento.repository.ConfiguracionesIngresoRepository;
import com.ceiba.estacionamiento.repository.PreciosRepository;
import com.ceiba.estacionamiento.repository.ServiciosRepository;
import com.ceiba.estacionamiento.service.EstacionamientoService;
import com.ceiba.estacionamiento.trm.TRMService;
import com.ceiba.estacionamiento.util.EstacionamientoUtils;
import com.ceiba.estacionamiento.validation.Vigilante;

@Service
public class EstacionamientoServiceImpl implements EstacionamientoService{
	
	private ServiciosRepository serviciosRepository;
	private ConfiguracionesIngresoRepository configuracionesIngresoRepository;
	private PreciosRepository preciosRepository;
	private ConfiguracionesCilindrajeRepository configuracionesCilindrajeRepository;
	private TRMService trmService;
	private Vigilante vigilante;
			
	@Autowired	
	public EstacionamientoServiceImpl(ServiciosRepository serviciosRepository, ConfiguracionesIngresoRepository configuracionesIngresoRepository, 
			TRMService trmWebService, PreciosRepository preciosRepository, Vigilante vigilante, 
			ConfiguracionesCilindrajeRepository configuracionesCilindrajeRepository, TRMService trmService) {
		this.serviciosRepository = serviciosRepository;
		this.configuracionesIngresoRepository = configuracionesIngresoRepository;
		this.preciosRepository = preciosRepository;
		this.configuracionesCilindrajeRepository= configuracionesCilindrajeRepository;
		this.vigilante = vigilante;
		this.trmService= trmService;
	}
	
	@Transactional(readOnly = true)
	public List<Servicios> getServiciosActivos(String placa) {
		if(null == placa || placa.isEmpty()){
			return serviciosRepository.findByServiciosActivos();
		}else{
			return serviciosRepository.findByServiciosActivosPlaca(placa);
		}
	}

	@Transactional
	public void registarVehiculo(RegistrarVehiculoDTO registrarVehiculo) throws EstacionamientoException {
		TipoVehiculo tipoVehiculo= registrarVehiculo.getCilindraje()==0 ? TipoVehiculo.CARRO: TipoVehiculo.MOTO;
		vigilante.validarRegistroVehiculo(registrarVehiculo, tipoVehiculo, 
				serviciosRepository.countByPlacaActivos(registrarVehiculo.getPlaca()), 
				serviciosRepository.countByVehiculoIngresadoActivos(tipoVehiculo));						
		vigilante.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), 
				configuracionesIngresoRepository.findByTipoVehiculo(tipoVehiculo));		
		
		Servicios servicios= new Servicios();
				
		servicios.setPlaca(registrarVehiculo.getPlaca());
		servicios.setCilindraje(registrarVehiculo.getCilindraje());
		servicios.setFechaHoraIngreso(new Date());
		servicios.setTipoVehiculo(tipoVehiculo);		
		
		serviciosRepository.save(servicios);
	}
	
	@Transactional
	public Servicios salidaVehiculo(Long idServicio, Date fechaActual) throws EstacionamientoException, RemoteException{		
		Optional<Servicios> optionalServicios= serviciosRepository.findById(idServicio);
		if(!optionalServicios.isPresent()){
			throw new EstacionamientoException("El servicio no existe.");
		}		
		
		BigDecimal cobroTotal= calcularCobroVehiculo(preciosRepository.findByTipoVehiculo(optionalServicios.get().getTipoVehiculo()), 
				EstacionamientoUtils.calcularHorasEntreFechas(optionalServicios.get().getFechaHoraIngreso(), fechaActual));				
		if(optionalServicios.get().getCilindraje()>0){		
			cobroTotal= cobroTotal.add(calcularCobroAdicionalVehiculo(configuracionesCilindrajeRepository.findByTipoVehiculo(optionalServicios.get().getTipoVehiculo()), 
					optionalServicios.get().getCilindraje()));
		}			
		
		Servicios servicios= optionalServicios.get();
		
		servicios.setCobrado(cobroTotal);
		servicios.setFechaHoraSalida(new Date());
		servicios.setCobradoUSD(EstacionamientoUtils.cobroTRM(cobroTotal, trmService.getTrm()));
		
		return serviciosRepository.save(servicios);
	}
	
	private BigDecimal calcularCobroVehiculo(List<Precios> preciosList, Long cantidadHoras){
		BigDecimal cobro= new BigDecimal(0);
		for(Precios precio: preciosList){
			if(precio.getTipoCobro()==TipoCobro.DIA && cantidadHoras > 24){
				cobro= cobro.add(EstacionamientoUtils.cobroDia(precio.getPrecio(), cantidadHoras));
			}else if(precio.getTipoCobro()==TipoCobro.HORA){
				cobro= cobro.add(EstacionamientoUtils.cobroHora(precio.getPrecio(), cantidadHoras));
			}
		}
		
		return cobro;
	}
	
	private BigDecimal calcularCobroAdicionalVehiculo(List<ConfiguracionesCilindraje> configuracionesCilindrajeList, Integer cilindraje){
		Integer mayorCilindraje=0;
		BigDecimal cobroAdicional= new BigDecimal(0);		
		for(ConfiguracionesCilindraje configuracionesCilindraje: configuracionesCilindrajeList){
			if(cilindraje > configuracionesCilindraje.getCilindraje() && 
					configuracionesCilindraje.getCilindraje()>mayorCilindraje){
				mayorCilindraje= configuracionesCilindraje.getCilindraje();
				cobroAdicional= configuracionesCilindraje.getCobroAdicional();
			}
		}
		
		return cobroAdicional;
	}
}
