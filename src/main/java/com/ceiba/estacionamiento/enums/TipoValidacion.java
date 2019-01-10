package com.ceiba.estacionamiento.enums;

public enum TipoValidacion {
	
	INICIO(1),
	FINAL(2);
	
	private Integer value;
	
	TipoValidacion(Integer value){
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
}
