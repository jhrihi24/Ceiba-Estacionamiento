package com.ceiba.estacionamiento.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

public class EstacionamientoUtils {
	
	private EstacionamientoUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Boolean buscarCadenaInicio(String padre, String hijo){
		String particionString= padre.substring(0, hijo.length());
		if(particionString.equals(hijo)){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}
	
	public static Boolean bucarCadenaFinal(String padre, String hijo){
		int indiceInicial= padre.length() - hijo.length();
		if(padre.indexOf(hijo, indiceInicial)>-1){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}
	
	public static Long calcularHorasEntreFechas(Date fechaInicial, Date fechaFinal){
		Calendar calFechaInicial=Calendar.getInstance();
		Calendar calFechaFinal=Calendar.getInstance();

		/**Le pasamos el objeto Date al metodo set time*/
		calFechaInicial.setTime(fechaInicial);
		calFechaFinal.setTime(fechaFinal);
		
		double numeroDeHoras= (double) (calFechaFinal.getTimeInMillis()-calFechaInicial.getTimeInMillis())/1000/60/60;
		
		return (long) Math.ceil(numeroDeHoras);	
	}
	
	public static BigDecimal cobroDia(BigDecimal cobro, Long horas){
		BigDecimal numeroDias= new BigDecimal(horas).divide(new BigDecimal(24), RoundingMode.HALF_DOWN);
		return cobro.multiply(numeroDias);
	}
	
	public static BigDecimal cobroHora(BigDecimal cobro, Long horas){
		BigDecimal numeroDias= new BigDecimal(horas).divide(new BigDecimal(24), RoundingMode.HALF_DOWN);
		BigDecimal numeroHoras= new BigDecimal(horas).subtract(numeroDias.multiply(new BigDecimal(24)));
		return cobro.multiply(numeroHoras);
	}
	
}
