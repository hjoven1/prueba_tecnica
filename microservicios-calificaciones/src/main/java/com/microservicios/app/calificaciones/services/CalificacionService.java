package com.microservicios.app.calificaciones.services;

import java.util.Optional;

import com.microservicios.app.calificaciones.models.entity.Calificacion;

public interface CalificacionService {

public Iterable<Calificacion> findAll();
	
	public Optional<Calificacion> findById(Long id);
	
	public Calificacion save(Calificacion calificacion);
	
	public void deleteById(Long id);
}
