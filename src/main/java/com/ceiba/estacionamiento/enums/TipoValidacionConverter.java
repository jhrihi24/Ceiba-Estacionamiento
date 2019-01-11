package com.ceiba.estacionamiento.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class TipoValidacionConverter implements AttributeConverter<TipoValidacion, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoValidacion attribute) {
		return attribute.getValue();
	}

	@Override
	public TipoValidacion convertToEntityAttribute(Integer dbData) {
		return TipoValidacion.toTipoValidacion(dbData);
	}

}
