package com.ceiba.estacionamiento.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	
}
