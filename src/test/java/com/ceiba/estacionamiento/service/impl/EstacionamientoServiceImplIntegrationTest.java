package com.ceiba.estacionamiento.service.impl;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
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

import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.exception.EstacionamientoException;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)*/
public class EstacionamientoServiceImplIntegrationTest {
	
	/*@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Autowired
	private EstacionamientoServiceImpl estacionamientoServiceImpl;

	private RegistrarVehiculoDTO registrarVehiculo;
	
	@Before
	public void init(){
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().build();
	}
				
	@Test
	public void testARegistroVehiculos() throws EstacionamientoException{
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);	
	}
	
	@Test
	public void testBRegistroVehiculos() throws EstacionamientoException{	
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void testCServiciosActivosNull(){
		assertEquals(1, estacionamientoServiceImpl.getServiciosActivos(null).size());
	}
	
	@Test
	public void testDServiciosActivosVacio(){
		assertEquals(1, estacionamientoServiceImpl.getServiciosActivos("").size());
	}
	
	@Test
	public void testEServiciosActivosVacioFiltro(){
		assertEquals(1, estacionamientoServiceImpl.getServiciosActivos("TR").size());
	}
	
	@Test
	public void testFServiciosActivosNoEncontrado(){
		assertEquals(0, estacionamientoServiceImpl.getServiciosActivos("KK").size());
	}
	
	@Test
	public void testGSalidaVehiculoFail() throws EstacionamientoException, RemoteException{
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("El servicio no existe.");
		estacionamientoServiceImpl.salidaVehiculo(0L, new Date());
	}
	
	@Test
	public void testHSalidaVehiculoSuccess() throws EstacionamientoException, RemoteException{
		assertEquals("1000.00", estacionamientoServiceImpl.salidaVehiculo(1L, new Date()).getCobrado().toString());
	}
	
	@Test
	public void testIRegistroVehiculosConCilindrajeAlto() throws EstacionamientoException{
		estacionamientoServiceImpl.registarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS85S").withCilindraje(600).build());	
	}
	
	@Test
	public void testJSalidaVehiculoSuccessConCilindrajeMayor() throws EstacionamientoException, RemoteException{
		assertEquals("2500.00", estacionamientoServiceImpl.salidaVehiculo(2L, new Date()).getCobrado().toString());
	}
	
	@Test
	public void testKRegistrarVehiculoConPlacaSinBuenFormatoCarro() throws EstacionamientoException{
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("La placa ingresada no cuenta con el formato valido.");
		estacionamientoServiceImpl.registarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS85S").build());	
	}
	
	@Test
	public void testLRegistrarVehiculoConPlacaSinBuenFormatoMoto() throws EstacionamientoException{
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("La placa ingresada no cuenta con el formato valido.");
		estacionamientoServiceImpl.registarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS858").withCilindraje(600).build());	
	}
	
	@Test
	public void testMRegistrarVehiculoCupoCarros() throws EstacionamientoException{
		for(Integer i=0; i<20; i++){
			String indiceNumero= i<10 ? "0"+i : i.toString();
			estacionamientoServiceImpl.registarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS"+indiceNumero+"8").build());
		}
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("No hay cupo para el veh\u00EDculo.");
		estacionamientoServiceImpl.registarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS218").build());
	}
	
	@Test
	public void testMRegistrarVehiculoCupoMotos() throws EstacionamientoException{
		for(Integer i=0; i<10; i++){
			String indiceNumero= i<10 ? "0"+i : i.toString();
			estacionamientoServiceImpl.registarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS"+indiceNumero+"C").withCilindraje(600).build());
		}
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("No hay cupo para el veh\u00EDculo.");
		estacionamientoServiceImpl.registarVehiculo(new RegistrarVehiculoDTODataBuilder().withPlaca("TRS21C").withCilindraje(600).build());
	}*/
	
}
