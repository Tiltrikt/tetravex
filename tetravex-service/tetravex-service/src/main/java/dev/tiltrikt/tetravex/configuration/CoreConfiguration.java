package dev.tiltrikt.tetravex.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "game")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoreConfiguration {

  int minField = 0;
  int maxField = 0;
}
