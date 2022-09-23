package com.example.demowithtests.util.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Employee CRUD API")
                        .description(" Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .version("v0.0.1").contact(new Contact()
                                .email("gaydabura.d@icloud.com")
                                .name("Dmitry Gaydabura")));
    }
}
