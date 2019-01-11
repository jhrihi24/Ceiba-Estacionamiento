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
	
	public static TipoCobro toTipoCobro(Integer value) {        
		if(value==1){
        	return TipoCobro.HORA;
        }else if(value==2){
	        return TipoCobro.DIA; 
        
        }
		return null;
    }
	
}
