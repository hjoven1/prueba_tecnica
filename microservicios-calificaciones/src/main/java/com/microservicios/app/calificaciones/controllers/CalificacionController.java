package com.microservicios.app.calificaciones.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.app.calificaciones.models.entity.Calificacion;
import com.microservicios.app.calificaciones.services.CalificacionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class CalificacionController {

	@Autowired
	private CalificacionService service;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {

		Optional<Calificacion> o = service.findById(id);

		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(o.get());

	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Calificacion calificacion) {

		Calificacion calificacionDb = service.save(calificacion);

		return ResponseEntity.status(HttpStatus.CREATED).body(calificacionDb);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Calificacion calificacion, @PathVariable Long id) {

		Optional<Calificacion> o = service.findById(id);

		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Calificacion calificacionDb = o.get();
		calificacionDb.setPunto(calificacion.getPunto());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(calificacionDb));
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
