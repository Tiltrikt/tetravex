package dev.tiltrikt.tetravex.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfiguration {

  @Bean
  public RestTemplate getRestTemplate(@NotNull List<ClientHttpRequestInterceptor> interceptors,
                                      @NotNull ResponseErrorHandler handler) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }
}
