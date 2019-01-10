package com.ceiba.estacionamiento.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.estacionamiento.domain.Servicios;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.repository.ServiciosRepository;
import com.ceiba.estacionamiento.service.EstacionamientoService;

@Service
public class EstacionamientoServiceImpl implements EstacionamientoService{
	
	private ServiciosRepository serviciosRepository;
	
	@Autowired	
	public EstacionamientoServiceImpl(ServiciosRepository serviciosRepository) {
		this.serviciosRepository = serviciosRepository;
	}

	@Transactional
	public void registarVehiculo(RegistrarVehiculoDTO registrarVehiculo) {
		Servicios servicios= new Servicios();
		
		servicios.setPlaca(registrarVehiculo.getPlaca());
		servicios.setCilindraje(registrarVehiculo.getCilindraje());
		servicios.setFechaHoraIngreso(new Date());
		servicios.setTipoVehiculo(servicios.getCilindraje()==0 ? TipoVehiculo.CARRO: TipoVehiculo.MOTO);
		
		serviciosRepository.save(servicios);	
	}

}
