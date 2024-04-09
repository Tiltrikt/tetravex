package dev.tiltrikt.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "dev.tiltrikt.security",
    "dev.tiltrikt.commons"
})
public class SecurityServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityServiceApplication.class, args);
  }

}
