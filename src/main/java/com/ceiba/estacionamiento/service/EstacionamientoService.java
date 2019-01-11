package com.ceiba.estacionamiento.service;

import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.exception.EstacionamientoException;

public interface EstacionamientoService {
	
	public void registarVehiculo(RegistrarVehiculoDTO registrarVehiculo) throws EstacionamientoException;
	
}
