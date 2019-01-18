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
		if(tipoVehiculo==TipoVehiculo.CARRO){
			//Placas particulares
			if(placa.length()==6 && 
				(placa.substring(0, 3).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count()==3 && 
				placa.substring(3, 6).chars().filter(ch -> ch >= '0' && ch <= '9').count()==3)){
				return Boolean.TRUE;			
			}
			
			//Placas de diplomaticos
			if(placa.length()==6 && 
				(placa.substring(0, 2).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count()==2 &&
				placa.substring(2, 6).chars().filter(ch -> ch >= '0' && ch <= '9').count()==4)){
				return Boolean.TRUE;
			}
			
			//Placas de carga
			if(placa.length()==5 &&
				(placa.substring(0, 1).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count()==1 &&
				placa.substring(1, 5).chars().filter(ch -> ch >= '0' && ch <= '9').count()==4)){
				return Boolean.TRUE;
			}
		}
		
		//Placas de moto
		if(tipoVehiculo==TipoVehiculo.MOTO && placa.length()==6 &&
			(placa.substring(0, 3).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count()==3 &&
			placa.substring(3, 5).chars().filter(ch -> ch >= '0' && ch <= '9').count()==2 &&
			placa.substring(5, 6).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count()==1)){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;		
	}
	
}
