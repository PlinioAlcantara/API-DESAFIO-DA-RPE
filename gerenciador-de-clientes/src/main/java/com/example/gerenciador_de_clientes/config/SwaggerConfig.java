package com.example.gerenciador_de_clientes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Gerenciador de Clientes")
                        .description("Documentação da API do desafio técnico RPE")
                        .version("1.0"));
    }
}
