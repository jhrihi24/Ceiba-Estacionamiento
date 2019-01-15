package com.ceiba.estacionamiento.service;

import java.util.Date;

import com.ceiba.estacionamiento.domain.Servicios;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.exception.EstacionamientoException;

public interface EstacionamientoService {
	
	public void registarVehiculo(RegistrarVehiculoDTO registrarVehiculo) throws EstacionamientoException;
	
	public Servicios salidaVehiculo(Long idServicio, Date fechaActual) throws EstacionamientoException;
	
}
