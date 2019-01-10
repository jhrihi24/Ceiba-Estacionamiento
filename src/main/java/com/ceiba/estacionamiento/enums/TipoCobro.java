package com.ceiba.estacionamiento.enums;

public enum TipoCobro {
	
	HORA(1),
	DIA(2);
	
	private Integer value;
	
	TipoCobro(Integer value){
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
}
