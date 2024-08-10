package com.br.luisvanique.academia.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/login")
	            .allowedOrigins("*") // Permite todas as origens
	            .allowedMethods("GET", "POST", "OPTIONS");

	    registry.addMapping("/instrutor")
	            .allowedOrigins("*") // Permite todas as origens
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
}
