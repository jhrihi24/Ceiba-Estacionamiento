package com.ceiba.estacionamiento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.dto.RespuestaDTO;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.service.EstacionamientoService;
import com.ceiba.estacionamiento.validation.EstacionamientoValidation;

@RestController
@RequestMapping("/api/estacionamiento")
public class EstacionamientoController extends AbstractController{
	
	private EstacionamientoService estacionamientoService;
	private EstacionamientoValidation estacionamientoValidation;
		
	@Autowired
	public EstacionamientoController(EstacionamientoService estacionamientoService, EstacionamientoValidation estacionamientoValidation) {
		this.estacionamientoService = estacionamientoService;
		this.estacionamientoValidation = estacionamientoValidation;
	}
	
	@PostMapping
	public RespuestaDTO<String> registrarVehiculo(@RequestBody RegistrarVehiculoDTO registrarVehiculo) throws EstacionamientoException{
		RespuestaDTO<String> respuesta= new RespuestaDTO<>();
		String mensajeValidation= estacionamientoValidation.validarCamposRegistroVehiculo(registrarVehiculo);
		
		if(mensajeValidation.isEmpty()){
			estacionamientoService.registarVehiculo(registrarVehiculo);
			respuesta.setSuccess(Boolean.FALSE);
			respuesta.setMensaje("El vehiculo con la placa "+registrarVehiculo.getPlaca()+" ingresado con exito.");
		}else{
			respuesta.setSuccess(Boolean.FALSE);
			respuesta.setMensaje(mensajeValidation);
		}
		
		return respuesta;
	}
	
}
