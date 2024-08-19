package com.picpaysimplificado.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

//Configuração do Token JWT no Swagger UI

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Desafio PicPay Simplificado", version = "v1")
)
public class OpenAPIConfig {
}

