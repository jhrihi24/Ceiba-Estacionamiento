package com.ceiba.estacionamiento.dataBuilder;

import java.math.BigDecimal;

import com.ceiba.estacionamiento.domain.Precios;
import com.ceiba.estacionamiento.enums.TipoCobro;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

public class PreciosDataBuilder {
	
	private Precios precios= new Precios();
	
	public PreciosDataBuilder(){
		this.precios.setId(1L);
		this.precios.setTipoCobro(TipoCobro.DIA);
		this.precios.setTipoVehiculo(TipoVehiculo.MOTO);
		this.precios.setPrecio(new BigDecimal(4000));
	}
	
	public PreciosDataBuilder withId(Long id){
		this.precios.setId(id);
		return this;
	}
	
	public PreciosDataBuilder withTipoCobro(TipoCobro tipoCobro){
		this.precios.setTipoCobro(tipoCobro);
		return this;
	}
	
	public PreciosDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo){
		this.precios.setTipoVehiculo(tipoVehiculo);
		return this;
	}
	
	public PreciosDataBuilder withPrecio(BigDecimal precio){
		this.precios.setPrecio(precio);
		return this;
	}
	
	public Precios build(){
		return this.precios;
	}
	
}
