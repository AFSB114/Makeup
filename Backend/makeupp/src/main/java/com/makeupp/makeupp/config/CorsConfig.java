package com.makeupp.makeupp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        System.out.println("CORS Config Loaded!"); // Debug

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:5500/");
        // config.addAllowedOriginPattern("*");

        config.addAllowedOriginPattern("*"); // Permitir cualquier origen con credenciales
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
        config.setAllowedHeaders(Arrays.asList("*")); // Permitir todos los headers
        config.setAllowCredentials(true); // Permitir envío de credenciales

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
