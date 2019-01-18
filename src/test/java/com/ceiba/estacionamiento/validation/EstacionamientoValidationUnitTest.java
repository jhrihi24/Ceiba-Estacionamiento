package com.ceiba.estacionamiento.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ceiba.estacionamiento.dataBuilder.ConfiguracionesIngresoDataBuilder;
import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.dto.RespuestaDTO;
import com.ceiba.estacionamiento.enums.TipoValidacion;
import com.ceiba.estacionamiento.util.EstacionamientoUtils;


@RunWith(PowerMockRunner.class)
@PrepareForTest(EstacionamientoUtils.class)
public class EstacionamientoValidationUnitTest {
	
	@InjectMocks
	private EstacionamientoValidation estacionamientoValidation;
		
	private RegistrarVehiculoDTO registrarVehiculo;
	private ConfiguracionesIngreso configuracionesIngreso;
	private List<ConfiguracionesIngreso> configuracionesIngresoList;
	
	@Before
	public void init(){
		configuracionesIngresoList= new ArrayList<>();
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("").build();
	}
	
	@Test
	public void validarCamposRegistroVehiculo(){
		RespuestaDTO<String> respuesta= estacionamientoValidation.validarCamposRegistroVehiculo(registrarVehiculo);
		assertEquals("Debe ingresar una placa", respuesta.getMensaje());
	}
	
	@Test
	public void validarIngresoVehiculoTipoValidacionInicioFALSE(){
		String prefijoDiaActual= asignacionPrefijoDia(new SimpleDateFormat("EEEE", Locale.US).format(new Date()));
		configuracionesIngreso= new ConfiguracionesIngresoDataBuilder().withProhibicionDias(prefijoDiaActual).build();
		configuracionesIngresoList.add(configuracionesIngreso);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.buscarCadenaInicio("ABC", configuracionesIngreso.getValor())).thenReturn(Boolean.TRUE);
		assertFalse(estacionamientoValidation.validarDiasIngresoVehiculo("ABC", configuracionesIngresoList));
	}
	
	@Test
	public void validarIngresoVehiculoTipoValidacionFinalFALSE(){
		String prefijoDiaActual= asignacionPrefijoDia(new SimpleDateFormat("EEEE", Locale.US).format(new Date()));
		configuracionesIngreso= new ConfiguracionesIngresoDataBuilder()
				.withProhibicionDias(prefijoDiaActual)
				.withTipoValidacion(TipoValidacion.FINAL).build();
		configuracionesIngresoList.add(configuracionesIngreso);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.bucarCadenaFinal("CBA", configuracionesIngreso.getValor())).thenReturn(Boolean.TRUE);
		assertFalse(estacionamientoValidation.validarDiasIngresoVehiculo("CBA", configuracionesIngresoList));
	}
	
	@Test
	public void validarIngresoVehiculoTipoValidacionDiaSemanaDiferenteTRUE(){
		String diaActual= "L";
		if(new SimpleDateFormat("EEEE", Locale.US).format(new Date()).equals("Monday"))
			diaActual="M";
		configuracionesIngreso= new ConfiguracionesIngresoDataBuilder().withProhibicionDias(diaActual).build();
		configuracionesIngresoList.add(configuracionesIngreso);
		assertTrue(estacionamientoValidation.validarDiasIngresoVehiculo("CBA", configuracionesIngresoList));
	}
	
	@Test
	public void validarIngresoVehiculoTipoValidacionInicioTRUE(){
		String prefijoDiaActual= asignacionPrefijoDia(new SimpleDateFormat("EEEE", Locale.US).format(new Date()));
		configuracionesIngreso= new ConfiguracionesIngresoDataBuilder()
				.withProhibicionDias(prefijoDiaActual)
				.build();
		configuracionesIngresoList.add(configuracionesIngreso);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.buscarCadenaInicio("CBA", configuracionesIngreso.getValor())).thenReturn(Boolean.FALSE);
		assertTrue(estacionamientoValidation.validarDiasIngresoVehiculo("CBA", configuracionesIngresoList));
	}
	
	@Test
	public void validarIngresoVehiculoTipoValidacionFinalTRUE(){
		String prefijoDiaActual= asignacionPrefijoDia(new SimpleDateFormat("EEEE", Locale.US).format(new Date()));
		configuracionesIngreso= new ConfiguracionesIngresoDataBuilder()
				.withProhibicionDias(prefijoDiaActual)
				.withTipoValidacion(TipoValidacion.FINAL)
				.build();
		configuracionesIngresoList.add(configuracionesIngreso);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.bucarCadenaFinal("CBA", configuracionesIngreso.getValor())).thenReturn(Boolean.FALSE);
		assertTrue(estacionamientoValidation.validarDiasIngresoVehiculo("CBA", configuracionesIngresoList));
	}	
	
	private String asignacionPrefijoDia(String dia){
		String prefijo= "";
		switch (dia) {
	        case "Monday":
	        	prefijo = "L";
	            break;
	        case "Tuesday":
	        	prefijo = "M";
	            break;
	        case "Wednesday":
	        	prefijo = "Mi";
	            break;
	        case "Thursday":
	        	prefijo = "J";
	            break;
	        case "Friday":
	        	prefijo = "V";
	            break;
	        case "Saturday":
	        	prefijo = "S";
	            break;
	        case "Sunday":
	        	prefijo = "D";
	            break;
	        default:
	            throw new IllegalArgumentException("nombre invalido del dia de la semana");
		}
		return prefijo;
	}
	
}
