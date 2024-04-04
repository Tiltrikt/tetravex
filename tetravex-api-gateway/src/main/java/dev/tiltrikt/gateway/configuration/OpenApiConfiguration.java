package dev.tiltrikt.gateway.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
@SuppressWarnings("DataFlowIssue")
public class OpenApiConfiguration {

  public OpenApiConfiguration(@NotNull SwaggerUiConfigParameters swaggerUiConfigParameters,
                              @NotNull RouteDefinitionLocator locator) {
    List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
    definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
            .forEach(routeDefinition -> {
              String name = routeDefinition.getId().replaceAll("-service", "");
              swaggerUiConfigParameters.addGroup(name);
            });
  }
}
