package com.microservicios.app.peliculas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.peliculas.models.entity.Pelicula;
import com.microservicios.app.peliculas.models.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	private PeliculaRepository repository;
	
	@Override
	@Transactional(readOnly =  true)
	public Iterable<Pelicula> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly =  true)
	public Optional<Pelicula> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Pelicula save(Pelicula pelicula) {
		// TODO Auto-generated method stub
		return repository.save(pelicula);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
