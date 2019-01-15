package com.ceiba.estacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.estacionamiento.domain.ConfiguracionesCilindraje;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

@Repository
public interface ConfiguracionesCilindrajeRepository extends JpaRepository<ConfiguracionesCilindraje, Long>{
	
	@Query("SELECT cc FROM ConfiguracionesCilindraje cc WHERE cc.tipoVehiculo = :tipoVehiculo ")
	public List<ConfiguracionesCilindraje> findByTipoVehiculo(@Param("tipoVehiculo") TipoVehiculo tipoVehiculo);
	
}
