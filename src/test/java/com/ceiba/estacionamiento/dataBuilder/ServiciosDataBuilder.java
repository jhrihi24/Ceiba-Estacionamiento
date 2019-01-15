package com.ceiba.estacionamiento.dataBuilder;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.ceiba.estacionamiento.domain.Servicios;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

public class ServiciosDataBuilder {
	
	private Servicios servicios= new Servicios();
	
	public ServiciosDataBuilder(){
		Calendar fechaHoraIngreso = Calendar.getInstance();
		fechaHoraIngreso.set(2019, 1, 14, 15, 30);
		
		this.servicios.setId(1L);
		this.servicios.setPlaca("CBJ57C");
		this.servicios.setFechaHoraIngreso(fechaHoraIngreso.getTime());
		this.servicios.setCilindraje(0);
		this.servicios.setTipoVehiculo(TipoVehiculo.MOTO);
	}
	
	public ServiciosDataBuilder withId(Long id){
		this.servicios.setId(id);
		return this;
	}
	
	public ServiciosDataBuilder withPlaca(String placa){
		this.servicios.setPlaca(placa);
		return this;		
	}
	
	public ServiciosDataBuilder withFechaHoraIngreso(Date fechaHoraIngreso){
		this.servicios.setFechaHoraIngreso(fechaHoraIngreso);
		return this;
	}
	
	public ServiciosDataBuilder withCilindraje(Integer cilindraje){
		this.servicios.setCilindraje(cilindraje);
		return this;
	}
	
	public ServiciosDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo){
		this.servicios.setTipoVehiculo(tipoVehiculo);
		return this;
	}
	
	public ServiciosDataBuilder withCobrado(BigDecimal cobrado){
		this.servicios.setCobrado(cobrado);
		return this;
	}
	
	public ServiciosDataBuilder withFechaHoraSalida(Date fechaHoraSalida){
		this.servicios.setFechaHoraSalida(fechaHoraSalida);
		return this;
	}
	
	public Servicios build(){
		return this.servicios;
	}
	
}
