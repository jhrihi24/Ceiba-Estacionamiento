package com.ceiba.estacionamiento.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import com.ceiba.estacionamiento.enums.TipoVehiculo;

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
	
	public static BigDecimal cobroTRM(BigDecimal cobroTotal, BigDecimal trm){
		return trm.doubleValue() > 0 ? cobroTotal.divide(trm, 2, RoundingMode.HALF_UP): BigDecimal.valueOf(0);
	}
	
	public static Boolean validarPlacaValida(String placa, TipoVehiculo tipoVehiculo){
		if(tipoVehiculo==TipoVehiculo.CARRO && validarPlacaParticularesPublicos(placa)){
			return Boolean.TRUE;
		}
		if(tipoVehiculo==TipoVehiculo.CARRO && validarPlacaDiplomaticos(placa)){
			return Boolean.TRUE;
		}
		if(tipoVehiculo==TipoVehiculo.CARRO && validarPlacaCarga(placa)){
			return Boolean.TRUE;
		}
		if(tipoVehiculo==TipoVehiculo.MOTO && validarPlacaMoto(placa)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;		
	}
	
	private static Boolean validarPlacaParticularesPublicos(String placa){
		if(placa.length()==6){
			long countLetras= placa.substring(0, 3).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
			long countNumeros= placa.substring(3, 6).chars().filter(ch -> ch >= '0' && ch <= '9').count();
			return (countLetras==3 && countNumeros==3);
		}
		
		return Boolean.FALSE;
	}
	
	private static Boolean validarPlacaDiplomaticos(String placa){
		if(placa.length()==6){
			long countLetras= placa.substring(0, 2).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
			long countNumeros= placa.substring(2, 6).chars().filter(ch -> ch >= '0' && ch <= '9').count();
			return (countLetras==2 && countNumeros==4);
		}
		
		return Boolean.FALSE;
	}
	
	private static Boolean validarPlacaCarga(String placa){
		if(placa.length()==5){
			long countLetras= placa.substring(0, 1).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
			long countNumeros= placa.substring(1, 5).chars().filter(ch -> ch >= '0' && ch <= '9').count();
			return (countLetras==1 && countNumeros==4);
		}
		
		return Boolean.FALSE;
	}
	
	private static Boolean validarPlacaMoto(String placa){
		if(placa.length()==6){
			long countLetras= placa.substring(0, 3).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
			long countNumeros= placa.substring(3, 5).chars().filter(ch -> ch >= '0' && ch <= '9').count();
			long countUltimaLetra= placa.substring(5, 6).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
			return (countLetras==3 && countNumeros==2 && countUltimaLetra==1);
		}
		
		return Boolean.FALSE;
	}
	
}
