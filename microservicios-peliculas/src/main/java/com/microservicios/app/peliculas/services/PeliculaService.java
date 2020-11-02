package com.microservicios.app.peliculas.services;

import java.util.Optional;

import com.microservicios.app.peliculas.models.entity.Pelicula;

public interface PeliculaService {

	public Iterable<Pelicula> findAll();
	
	public Optional<Pelicula> findById(Long id);
	
	public Pelicula save(Pelicula pelicula);
	
	public void deleteById(Long id);
	
	
	
}
