package com.microservicios.app.calificaciones.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicios.app.calificaciones.models.entity.Calificacion;


public interface CalificacionRepository extends CrudRepository<Calificacion, Long>  {

}
