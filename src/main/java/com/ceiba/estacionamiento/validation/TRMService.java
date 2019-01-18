package com.ceiba.estacionamiento.validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class TRMService {
	
	public BigDecimal getTrm(){
		return BigDecimal.valueOf(2000);
	}
	
}
