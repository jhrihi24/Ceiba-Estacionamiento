package com.ceiba.estacionamiento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.dto.RespuestaDTO;
import com.ceiba.estacionamiento.service.EstacionamientoService;

@RestController
@RequestMapping("/api/estacionamiento")
public class EstacionamientoController {
	
	private EstacionamientoService estacionamientoService;
		
	@Autowired
	public EstacionamientoController(EstacionamientoService estacionamientoService) {
		this.estacionamientoService = estacionamientoService;
	}
	
	@PostMapping
	public RespuestaDTO<String> registrarVehiculo(@RequestBody RegistrarVehiculoDTO registrarVehiculo){
		return null;
	}
	
}
