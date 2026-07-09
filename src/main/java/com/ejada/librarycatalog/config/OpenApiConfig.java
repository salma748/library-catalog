package com.ejada.librarycatalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryCatalogOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Catalog API")
                        .version("1.0")
                        .description("REST API for managing authors and books"));
    }
}