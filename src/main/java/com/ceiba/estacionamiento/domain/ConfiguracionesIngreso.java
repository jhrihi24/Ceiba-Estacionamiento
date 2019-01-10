package com.ceiba.estacionamiento.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ceiba.estacionamiento.enums.TipoValidacion;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CONFIGURACIONES_INGRESO")
public class ConfiguracionesIngreso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private TipoVehiculo tipoVehiculo;
	
	private TipoValidacion tipoValidacion;
	
	private String valor;
	
	private String prohibicionDias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getProhibicionDias() {
		return prohibicionDias;
	}

	public void setProhibicionDias(String prohibicionDias) {
		this.prohibicionDias = prohibicionDias;
	}

	public TipoValidacion getTipoValidacion() {
		return tipoValidacion;
	}

	public void setTipoValidacion(TipoValidacion tipoValidacion) {
		this.tipoValidacion = tipoValidacion;
	}
}
