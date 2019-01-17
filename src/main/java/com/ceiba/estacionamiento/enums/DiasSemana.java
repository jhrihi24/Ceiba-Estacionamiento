package com.ceiba.estacionamiento.enums;

public enum DiasSemana {
	
	LUNES("L", "Monday"),
	MARTES("M", "Tuesday"),
	MIERCOLES("Mi", "Wednesday"),
	JUEVES("J", "Thursday"),
	VIERNES("V", "Friday"),
	SABADO("S", "Saturday"),
	DOMINGO("D", "Sunday");
	
	private final String identificador;
	private final String descripcion;
	
	DiasSemana(String identificador, String descripcion){
		this.identificador= identificador;
		this.descripcion= descripcion;
	}
	
	public String getIndentificador(){
		return identificador;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public static String getDescripcion(String identificador){
		for(DiasSemana diaSemana: DiasSemana.values()){
			if(diaSemana.getIndentificador().equals(identificador)){
				return diaSemana.getDescripcion();
			}
		}
		
		return null;
	}
	
}
