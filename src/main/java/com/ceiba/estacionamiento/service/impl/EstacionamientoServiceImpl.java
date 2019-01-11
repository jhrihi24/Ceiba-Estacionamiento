package com.ceiba.estacionamiento.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ceiba.estacionamiento.domain.Servicios;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.repository.ConfiguracionesIngresoRepository;
import com.ceiba.estacionamiento.repository.ServiciosRepository;
import com.ceiba.estacionamiento.service.EstacionamientoService;
import com.ceiba.estacionamiento.validation.EstacionamientoValidation;

@Service
public class EstacionamientoServiceImpl implements EstacionamientoService{
	
	private ServiciosRepository serviciosRepository;
	private ConfiguracionesIngresoRepository configuracionesIngresoRepository;
	private EstacionamientoValidation estacionamientoValidation;
	
	@Value("${maximo.motos}")
	private Integer maximoMotos;
	
	@Value("${maximo.carros}")
	private Integer maximoCarros;
	
	@Autowired	
	public EstacionamientoServiceImpl(ServiciosRepository serviciosRepository, ConfiguracionesIngresoRepository configuracionesIngresoRepository, 
			EstacionamientoValidation estacionamientoValidation) {
		this.serviciosRepository = serviciosRepository;
		this.configuracionesIngresoRepository = configuracionesIngresoRepository;
		this.estacionamientoValidation = estacionamientoValidation;
	}

	@Transactional
	public void registarVehiculo(RegistrarVehiculoDTO registrarVehiculo) throws EstacionamientoException {
		TipoVehiculo tipoVehiculo= registrarVehiculo.getCilindraje()==0 ? TipoVehiculo.CARRO: TipoVehiculo.MOTO;		
		if(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())>0){
			throw new EstacionamientoException("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		}		
		Long vehiculosIngresados= serviciosRepository.countByVehiculoIngresado(tipoVehiculo);
		if((tipoVehiculo.equals(TipoVehiculo.CARRO) && vehiculosIngresados.intValue()==maximoCarros) || 
				(tipoVehiculo.equals(TipoVehiculo.MOTO) && vehiculosIngresados.intValue()==maximoMotos)){
			throw new EstacionamientoException("No hay cupo para el vehiculo.");
		}
		if(!estacionamientoValidation.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), tipoVehiculo, configuracionesIngresoRepository.findAll())){
			throw new EstacionamientoException("El vehiculo no esta autorizado para ingresar el dia de hoy.");
		}
		
		
		Servicios servicios= new Servicios();
				
		servicios.setPlaca(registrarVehiculo.getPlaca());
		servicios.setCilindraje(registrarVehiculo.getCilindraje());
		servicios.setFechaHoraIngreso(new Date());
		servicios.setTipoVehiculo(tipoVehiculo);
		
		serviciosRepository.save(servicios);	
	}
}
