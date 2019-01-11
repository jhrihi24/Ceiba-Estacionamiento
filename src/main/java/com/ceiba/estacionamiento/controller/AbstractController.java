package com.ceiba.estacionamiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ceiba.estacionamiento.dto.RespuestaDTO;
import com.ceiba.estacionamiento.exception.EstacionamientoException;

/**
 * Controlador abstracto que recibe los errores inesperados ocurridos en la aplicacion para
 * las vistas de jsp no para las peticiones REST
 * @author dromeroa
 *
 */
@Controller()
public class AbstractController {	
			
	@ExceptionHandler(value=Exception.class )
	public RespuestaDTO<String> errorInesperado(final Exception oops){
		RespuestaDTO<String> respuesta= new RespuestaDTO<>();
		respuesta.setSuccess(Boolean.FALSE);
		respuesta.setMensaje("Ocurrio un error inesperado");
		return respuesta;
	}
	
	@ExceptionHandler(value=EstacionamientoException.class)
	public RespuestaDTO<String> errorValidation(final EstacionamientoException oops){
		RespuestaDTO<String> respuesta= new RespuestaDTO<>();
		respuesta.setSuccess(Boolean.FALSE);
		respuesta.setMensaje(oops.getMessage());
		return respuesta;
	}
	
}
