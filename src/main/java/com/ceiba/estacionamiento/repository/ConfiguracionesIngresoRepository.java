package com.ceiba.estacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

@Repository
public interface ConfiguracionesIngresoRepository extends JpaRepository<ConfiguracionesIngreso, Long>{
	
	@Query("SELECT ci FROM ConfiguracionesIngreso ci WHERE ci.tipoVehiculo = :tipoVehiculo")
	public List<ConfiguracionesIngreso> findByTipoVehiculo(@Param("tipoVehiculo") TipoVehiculo tipoVehiculo);
	
}
