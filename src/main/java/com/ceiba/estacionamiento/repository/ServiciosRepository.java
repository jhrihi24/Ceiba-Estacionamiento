package com.ceiba.estacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.estacionamiento.domain.Servicios;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long>{

}
