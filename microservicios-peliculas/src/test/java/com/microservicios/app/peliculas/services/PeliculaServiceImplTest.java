package com.microservicios.app.peliculas.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservicios.app.peliculas.models.entity.Pelicula;
import com.microservicios.app.peliculas.models.repository.PeliculaRepository;

@RunWith(SpringRunner.class)
class PeliculaServiceImplTest {

	@TestConfiguration
	static class peliculaServiceImplTestContextConfiguration {

		@Bean
		public PeliculaService peliculaService() {
			return new PeliculaServiceImpl();
		}
	}

	@Autowired
	private PeliculaService peliculaService;

	@MockBean
	private PeliculaRepository peliculaRepository;

	@Before
	public void setUp() {

		List<Pelicula> peliculas = new ArrayList<Pelicula>();

		for (long i = 1; i < 4; i++) {
			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(i);
			pelicula1.setNombre("Pelicula 1");
			pelicula1.setDescripcion("Descripción");
			peliculas.add(pelicula1);
		}
		
		Mockito.when(peliculaRepository.findById(1L)).thenReturn(Optional.of(peliculas.get(1)));
		Mockito.when(peliculaRepository.findAll()).thenReturn(peliculas);
		Mockito.when(peliculaRepository.findById(-99L)).thenReturn(Optional.empty());
	}

	@Test
	void testFindAll() {
		Iterable<Pelicula> peliculas = peliculaService.findAll();
		assertEquals(3, Lists.newArrayList(peliculas).size());
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

}
