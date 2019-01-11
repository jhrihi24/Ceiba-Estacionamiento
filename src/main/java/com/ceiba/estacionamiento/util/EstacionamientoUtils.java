package com.ceiba.estacionamiento.util;

public class EstacionamientoUtils {
	
	private EstacionamientoUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Boolean buscarCadenaInicio(String padre, String hijo){
		if(padre.indexOf(hijo, 0)<0){
			return Boolean.FALSE;
		}else{
			return Boolean.TRUE;
		}
	}
	
	public static Boolean bucarCadenaFinal(String padre, String hijo){
		int indiceInicial= padre.length() - hijo.length();
		if(padre.indexOf(hijo, indiceInicial)<0){
			return Boolean.FALSE;
		}else{
			return Boolean.TRUE;
		}
	}
	
}
