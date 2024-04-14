package dev.tiltrikt.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
    "dev.tiltrikt.security",
    "dev.tiltrikt.commons"
})
@EnableJpaRepositories(basePackages = "dev.tiltrikt.security.repository")
@EntityScan(basePackages = "dev.tiltrikt.security.model")
public class SecurityServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityServiceApplication.class, args);
  }

}
