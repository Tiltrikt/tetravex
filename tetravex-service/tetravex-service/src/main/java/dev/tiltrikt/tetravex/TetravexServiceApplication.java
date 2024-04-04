package dev.tiltrikt.tetravex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
    "dev.tiltrikt.tetravex",
    "dev.tiltrikt.commons"
})
public class TetravexServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TetravexServiceApplication.class, args);
  }
}
