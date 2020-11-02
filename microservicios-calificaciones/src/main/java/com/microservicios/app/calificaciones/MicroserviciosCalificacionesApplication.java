package com.microservicios.app.calificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosCalificacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCalificacionesApplication.class, args);
	}

}
