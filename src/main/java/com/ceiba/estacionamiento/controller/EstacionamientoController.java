package com.ceiba.estacionamiento.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.estacionamiento.domain.Servicios;
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
		RespuestaDTO<String> respuesta= estacionamientoValidation.validarCamposRegistroVehiculo(registrarVehiculo);
		if(respuesta.isSuccess()){
			estacionamientoService.registarVehiculo(registrarVehiculo);
			respuesta.setMensaje("El vehiculo con la placa "+registrarVehiculo.getPlaca()+" ingresado con exito.");
		}		
		return respuesta;
	}
	
	@PutMapping
	public RespuestaDTO<String> salidaVehiculo(@RequestBody Map<String, Long> param) throws EstacionamientoException{
		Date fechaActual= new Date();
		RespuestaDTO<String> respuesta= new RespuestaDTO<>();		
		Servicios servicios= estacionamientoService.salidaVehiculo(param.get("idServicio"), fechaActual);
		respuesta.setMensaje("El total a pagar por el vehiculo es: "+servicios.getCobrado());	
		return respuesta;
	}
	
}
