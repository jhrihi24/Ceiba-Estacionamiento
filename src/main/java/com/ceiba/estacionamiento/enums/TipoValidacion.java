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
	
	public static TipoValidacion toTipoValidacion(Integer value) {        
		if(value==1){
        	return TipoValidacion.INICIO;
        }else if(value==2){
	        return TipoValidacion.FINAL; 
        
        }
		return null;
    }
	
}
