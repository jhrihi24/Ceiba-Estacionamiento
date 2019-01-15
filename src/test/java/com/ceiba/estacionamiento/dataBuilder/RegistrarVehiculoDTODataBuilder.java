package com.ceiba.estacionamiento.dataBuilder;

import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;

public class RegistrarVehiculoDTODataBuilder {
	
	private String placa;	
	private Integer cilindraje;
	
	public RegistrarVehiculoDTODataBuilder() {
		this.placa = "CBJ57C";
		this.cilindraje = 0;
	}
	
	public RegistrarVehiculoDTODataBuilder withPlaca(String placa){
		this.placa= placa;
		return this;
	}
	
	public RegistrarVehiculoDTODataBuilder withCilindraje(Integer cilindraje){
		this.cilindraje= cilindraje;
		return this;
	}
	
	public RegistrarVehiculoDTO build(){
		return new RegistrarVehiculoDTO(placa, cilindraje);
	}
		
}
