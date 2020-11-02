package com.microservicios.app.calificaciones.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.calificaciones.models.entity.Calificacion;
import com.microservicios.app.calificaciones.models.repository.CalificacionRepository;

@Service
public class CalificacionServiceImpl implements CalificacionService {

	
	@Autowired
	private CalificacionRepository repository;
	
	@Override
	@Transactional(readOnly =  true)
	public Iterable<Calificacion> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly =  true)
	public Optional<Calificacion> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Calificacion save(Calificacion calificacion) {
		// TODO Auto-generated method stub
		return repository.save(calificacion);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
