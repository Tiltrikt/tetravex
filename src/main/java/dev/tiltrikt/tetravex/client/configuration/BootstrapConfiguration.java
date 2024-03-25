package dev.tiltrikt.tetravex.client.configuration;

import dev.tiltrikt.tetravex.client.ui.UserInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootstrapConfiguration {

  @Bean
  public CommandLineRunner runner(UserInterface ui) {
    return args -> ui.bootstrap();
  }
}
