package com.example.javaxml_bookeeper;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allows all paths in the API
            .allowedOrigins("http://localhost:3000")  // React frontend URL
            .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed HTTP methods
            .allowedHeaders("*");  // Allowed headers
    }

}
