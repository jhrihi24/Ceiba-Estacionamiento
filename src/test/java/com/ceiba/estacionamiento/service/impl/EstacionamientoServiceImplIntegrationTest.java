package com.ceiba.estacionamiento.service.impl;

import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.exception.EstacionamientoException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstacionamientoServiceImplIntegrationTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Autowired
	private EstacionamientoServiceImpl estacionamientoServiceImpl;

	private RegistrarVehiculoDTO registrarVehiculo;
	
	@Before
	public void init(){
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().build();
		ReflectionTestUtils.setField(estacionamientoServiceImpl, "maximoMotos", 10);
		ReflectionTestUtils.setField(estacionamientoServiceImpl, "maximoCarros", 20);
	}
	
	@Test
	public void testA() throws EstacionamientoException{
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);	
	}
	
	@Test
	public void testB() throws EstacionamientoException{	
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void testSalidaVehiculoFail() throws EstacionamientoException{
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("El servicio no existe.");
		estacionamientoServiceImpl.salidaVehiculo(0L, new Date());
	}
	
	@Test
	public void testSalidaVehiculoSuccess() throws EstacionamientoException{
		assertEquals("1000.00", estacionamientoServiceImpl.salidaVehiculo(1L, new Date()).getCobrado().toString());
	}
	
}
