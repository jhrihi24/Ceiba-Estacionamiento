package com.ceiba.estacionamiento.enums;

public enum TipoVehiculo {
	
	MOTO(1),
	CARRO(2);
	
	private Integer value;
	
	TipoVehiculo(Integer value){
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
}
