package dev.tiltrikt.tetravex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "dev.tiltrikt.tetravex")
//@EnableJpaRepositories(basePackages = "dev.tiltrikt.tetravex.core.repository")
@EntityScan(basePackages = "dev.tiltrikt.tetravex.core.model")
public class TetravexApplication {

  public static void main(String[] args) {
    SpringApplication.run(TetravexApplication.class, args);
  }
}
