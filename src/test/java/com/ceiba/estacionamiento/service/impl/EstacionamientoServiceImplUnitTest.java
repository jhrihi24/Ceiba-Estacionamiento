package com.ceiba.estacionamiento.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.mockito.Mockito.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.repository.ConfiguracionesIngresoRepository;
import com.ceiba.estacionamiento.repository.ServiciosRepository;
import com.ceiba.estacionamiento.validation.EstacionamientoValidation;

@RunWith(MockitoJUnitRunner.class)
public class EstacionamientoServiceImplUnitTest {
	
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
		
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(any(TipoVehiculo.class))).thenReturn(0L);
		when(configuracionesIngresoRepository.findAll()).thenReturn(configuracionesIngresoList);
		//when(estacionamientoValidation.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), any(TipoVehiculo.class), configuracionesIngresoList)).thenReturn(Boolean.TRUE);
		
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);	
	}

}
