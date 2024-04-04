package dev.tiltrikt.tetravex.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "tetravex",
        version = "1"
    )
)
@Configuration
public class OpenApiConfiguration {
}
