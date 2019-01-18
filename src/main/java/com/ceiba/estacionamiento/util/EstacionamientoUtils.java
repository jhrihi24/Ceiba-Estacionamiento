package com.ceiba.estacionamiento.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ceiba.estacionamiento.enums.TipoVehiculo;

public final class EstacionamientoUtils {
	
	private static Map<String, Integer> mapLetras= new HashMap<>();
	private static Map<String, Integer> mapNumeros= new HashMap<>();
	
	private static final String INICIO= "inicio";
	private static final String FINAL="final";
	private static final String TOTAL="total";
	
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
		
		if(validacionPlacaTipoParticulares(tipoVehiculo, placa)){
			return Boolean.TRUE;			
		}
			
		if(validacionPlacaTipoDiplomaticos(tipoVehiculo, placa)){
			return Boolean.TRUE;
		}
		
		if(validacionPlacaTipoCarga(tipoVehiculo, placa)){
			return Boolean.TRUE;
		}		
		
		if(validacionPlacaTipoMoto(tipoVehiculo, placa)){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;		
	}
	
	private static Boolean validacionPlacaTipoParticulares(TipoVehiculo tipoVehiculo, String placa){		
		mapLetras.put(INICIO, 0);
		mapLetras.put(FINAL, 3);
		mapLetras.put(TOTAL, 3);		
		
		mapNumeros.put(INICIO, 3);
		mapNumeros.put(FINAL, 6);
		mapNumeros.put(TOTAL, 3);
		
		return tipoVehiculo==TipoVehiculo.CARRO && validacionComposicionPlaca(placa, 6, mapLetras, mapNumeros);
	}
	
	private static Boolean validacionPlacaTipoDiplomaticos(TipoVehiculo tipoVehiculo, String placa){
		mapLetras.put(INICIO, 0);
		mapLetras.put(FINAL, 2);
		mapLetras.put(TOTAL, 2);
		
		mapNumeros.put(INICIO, 2);
		mapNumeros.put(FINAL, 6);
		mapNumeros.put(TOTAL, 4);		
		
		return tipoVehiculo==TipoVehiculo.CARRO && validacionComposicionPlaca(placa, 6, mapLetras, mapNumeros);
	}
	
	private static Boolean validacionPlacaTipoCarga(TipoVehiculo tipoVehiculo, String placa){
		mapLetras.put(INICIO, 0);
		mapLetras.put(FINAL, 1);
		mapLetras.put(TOTAL, 1);
		
		mapNumeros.put(INICIO, 1);
		mapNumeros.put(FINAL, 5);
		mapNumeros.put(TOTAL, 4);
		
		return tipoVehiculo==TipoVehiculo.CARRO &&validacionComposicionPlaca(placa, 5, mapLetras, mapNumeros);
	}
	
	private static Boolean validacionPlacaTipoMoto(TipoVehiculo tipoVehiculo, String placa){
		mapLetras.put(INICIO, 0);
		mapLetras.put(FINAL, 3);
		mapLetras.put(TOTAL, 3);
		
		mapNumeros.put(INICIO, 3);
		mapNumeros.put(FINAL, 5);
		mapNumeros.put(TOTAL, 2);
		
		return tipoVehiculo==TipoVehiculo.MOTO && 
				validacionComposicionPlaca(placa, 6, mapLetras, mapNumeros) &&
				placa.substring(5, 6).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count()==1;
	}
	
	private static Boolean validacionComposicionPlaca(String placa, Integer longitudPlaca, Map<String, Integer> mapLetras, Map<String, Integer> mapNumeros){
		return placa.length()==longitudPlaca &&
				placa.substring(mapLetras.get(INICIO), mapLetras.get(FINAL)).chars().filter(ch -> ch >= 'A' && ch <= 'Z').count()==mapLetras.get(TOTAL) &&
				placa.substring(mapNumeros.get(INICIO), mapNumeros.get(FINAL)).chars().filter(ch -> ch >= '0' && ch <= '9').count()==mapNumeros.get(TOTAL);
	}
	
}
