package com.ceiba.estacionamiento.service.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ceiba.estacionamiento.validation.EstacionamientoValidation;

@Service
public class EstacionamientoServiceImpl implements EstacionamientoService{
	
	private ServiciosRepository serviciosRepository;
	private ConfiguracionesIngresoRepository configuracionesIngresoRepository;
	private PreciosRepository preciosRepository;
	private ConfiguracionesCilindrajeRepository configuracionesCilindrajeRepository;
	private TRMService trmService;
	private EstacionamientoValidation estacionamientoValidation;
	
	@Value("${maximo.motos}")
	private Integer maximoMotos;
	
	@Value("${maximo.carros}")
	private Integer maximoCarros;
	
	@Autowired	
	public EstacionamientoServiceImpl(ServiciosRepository serviciosRepository, ConfiguracionesIngresoRepository configuracionesIngresoRepository, 
			TRMService trmWebService, PreciosRepository preciosRepository, EstacionamientoValidation estacionamientoValidation, 
			ConfiguracionesCilindrajeRepository configuracionesCilindrajeRepository, TRMService trmService) {
		this.serviciosRepository = serviciosRepository;
		this.configuracionesIngresoRepository = configuracionesIngresoRepository;
		this.preciosRepository = preciosRepository;
		this.configuracionesCilindrajeRepository= configuracionesCilindrajeRepository;
		this.estacionamientoValidation = estacionamientoValidation;
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
		if(!EstacionamientoUtils.validarPlacaValida(registrarVehiculo.getPlaca(), tipoVehiculo)){
			throw new EstacionamientoException("La placa ingresada no cuenta con el formato valido.");
		}		
		if(serviciosRepository.countByPlacaActivos(registrarVehiculo.getPlaca())>0){
			throw new EstacionamientoException("Ya se encuentra un veh\u00EDculo con esa placa en el estacionamiento.");
		}		
		Long vehiculosIngresados= serviciosRepository.countByVehiculoIngresadoActivos(tipoVehiculo);
		if((tipoVehiculo.equals(TipoVehiculo.CARRO) && vehiculosIngresados.intValue()==maximoCarros) || 
				(tipoVehiculo.equals(TipoVehiculo.MOTO) && vehiculosIngresados.intValue()==maximoMotos)){
			throw new EstacionamientoException("No hay cupo para el veh\u00EDculo.");
		}
		if(!estacionamientoValidation.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), configuracionesIngresoRepository.findByTipoVehiculo(tipoVehiculo))){
			throw new EstacionamientoException("El veh\u00EDculo no esta autorizado para ingresar el dia de hoy.");
		}		
		
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
		
		//Realizacion del cobro normal
		Long cantidadHoras= EstacionamientoUtils.calcularHorasEntreFechas(optionalServicios.get().getFechaHoraIngreso(), fechaActual);
		List<Precios> preciosList= preciosRepository.findByTipoVehiculo(optionalServicios.get().getTipoVehiculo());
		BigDecimal cobroTotal= new BigDecimal(0);
		for(Precios precio: preciosList){
			if(precio.getTipoCobro()==TipoCobro.DIA && cantidadHoras > 24){
				cobroTotal= cobroTotal.add(EstacionamientoUtils.cobroDia(precio.getPrecio(), cantidadHoras));
			}else if(precio.getTipoCobro()==TipoCobro.HORA){
				cobroTotal= cobroTotal.add(EstacionamientoUtils.cobroHora(precio.getPrecio(), cantidadHoras));
			}
		}
		
		//Realizacion del cobro adicional
		if(optionalServicios.get().getCilindraje()>0){
			Integer mayorCilindraje=0;
			BigDecimal cobroAdicional= new BigDecimal(0);
			List<ConfiguracionesCilindraje> configuracionesCilindrajeList= configuracionesCilindrajeRepository.findByTipoVehiculo(optionalServicios.get().getTipoVehiculo());
			for(ConfiguracionesCilindraje configuracionesCilindraje: configuracionesCilindrajeList){
				if(optionalServicios.get().getCilindraje()>configuracionesCilindraje.getCilindraje() && 
						configuracionesCilindraje.getCilindraje()>mayorCilindraje){
					mayorCilindraje= configuracionesCilindraje.getCilindraje();
					cobroAdicional= configuracionesCilindraje.getCobroAdicional();
				}
			}
			
			cobroTotal= cobroTotal.add(cobroAdicional);
		}			
		
		Servicios servicios= optionalServicios.get();
		
		servicios.setCobrado(cobroTotal);
		servicios.setFechaHoraSalida(new Date());
		servicios.setCobradoUSD(EstacionamientoUtils.cobroTRM(cobroTotal, trmService.getTrm()));
		
		return serviciosRepository.save(servicios);
	}	
}
