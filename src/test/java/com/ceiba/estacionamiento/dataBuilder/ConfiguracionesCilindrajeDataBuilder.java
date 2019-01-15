package com.ceiba.estacionamiento.dataBuilder;

import java.math.BigDecimal;

import com.ceiba.estacionamiento.domain.ConfiguracionesCilindraje;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

public class ConfiguracionesCilindrajeDataBuilder {
	
	private ConfiguracionesCilindraje configuracionesCilindraje= new ConfiguracionesCilindraje();
	
	public ConfiguracionesCilindrajeDataBuilder(){
		this.configuracionesCilindraje.setId(1L);
		this.configuracionesCilindraje.setTipoVehiculo(TipoVehiculo.MOTO);
		this.configuracionesCilindraje.setCilindraje(500);
		this.configuracionesCilindraje.setCobroAdicional(new BigDecimal(2000));
	}
	
	public ConfiguracionesCilindrajeDataBuilder withId(Long id){
		this.configuracionesCilindraje.setId(id);
		return this;
	}
	
	public ConfiguracionesCilindrajeDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo){
		this.configuracionesCilindraje.setTipoVehiculo(tipoVehiculo);
		return this;
	}
	
	public ConfiguracionesCilindrajeDataBuilder withCilindraje(Integer cilindraje){
		this.configuracionesCilindraje.setCilindraje(cilindraje);
		return this;
	}
	
	public ConfiguracionesCilindrajeDataBuilder withCobroAdicional(BigDecimal cobroAdicional){
		this.configuracionesCilindraje.setCobroAdicional(cobroAdicional);
		return this;
	}
	
	public ConfiguracionesCilindraje build(){
		return this.configuracionesCilindraje;
	}
}
