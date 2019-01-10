package com.ceiba.estacionamiento.validation;

import org.springframework.stereotype.Component;

import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;

@Component
public class EstacionamientoValidation {
	
	public String validarRegistroVehiculo(RegistrarVehiculoDTO registrarVehiculo){
		String mensaje= "";
		
		if(registrarVehiculo.getPlaca().isEmpty()){
			mensaje= "Debe ingresar una placa";
		}		
		
		return mensaje;
	}
	
}
