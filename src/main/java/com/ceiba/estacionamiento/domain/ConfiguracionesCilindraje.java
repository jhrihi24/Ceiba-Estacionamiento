package com.ceiba.estacionamiento.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ceiba.estacionamiento.enums.TipoVehiculo;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CONFIGURACIONES_CILINDRAJE")
public class ConfiguracionesCilindraje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private TipoVehiculo tipoVehiculo;
	
	private Integer cilindraje;
	
	private BigDecimal cobroAdicional;

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

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public BigDecimal getCobroAdicional() {
		return cobroAdicional;
	}

	public void setCobroAdicional(BigDecimal cobroAdicional) {
		this.cobroAdicional = cobroAdicional;
	}	
}
