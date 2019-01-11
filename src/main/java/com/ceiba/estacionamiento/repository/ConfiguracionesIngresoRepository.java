package com.ceiba.estacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.estacionamiento.domain.ConfiguracionesIngreso;

@Repository
public interface ConfiguracionesIngresoRepository extends JpaRepository<ConfiguracionesIngreso, Long>{

}
