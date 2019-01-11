package com.ceiba.estacionamiento.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class TipoVehiculoConverter implements AttributeConverter<TipoVehiculo, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoVehiculo attribute) {
		return attribute.getValue();
	}

	@Override
	public TipoVehiculo convertToEntityAttribute(Integer dbData) {		
		return TipoVehiculo.toTipoVehiculo(dbData);
	}
	
	
	
}
