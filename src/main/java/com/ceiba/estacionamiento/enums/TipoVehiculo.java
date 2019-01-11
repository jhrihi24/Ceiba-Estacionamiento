package com.ceiba.estacionamiento.enums;

public enum TipoVehiculo {
	
	MOTO(1),
	CARRO(2);
	
	private final Integer value;
	
	TipoVehiculo(Integer value){
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public static TipoVehiculo toTipoVehiculo(Integer value) {        
		if(value==1){
        	return TipoVehiculo.MOTO;
        }else if(value==2){
	        return TipoVehiculo.CARRO; 
        
        }
		return null;
    }
		
}
