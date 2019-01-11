package com.ceiba.estacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.estacionamiento.domain.Servicios;
import com.ceiba.estacionamiento.enums.TipoVehiculo;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long>{
	
	@Query("SELECT COUNT(s.id) FROM Servicios s WHERE s.tipoVehiculo = :tipoVehiculo ")
	Long countByVehiculoIngresado(@Param("tipoVehiculo") TipoVehiculo tipoVehiculo);
	
	@Query("SELECT COUNT(s.id) FROM Servicios s WHERE s.placa = :placa AND s.fechaHoraSalida IS NULL AND s.cobrado IS NULL")
	Long countByPlacaSinSalir(@Param("placa") String placa);
	
}
