package com.ceiba.estacionamiento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estacionamiento")
public class EstacionamientoController {
	
	@GetMapping
	public String saludar(){
		return "hola mundo";
	}
	
}
