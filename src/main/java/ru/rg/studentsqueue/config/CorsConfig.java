package ru.rg.studentsqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for enabling Cross-Origin Resource Sharing (CORS) for the API.
 */
@Configuration
public class CorsConfig {
    /**
     * Returns a new WebMvcConfigurer object with CORS configuration for the API.
     *
     * @return a new WebMvcConfigurer object with CORS configuration for the API
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Adds CORS mappings to the given CorsRegistry object.
             *
             * @param registry the CorsRegistry object to add CORS mappings to
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/**")
                        .allowedOrigins("http://localhost")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(false)
                        .maxAge(0);
            }
        };
    }
}