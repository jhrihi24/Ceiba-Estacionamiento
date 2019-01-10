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
		this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),null,null);
	}
	
	public RespuestaDTO(String message, T data) {
		this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),message,data);
	}
	
	public RespuestaDTO(String message) {
		this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),message,null);
	}
	
	public RespuestaDTO(String message, boolean isSuccess) {
		this(isSuccess,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),message,null);
	}
	
	public RespuestaDTO(Throwable ex) {	
		this(false,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),ex.getMessage(),null);
	}
	
	public RespuestaDTO(T data) {
		this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),null,data);
	}
	
	public RespuestaDTO(boolean success, int idError, String dateTime, String message, T data) {
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
	
	public int getIdError() {
		return idError;
	}

	public void setIdError(int idError) {
		this.idError = idError;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
