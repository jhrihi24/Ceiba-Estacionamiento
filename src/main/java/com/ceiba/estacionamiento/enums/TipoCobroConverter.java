package com.ceiba.estacionamiento.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class TipoCobroConverter implements AttributeConverter<TipoCobro, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoCobro attribute) {
		return attribute.getValue();
	}

	@Override
	public TipoCobro convertToEntityAttribute(Integer dbData) {
		return TipoCobro.toTipoCobro(dbData);
	}

	
	
}
