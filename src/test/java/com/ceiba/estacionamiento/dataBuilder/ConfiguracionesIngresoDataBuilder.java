package com.ceiba.estacionamiento.dataBuilder;

import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.enums.TipoValidacion;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

public class ConfiguracionesIngresoDataBuilder {
	
	private ConfiguracionesIngreso configuracionesIngreso= new ConfiguracionesIngreso();
	
	public ConfiguracionesIngresoDataBuilder(){
		this.configuracionesIngreso.setId(1L);
		this.configuracionesIngreso.setValor("A");
		this.configuracionesIngreso.setTipoValidacion(TipoValidacion.INICIO);
		this.configuracionesIngreso.setTipoVehiculo(TipoVehiculo.MOTO);
		this.configuracionesIngreso.setProhibicionDias("D-L");
	}
	
	public ConfiguracionesIngresoDataBuilder withId(Long id){
		this.configuracionesIngreso.setId(id);
		return this;
	}
	
	public ConfiguracionesIngresoDataBuilder withValor(String valor){
		this.configuracionesIngreso.setValor(valor);
		return this;
	}
	
	public ConfiguracionesIngresoDataBuilder withTipoValidacion(TipoValidacion tipoValidacion){
		this.configuracionesIngreso.setTipoValidacion(tipoValidacion);
		return this;
	}
	
	public ConfiguracionesIngresoDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo){
		this.configuracionesIngreso.setTipoVehiculo(tipoVehiculo);
		return this;
	}
	
	public ConfiguracionesIngresoDataBuilder withProhibicionDias(String prohibicionDias){
		this.configuracionesIngreso.setProhibicionDias(prohibicionDias);
		return this;
	}
	
	public ConfiguracionesIngreso build(){
		return this.configuracionesIngreso;
	}
	
}
