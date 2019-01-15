package com.ceiba.estacionamiento.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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

import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.dto.RespuestaDTO;
import com.ceiba.estacionamiento.exception.EstacionamientoException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstacionamientoControllerIntegrationTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Autowired
	private EstacionamientoController estacionamientoController;
	
	private RegistrarVehiculoDTO registrarVehiculo;
	
	private Map<String, Long> param;
	
	@Before
	public void init(){
		param= new HashMap<String, Long>();
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("FAC85X").build();
	}
	
	@Test
	public void testA() throws EstacionamientoException, InterruptedException{		
		RespuestaDTO<String> respuestaDTO= estacionamientoController.registrarVehiculo(registrarVehiculo);
		assertTrue(respuestaDTO.isSuccess());
		assertEquals("El vehiculo con la placa "+registrarVehiculo.getPlaca()+" ingresado con exito.", respuestaDTO.getMensaje());
	}
	
	@Test
	public void testB() throws EstacionamientoException, InterruptedException{	
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		estacionamientoController.registrarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void testC() throws EstacionamientoException{
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("").build();
		RespuestaDTO<String> respuestaDTO= estacionamientoController.registrarVehiculo(registrarVehiculo);
		assertFalse(respuestaDTO.isSuccess());
		assertEquals("Debe ingresar una placa", respuestaDTO.getMensaje());
	}
	
	@Test
	public void testSalidaVehiculoNoEncontrado() throws EstacionamientoException{
		param.put("idServicio", 0L);
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("El servicio no existe.");
		estacionamientoController.salidaVehiculo(param);
	}
	
	@Test
	public void testSalidaVehiculoSuccess() throws EstacionamientoException{
		param.put("idServicio", 1L);
		RespuestaDTO<String> respuestaDTO= estacionamientoController.salidaVehiculo(param);
		assertTrue(respuestaDTO.isSuccess());
		assertEquals("El total a pagar por el vehiculo es: 1000.00", respuestaDTO.getMensaje());
	}
		
}
