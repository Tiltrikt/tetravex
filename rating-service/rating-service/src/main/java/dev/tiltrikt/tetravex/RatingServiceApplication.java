package dev.tiltrikt.tetravex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
    "dev.tiltrikt.tetravex",
    "dev.tiltrikt.commons"
})
@EnableJpaRepositories(basePackages = "dev.tiltrikt.tetravex.repository")
@EntityScan(basePackages = "dev.tiltrikt.tetravex.model")
public class RatingServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RatingServiceApplication.class);
  }
}