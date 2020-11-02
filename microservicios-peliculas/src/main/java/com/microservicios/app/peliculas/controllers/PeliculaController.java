package com.microservicios.app.peliculas.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservicios.app.peliculas.models.entity.Pelicula;
import com.microservicios.app.peliculas.services.PeliculaService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class PeliculaController {

	@Autowired
	private PeliculaService service;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {

		Optional<Pelicula> o = service.findById(id);

		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(o.get());

	}
	
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		Optional<Pelicula> o = service.findById(id);

		if (o.isEmpty() || o.get().getFoto()==null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}

	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody Pelicula pelicula, BindingResult result ) {

Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			
			List<String> errors = new ArrayList<>();
			for(FieldError err:  result.getFieldErrors()) {
				errors.add("El campo '"+ err.getField() +"' "+ err.getDefaultMessage());
			}
						
			response.put("errors",errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Pelicula peliculaDb = service.save(pelicula);

		return ResponseEntity.status(HttpStatus.CREATED).body(peliculaDb);
	}
	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Pelicula pelicula, BindingResult result, @RequestParam MultipartFile archivo ) throws IOException {
		
		if(!archivo.isEmpty()) {
			pelicula.setFoto(archivo.getBytes());
		}
		return this.crear(pelicula, result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Pelicula pelicula, @PathVariable Long id) {

		Optional<Pelicula> o = service.findById(id);

		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Pelicula peliculaDb = o.get();
		peliculaDb.setNombre(pelicula.getNombre());
		peliculaDb.setDescripcion(pelicula.getDescripcion());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(peliculaDb));
		
	}
	
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Pelicula pelicula, BindingResult result, @PathVariable Long id, 
			@RequestParam MultipartFile archivo) throws IOException {

		Optional<Pelicula> o = service.findById(id);
		
		

		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Pelicula peliculaDb = o.get();
		peliculaDb.setNombre(pelicula.getNombre());
		peliculaDb.setDescripcion(pelicula.getDescripcion());
		
		if(!archivo.isEmpty()) {
			peliculaDb.setFoto(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(peliculaDb));
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
