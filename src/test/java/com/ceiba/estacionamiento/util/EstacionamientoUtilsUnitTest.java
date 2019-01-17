package com.ceiba.estacionamiento.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ceiba.estacionamiento.enums.TipoVehiculo;

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
		assertEquals(Long.valueOf(26), EstacionamientoUtils.calcularHorasEntreFechas(fechaInicial.getTime(), fechaFinal.getTime()));
	}
	
	@Test
	public void cobroDia(){
		assertEquals(BigDecimal.valueOf(8000), EstacionamientoUtils.cobroDia(BigDecimal.valueOf(8000), Long.valueOf(27)));
	}
	
	@Test
	public void cobroHora(){
		assertEquals(BigDecimal.valueOf(3000), EstacionamientoUtils.cobroHora(BigDecimal.valueOf(1000), Long.valueOf(27)));
	}
	
	@Test
	public void cobroTRM(){
		assertEquals(BigDecimal.valueOf(1).setScale(2,  BigDecimal.ROUND_UP), EstacionamientoUtils.cobroTRM(BigDecimal.valueOf(3000), BigDecimal.valueOf(3000)));
	}
	
	@Test
	public void cobroTRMCero(){
		assertEquals(BigDecimal.valueOf(0), EstacionamientoUtils.cobroTRM(BigDecimal.valueOf(3000), BigDecimal.valueOf(0)));
	}	

	@Test
	public void validarPlacaParticulares(){
		assertTrue(EstacionamientoUtils.validarPlacaValida("CHE858", TipoVehiculo.CARRO));
	}
	
	@Test
	public void validarPlacaDiplomaticos(){
		assertTrue(EstacionamientoUtils.validarPlacaValida("CH8588", TipoVehiculo.CARRO));
	}
	
	@Test
	public void validarPlacaCarga(){
		assertTrue(EstacionamientoUtils.validarPlacaValida("C9858", TipoVehiculo.CARRO));
	}
	
	@Test
	public void validarPlacaMotos(){
		assertTrue(EstacionamientoUtils.validarPlacaValida("CBJ57C", TipoVehiculo.MOTO));
	}
	
	@Test
	public void validarPlacaFormatoNoValidoCarro(){
		assertFalse(EstacionamientoUtils.validarPlacaValida("CBJ57C", TipoVehiculo.CARRO));
	}
	
	@Test
	public void validarPlacaCargaNoValidoMoto(){
		assertFalse(EstacionamientoUtils.validarPlacaValida("C9858", TipoVehiculo.MOTO));
	}
	
}
