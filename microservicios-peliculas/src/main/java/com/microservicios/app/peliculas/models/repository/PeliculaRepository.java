package com.microservicios.app.peliculas.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicios.app.peliculas.models.entity.Pelicula;



public interface PeliculaRepository extends CrudRepository<Pelicula, Long> {

	
}
