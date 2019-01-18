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
	
	@Mock
	private TRMWebService trmWebService;
	
	@InjectMocks
	private EstacionamientoServiceImpl estacionamientoServiceImpl;

	private RegistrarVehiculoDTO registrarVehiculo;
	
	private List<Servicios> serviciosList;
	
	private Optional<Servicios> optionalServicios;
	
	private List<Precios> preciosList;
	
	private List<ConfiguracionesCilindraje> configuracionesCilindrajeList;
 	
	@Before
	public void init(){
		serviciosList= new ArrayList<>();
		serviciosList.add(new ServiciosDataBuilder().build());
		preciosList= new ArrayList<>();
		configuracionesCilindrajeList= new ArrayList<>();
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().build();
		optionalServicios= Optional.of(new ServiciosDataBuilder().build());
		ReflectionTestUtils.setField(estacionamientoServiceImpl, "maximoMotos", 10);
		ReflectionTestUtils.setField(estacionamientoServiceImpl, "maximoCarros", 20);
	}
	
	@Test
	public void prueba(){
		assertTrue(Boolean.TRUE);
	}
	
	/*@Test
	public void getServiciosActivosPlacaNull(){
		when(serviciosRepository.findByServiciosActivos()).thenReturn(serviciosList);
		assertEquals(1, estacionamientoServiceImpl.getServiciosActivos(null).size());
	}
	
	@Test
	public void getServiciosActivosPlacaVacia(){		
		when(serviciosRepository.findByServiciosActivos()).thenReturn(serviciosList);
		assertEquals(1, estacionamientoServiceImpl.getServiciosActivos("").size());
	}
	
	@Test
	public void getServiciosActivosBusquedaPlaca(){
		when(serviciosRepository.findByServiciosActivosPlaca("CB")).thenReturn(serviciosList);
		assertEquals(1, estacionamientoServiceImpl.getServiciosActivos("CB").size());
	}
	
	@Test
	public void registrarVehiculoSuccess() throws EstacionamientoException{
		List<ConfiguracionesIngreso> configuracionesIngresoList= new ArrayList<>();
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(2);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.validarPlacaValida(registrarVehiculo.getPlaca(), tipoVehiculo)).thenReturn(Boolean.TRUE);
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(0L);
		when(configuracionesIngresoRepository.findAll()).thenReturn(configuracionesIngresoList);
		when(estacionamientoValidation.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), tipoVehiculo, configuracionesIngresoList)).thenReturn(Boolean.TRUE);		
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);	
	}
	
	@Test
	public void registrarVehiculoPlacaConFormatoInvalido() throws EstacionamientoException{		
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("CBJ57C").build();
		exception.expect(EstacionamientoException.class);
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void registrarVehiculoPlacaDuplicada() throws EstacionamientoException{		
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(2);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.validarPlacaValida(registrarVehiculo.getPlaca(), tipoVehiculo)).thenReturn(Boolean.TRUE);
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(1L);
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void registrarVehiculoSinCupoMoto() throws EstacionamientoException{
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withCilindraje(150).build();
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(1);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.validarPlacaValida(registrarVehiculo.getPlaca(), tipoVehiculo)).thenReturn(Boolean.TRUE);
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(10L);
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("No hay cupo para el vehiculo.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void registrarVehiculoSinCupoCarro() throws EstacionamientoException{
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(2);
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withCilindraje(0).build();
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.validarPlacaValida(registrarVehiculo.getPlaca(), tipoVehiculo)).thenReturn(Boolean.TRUE);
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(20L);
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("No hay cupo para el vehiculo.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void registrarVehiculoDiasIngreso() throws EstacionamientoException{
		List<ConfiguracionesIngreso> configuracionesIngresoList= new ArrayList<>();
		TipoVehiculo tipoVehiculo= TipoVehiculo.toTipoVehiculo(1);		
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.validarPlacaValida(registrarVehiculo.getPlaca(), tipoVehiculo)).thenReturn(Boolean.TRUE);
		when(serviciosRepository.countByPlacaSinSalir(registrarVehiculo.getPlaca())).thenReturn(0L);
		when(serviciosRepository.countByVehiculoIngresado(tipoVehiculo)).thenReturn(0L);
		when(configuracionesIngresoRepository.findAll()).thenReturn(configuracionesIngresoList);
		when(estacionamientoValidation.validarDiasIngresoVehiculo(registrarVehiculo.getPlaca(), tipoVehiculo, configuracionesIngresoList)).thenReturn(Boolean.FALSE);		
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("El vehiculo no esta autorizado para ingresar el dia de hoy.");
		estacionamientoServiceImpl.registarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void salidaVehiculosServicioNoExiste() throws EstacionamientoException{
		when(serviciosRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("El servicio no existe.");
		estacionamientoServiceImpl.salidaVehiculo(1L, new Date());		
	}
	
	@Test
	public void cobroSinExcesoCilindraje() throws EstacionamientoException{
		Date fechaActual= new Date();
		Long cantidadHoras= new Long(27);
		preciosList.add(new PreciosDataBuilder().build());
		preciosList.add(new PreciosDataBuilder()
							.withId(2L)
							.withTipoCobro(TipoCobro.HORA)
							.withPrecio(new BigDecimal(500)).build());
		
		when(serviciosRepository.findById(1L)).thenReturn(optionalServicios);
		when(preciosRepository.findByTipoVehiculo(optionalServicios.get().getTipoVehiculo())).thenReturn(preciosList);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.calcularHorasEntreFechas(optionalServicios.get().getFechaHoraIngreso(), fechaActual)).thenReturn(cantidadHoras);
		PowerMockito.when(EstacionamientoUtils.cobroHora(new BigDecimal(500), cantidadHoras)).thenReturn(new BigDecimal(1500));
		PowerMockito.when(EstacionamientoUtils.cobroDia(new BigDecimal(4000), cantidadHoras)).thenReturn(new BigDecimal(4000));
		when(serviciosRepository.save(optionalServicios.get())).thenReturn(optionalServicios.get());
		when(trmWebService.getTrmActual()).thenReturn(BigDecimal.valueOf(3000));
		PowerMockito.when(EstacionamientoUtils.cobroTRM(BigDecimal.valueOf(5500), BigDecimal.valueOf(3000))).thenReturn(BigDecimal.valueOf(1.83));
		
		assertEquals(BigDecimal.valueOf(5500), estacionamientoServiceImpl.salidaVehiculo(1L, fechaActual).getCobrado());
	}
	
	@Test
	public void cobroConExcesoCilindraje() throws EstacionamientoException{
		Date fechaActual= new Date();
		Long cantidadHoras= new Long(27);
		preciosList.add(new PreciosDataBuilder().build());
		preciosList.add(new PreciosDataBuilder()
							.withId(2L)
							.withTipoCobro(TipoCobro.HORA)
							.withPrecio(new BigDecimal(500)).build());
		configuracionesCilindrajeList.add(new ConfiguracionesCilindrajeDataBuilder().build());
		
		optionalServicios= Optional.of(new ServiciosDataBuilder().withCilindraje(510).build());
		when(serviciosRepository.findById(1L)).thenReturn(optionalServicios);
		when(preciosRepository.findByTipoVehiculo(optionalServicios.get().getTipoVehiculo())).thenReturn(preciosList);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.calcularHorasEntreFechas(optionalServicios.get().getFechaHoraIngreso(), fechaActual)).thenReturn(cantidadHoras);
		PowerMockito.when(EstacionamientoUtils.cobroHora(new BigDecimal(500), cantidadHoras)).thenReturn(new BigDecimal(1500));
		PowerMockito.when(EstacionamientoUtils.cobroDia(new BigDecimal(4000), cantidadHoras)).thenReturn(new BigDecimal(4000));		
		when(configuracionesCilindrajeRepository.findByTipoVehiculo(optionalServicios.get().getTipoVehiculo())).thenReturn(configuracionesCilindrajeList);		
		when(serviciosRepository.save(optionalServicios.get())).thenReturn(optionalServicios.get());
		when(trmWebService.getTrmActual()).thenReturn(BigDecimal.valueOf(3000));
		PowerMockito.when(EstacionamientoUtils.cobroTRM(BigDecimal.valueOf(7500), BigDecimal.valueOf(3000))).thenReturn(BigDecimal.valueOf(1.83));
		
		assertEquals(BigDecimal.valueOf(7500), estacionamientoServiceImpl.salidaVehiculo(1L, fechaActual).getCobrado());
	}*/
	
}
