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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.ceiba.estacionamiento.dataBuilder.ConfiguracionesIngresoDataBuilder;
import com.ceiba.estacionamiento.dataBuilder.RegistrarVehiculoDTODataBuilder;
import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.dto.RespuestaDTO;
import com.ceiba.estacionamiento.enums.TipoValidacion;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.util.EstacionamientoUtils;
import com.ceiba.estacionamiento.validation.Vigilante;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EstacionamientoUtils.class)
public class VigilanteUnitTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@InjectMocks
	private Vigilante vigilante;
		
	private RegistrarVehiculoDTO registrarVehiculo;
	private ConfiguracionesIngreso configuracionesIngreso;
	private List<ConfiguracionesIngreso> configuracionesIngresoList;
	private TipoVehiculo tipoVehiculo;
	
	@Before
	public void init(){
		configuracionesIngresoList= new ArrayList<>();
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("").build();
		ReflectionTestUtils.setField(vigilante, "maximoMotos", 10);
		ReflectionTestUtils.setField(vigilante, "maximoCarros", 20);
		tipoVehiculo= TipoVehiculo.toTipoVehiculo(2);
	}
	
	@Test
	public void validarRegistroVehiculoIngresarPlaca() throws EstacionamientoException{
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("Debe ingresar una placa");
		vigilante.validarRegistroVehiculo(registrarVehiculo, tipoVehiculo, 0L, 0L);		
	}
	
	@Test
	public void validarIngresoVehiculoTipoValidacionInicioFALSE() throws EstacionamientoException{
		String prefijoDiaActual= asignacionPrefijoDia(new SimpleDateFormat("EEEE", Locale.US).format(new Date()));
		configuracionesIngreso= new ConfiguracionesIngresoDataBuilder().withProhibicionDias(prefijoDiaActual).build();
		configuracionesIngresoList.add(configuracionesIngreso);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.buscarCadenaInicio("ABC", configuracionesIngreso.getValor())).thenReturn(Boolean.TRUE);
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("El veh\u00EDculo no esta autorizado para ingresar el dia de hoy.");
		vigilante.validarDiasIngresoVehiculo("ABC", configuracionesIngresoList);
	}
	
	@Test
	public void validarIngresoVehiculoTipoValidacionFinalFALSE() throws EstacionamientoException{
		String prefijoDiaActual= asignacionPrefijoDia(new SimpleDateFormat("EEEE", Locale.US).format(new Date()));
		configuracionesIngreso= new ConfiguracionesIngresoDataBuilder()
				.withProhibicionDias(prefijoDiaActual)
				.withTipoValidacion(TipoValidacion.FINAL).build();
		configuracionesIngresoList.add(configuracionesIngreso);
		PowerMockito.mockStatic(EstacionamientoUtils.class);
		PowerMockito.when(EstacionamientoUtils.bucarCadenaFinal("CBA", configuracionesIngreso.getValor())).thenReturn(Boolean.TRUE);
		exception.expect(EstacionamientoException.class);
		exception.expectMessage("El veh\u00EDculo no esta autorizado para ingresar el dia de hoy.");
		vigilante.validarDiasIngresoVehiculo("CBA", configuracionesIngresoList);
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
