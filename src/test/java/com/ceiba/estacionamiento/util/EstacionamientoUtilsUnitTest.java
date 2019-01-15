package com.ceiba.estacionamiento.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class EstacionamientoUtilsUnitTest {
			
	@Before
	public void init(){
		
	}
	
	@Test
	public void buscarCadenaInicioTRUE(){
		assertTrue(EstacionamientoUtils.buscarCadenaInicio("CBJ", "C"));
	}
	
	@Test
	public void bucarCadenaInicioFALSE(){
		assertFalse(EstacionamientoUtils.buscarCadenaInicio("CBJ", "B"));
	}
	
	@Test
	public void bucarCadenaFinalTRUE(){
		assertTrue(EstacionamientoUtils.bucarCadenaFinal("ABC", "C"));
	}
	
	@Test
	public void bucarCadenaFinalFALSE(){
		assertFalse(EstacionamientoUtils.bucarCadenaFinal("ABC", "T"));
	}
	
	@Test
	public void calcularHorasEntreFechas(){
		Calendar fechaInicial = Calendar.getInstance();
		fechaInicial.set(2019, 1, 13, 15, 30);
		Calendar fechaFinal= Calendar.getInstance();
		fechaFinal.set(2019, 1, 14, 17, 0);
		EstacionamientoUtils.calcularHorasEntreFechas(fechaInicial.getTime(), fechaFinal.getTime());
		assertEquals(new Long(26), EstacionamientoUtils.calcularHorasEntreFechas(fechaInicial.getTime(), fechaFinal.getTime()));
	}
	
	@Test
	public void cobroDia(){
		assertEquals(new BigDecimal(8000), EstacionamientoUtils.cobroDia(new BigDecimal(8000), new Long(27)));
	}
	
	@Test
	public void cobroHora(){
		assertEquals(new BigDecimal(3000), EstacionamientoUtils.cobroHora(new BigDecimal(1000), new Long(27)));
	}
	
}
