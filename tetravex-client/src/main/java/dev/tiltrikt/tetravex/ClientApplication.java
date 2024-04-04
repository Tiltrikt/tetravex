package dev.tiltrikt.tetravex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "dev.tiltrikt.tetravex",
    "dev.tiltrikt.commons"
})
public class ClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientApplication.class);
  }
}
