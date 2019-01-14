package com.ceiba.estacionamiento.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RespuestaDTO<T> {
	private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";

	protected boolean success;
	
	protected int idError;
	
	protected String dateTime;
	
	protected String mensaje;
	
	protected T data;
	
	public RespuestaDTO() {	
		this(true,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),null,null);
	}
		
	public RespuestaDTO(boolean success, String dateTime, String message, T data) {
		this.success = success;
		this.dateTime = dateTime;
		this.mensaje = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String message) {
		this.mensaje = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
