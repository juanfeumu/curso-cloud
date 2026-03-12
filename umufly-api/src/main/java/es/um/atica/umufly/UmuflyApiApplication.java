package es.um.atica.umufly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition( info = @Info( title = "API de Vuelos", version = "1.0", description = "API para gestión de vuelos" ) )
@SpringBootApplication
public class UmuflyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmuflyApiApplication.class, args);
	}

}
