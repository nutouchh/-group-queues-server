package com.example.studentsqueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
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
