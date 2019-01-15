package com.ceiba.estacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.estacionamiento.domain.Precios;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

@Repository
public interface PreciosRepository extends JpaRepository<Precios, Long>{
	
	@Query("SELECT p FROM Precios p WHERE p.tipoVehiculo= :tipoVehiculo ")
	public List<Precios> findByTipoVehiculo(@Param("tipoVehiculo") TipoVehiculo tipoVehiculo);	
	
}
