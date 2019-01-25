package com.ceiba.estacionamiento.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
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
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("TRI852").build();
	}
			
	@Test
	public void testARegistrarVehiculo() throws EstacionamientoException, InterruptedException{		
		RespuestaDTO<String> respuestaDTO= estacionamientoController.registrarVehiculo(registrarVehiculo);
		assertTrue(respuestaDTO.isSuccess());
		assertEquals("El veh\u00EDculo con la placa "+registrarVehiculo.getPlaca()+" ingresado con exito.", respuestaDTO.getMensaje());
	}
	
	@Test
	public void testBRegistrarVehiculoPlacaDuplicada() throws EstacionamientoException, InterruptedException{	
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		estacionamientoController.registrarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void testCRegistrarVehiculoPlacaSinIngresar() throws EstacionamientoException{		
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("").build();
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("Debe ingresar una placa");
		estacionamientoController.registrarVehiculo(registrarVehiculo);
	}
			
	@Test
	public void testDGetServiciosActivosSinPlaca(){
		assertEquals(1, estacionamientoController.getServiciosActivos(null).getData().size());
	}
	
	@Test
	public void testEGetServiciosActivosConPlaca(){
		assertEquals(1, estacionamientoController.getServiciosActivos("TR").getData().size());
	}
	
	@Test
	public void testFSalidaVehiculoNoEncontrado() throws EstacionamientoException, RemoteException{
		param.put("idServicio", 0L);
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("El servicio no existe.");
		estacionamientoController.salidaVehiculo(param);
	}
	
	@Test
	public void testGSalidaVehiculoSuccess() throws EstacionamientoException, RemoteException{
		param.put("idServicio", 1L);
		RespuestaDTO<String> respuestaDTO= estacionamientoController.salidaVehiculo(param);
		assertTrue(respuestaDTO.isSuccess());
	}
			
	@Test
	public void testMRegistrarVehiculoCupoMotos() throws EstacionamientoException{
		for(Integer i=0; i<10; i++){
			String indiceNumero= i<10 ? "0"+i : i.toString();
			estacionamientoController.registrarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS"+indiceNumero+"C").withCilindraje(600).build());
		}
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("No hay cupo para el veh\u00EDculo.");
		estacionamientoController.registrarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS21C").withCilindraje(600).build());
	}
		
}
