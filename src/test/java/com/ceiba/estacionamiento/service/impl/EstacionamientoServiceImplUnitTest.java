package com.ceiba.estacionamiento.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.repository.ConfiguracionesIngresoRepository;
import com.ceiba.estacionamiento.repository.ServiciosRepository;
import com.ceiba.estacionamiento.validation.EstacionamientoValidation;

@RunWith(PowerMockRunner.class)
public class EstacionamientoServiceImplUnitTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Mock
	private ServiciosRepository serviciosRepository;
	
	@Mock
	private ConfiguracionesIngresoRepository configuracionesIngresoRepository;
	
	@Mock
	private EstacionamientoValidation estacionamientoValidation;
	
	@InjectMocks
	private EstacionamientoServiceImpl estacionamientoServiceImpl;

	private RegistrarVehiculoDTO registrarVehiculo;
	
	@Before
	public void init(){
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().build();
		ReflectionTestUtils.setField(estacionamientoServiceImpl, "maximoMotos", 10);
		ReflectionTestUtils.setField(estacionamientoServiceImpl, "maximoCarros", 20);
	}
	
	@Test
	public void registrarVehiculoSuccess() throws EstacionamientoException{
		List<ConfiguracionesIngreso> configuracionesIngresoList= new ArrayList<>();
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(1);
		
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(0L);
		when(configuracionesIngresoRepository.findAll()).thenReturn(configuracionesIngresoList);
		when(estacionamientoValidation.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), tipoVehiculo, configuracionesIngresoList)).thenReturn(Boolean.TRUE);
		
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);	
	}
	
	@Test
	public void registrarVehiculoPlacaDuplicada() throws EstacionamientoException{		
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(1L);
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void registrarVehiculoSinCupoMoto() throws EstacionamientoException{
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(1);
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(10L);
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("No hay cupo para el vehiculo.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void registrarVehiculoSinCupoCarro() throws EstacionamientoException{
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(2);
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withCilindraje(0).build();
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(20L);
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("No hay cupo para el vehiculo.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void registrarVehiculoDiasIngreso() throws EstacionamientoException{
		List<ConfiguracionesIngreso> configuracionesIngresoList= new ArrayList<>();
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(1);
		
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(0L);
		when(configuracionesIngresoRepository.findAll()).thenReturn(configuracionesIngresoList);
		when(estacionamientoValidation.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), tipoVehiculo, configuracionesIngresoList)).thenReturn(Boolean.FALSE);
		
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("El vehiculo no esta autorizado para ingresar el dia de hoy.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}

}
