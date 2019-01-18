package com.ceiba.estacionamiento.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.ceiba.estacionamiento.dataBuilder.ConfiguracionesCilindrajeDataBuilder;
import com.ceiba.estacionamiento.dataBuilder.PreciosDataBuilder;
import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.dataBuilder.ServiciosDataBuilder;
import com.ceiba.estacionamiento.domain.ConfiguracionesCilindraje;
import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.domain.Precios;
import com.ceiba.estacionamiento.domain.Servicios;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.enums.TipoCobro;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.repository.ConfiguracionesCilindrajeRepository;
import com.ceiba.estacionamiento.repository.ConfiguracionesIngresoRepository;
import com.ceiba.estacionamiento.repository.PreciosRepository;
import com.ceiba.estacionamiento.repository.ServiciosRepository;
import com.ceiba.estacionamiento.trm.TRMWebService;
import com.ceiba.estacionamiento.util.EstacionamientoUtils;
import com.ceiba.estacionamiento.validation.EstacionamientoValidation;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EstacionamientoUtils.class)
public class EstacionamientoServiceImplUnitTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Mock
	private ServiciosRepository serviciosRepository;
	
	@Mock
	private ConfiguracionesIngresoRepository configuracionesIngresoRepository;
	
	@Mock
	private PreciosRepository preciosRepository;
	
	@Mock
	private ConfiguracionesCilindrajeRepository configuracionesCilindrajeRepository;
	
	@Mock
	private EstacionamientoValidation estacionamientoValidation;
	
	/*@Mock
	private TRMWebService trmWebService;*/
	
	@InjectMocks
	private EstacionamientoServiceImpl estacionamientoServiceImpl;
	
	private List<Servicios> serviciosList;
	
	@Before
	public void init(){
		serviciosList= new ArrayList<>();
		serviciosList.add(new ServiciosDataBuilder().build());
	}
	
	@Test
	public void getServiciosActivosBusquedaPlaca(){
		when(serviciosRepository.findByServiciosActivosPlaca("CB")).thenReturn(serviciosList);
		assertEquals(1, estacionamientoServiceImpl.getServiciosActivos("CB").size());
	}
}
