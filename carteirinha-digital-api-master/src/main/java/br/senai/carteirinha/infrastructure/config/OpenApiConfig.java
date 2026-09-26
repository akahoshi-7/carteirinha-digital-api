package br.senai.carteirinha.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI carteirinhaOpenApi() {
        return new OpenAPI().info(new Info()
            .title("Carteirinha Digital API")
            .version("2.1.0")
            .description("Etapa atual: autenticação do aplicativo Android."));
    }
}
