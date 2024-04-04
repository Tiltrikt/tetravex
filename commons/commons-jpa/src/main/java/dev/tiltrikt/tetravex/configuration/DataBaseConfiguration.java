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
@ConfigurationProperties(prefix = "spring.datasource")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataBaseConfiguration {

  String url = "url";
  String username = "username";
  String password = "password";
}
