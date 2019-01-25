package com.ceiba.estacionamiento.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.dto.RegistrarVehiculoDTO;
import com.ceiba.estacionamiento.enums.DiasSemana;
import com.ceiba.estacionamiento.enums.TipoValidacion;
import com.ceiba.estacionamiento.enums.TipoVehiculo;
import com.ceiba.estacionamiento.exception.EstacionamientoException;
import com.ceiba.estacionamiento.util.EstacionamientoUtils;

@Component
public class Vigilante {
	
	@Value("${maximo.motos}")
	private Integer maximoMotos;
	
	@Value("${maximo.carros}")
	private Integer maximoCarros;
	
	public void validarRegistroVehiculo(RegistrarVehiculoDTO registrarVehiculo, TipoVehiculo tipoVehiculo, 
			Long countPlacaActiva, Long vehiculosIngresados) throws EstacionamientoException{		
		
		if(registrarVehiculo.getPlaca().isEmpty())
			throw new EstacionamientoException("Debe ingresar una placa");
		
		if(!EstacionamientoUtils.validarPlacaValida(registrarVehiculo.getPlaca(), tipoVehiculo))
			throw new EstacionamientoException("La placa ingresada no cuenta con el formato valido.");
		
		if(countPlacaActiva>0)
			throw new EstacionamientoException("Ya se encuentra un veh\u00EDculo con esa placa en el estacionamiento.");
		
		if((tipoVehiculo.equals(TipoVehiculo.CARRO) && vehiculosIngresados.intValue()==maximoCarros) || 
				(tipoVehiculo.equals(TipoVehiculo.MOTO) && vehiculosIngresados.intValue()==maximoMotos))
			throw new EstacionamientoException("No hay cupo para el veh\u00EDculo.");		
			
	}
	
	public void validarDiasIngresoVehiculo(String placa, List<ConfiguracionesIngreso> configuracionesIngresoList) throws EstacionamientoException{
		String diaActual= new SimpleDateFormat("EEEE", Locale.US).format(new Date());
		for(ConfiguracionesIngreso configuracionesIngreso: configuracionesIngresoList){			
			String[] dias= configuracionesIngreso.getProhibicionDias().split("-");
			for(String d: dias){
				if(DiasSemana.getDescripcion(d).equals(diaActual) && 
					(validarTipoValidacionInicial(configuracionesIngreso.getTipoValidacion(), placa, configuracionesIngreso.getValor()) || 
							validarTipoValidacionFInal(configuracionesIngreso.getTipoValidacion(), placa, configuracionesIngreso.getValor()))){
						throw new EstacionamientoException("El veh\u00EDculo no esta autorizado para ingresar el dia de hoy.");
				}
			}			
		}
	}
	
	private Boolean validarTipoValidacionInicial(TipoValidacion tipoValidacion, String placa, String valorConfiguracionesIngreso){
		return tipoValidacion==TipoValidacion.INICIO && EstacionamientoUtils.buscarCadenaInicio(placa, valorConfiguracionesIngreso);
	}
	
	private Boolean validarTipoValidacionFInal(TipoValidacion tipoValidacion, String placa, String valorConfiguracionesIngreso){
		return tipoValidacion==TipoValidacion.FINAL && EstacionamientoUtils.bucarCadenaFinal(placa, valorConfiguracionesIngreso);
	}
		
}
