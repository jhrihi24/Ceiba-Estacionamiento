package com.ceiba.estacionamiento.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.dto.RespuestaDTO;
import com.ceiba.estacionamiento.enums.DiasSemana;
import com.ceiba.estacionamiento.enums.TipoValidacion;
import com.ceiba.estacionamiento.util.EstacionamientoUtils;

@Component
public class EstacionamientoValidation {
	
	public RespuestaDTO<String> validarCamposRegistroVehiculo(RegistrarVehiculoDTO registrarVehiculo){
		RespuestaDTO<String> respuesta= new RespuestaDTO<>();
		
		if(registrarVehiculo.getPlaca().isEmpty()){
			respuesta.setMensaje("Debe ingresar una placa");
			respuesta.setSuccess(Boolean.FALSE);
		}		
		
		return respuesta;
	}
	
	public Boolean validarDiasIngresoVehiculo(String placa, List<ConfiguracionesIngreso> configuracionesIngresoList){
		String diaActual= new SimpleDateFormat("EEEE", Locale.US).format(new Date());
		for(ConfiguracionesIngreso configuracionesIngreso: configuracionesIngresoList){			
			String[] dias= configuracionesIngreso.getProhibicionDias().split("-");
			for(String d: dias){
				if(DiasSemana.getDescripcion(d).equals(diaActual) && 
					(validarTipoValidacionInicial(configuracionesIngreso.getTipoValidacion(), placa, configuracionesIngreso.getValor()) || 
							validarTipoValidacionFInal(configuracionesIngreso.getTipoValidacion(), placa, configuracionesIngreso.getValor()))){
						return Boolean.FALSE;
				}
			}			
		}
		
		return Boolean.TRUE;
	}
	
	private Boolean validarTipoValidacionInicial(TipoValidacion tipoValidacion, String placa, String valorConfiguracionesIngreso){
		return tipoValidacion==TipoValidacion.INICIO && EstacionamientoUtils.buscarCadenaInicio(placa, valorConfiguracionesIngreso);
	}
	
	private Boolean validarTipoValidacionFInal(TipoValidacion tipoValidacion, String placa, String valorConfiguracionesIngreso){
		return tipoValidacion==TipoValidacion.FINAL && EstacionamientoUtils.bucarCadenaFinal(placa, valorConfiguracionesIngreso);
	}
		
}
