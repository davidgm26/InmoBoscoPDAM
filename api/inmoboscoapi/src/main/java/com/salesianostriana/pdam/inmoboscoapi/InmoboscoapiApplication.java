package com.salesianostriana.pdam.inmoboscoapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(description = "API para la gestión de una inmobiliaria, forma parte del proyecto DAM",
		version = "1.0",
		contact = @Contact(email = "davidgama260402@gmail.com", name = "David"),
		license = @License(name = "Creative commons"),
		title = "InmoBoscoAPI"
)
)
public class InmoboscoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InmoboscoapiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		String[] allowDomains = new String[2];
		allowDomains[0] = "http://localhost:4200";
		allowDomains[1] = "http://localhost:8080";

		System.out.println("CORS configuration....");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowedOrigins(allowDomains);
			}
		};
	}

}
