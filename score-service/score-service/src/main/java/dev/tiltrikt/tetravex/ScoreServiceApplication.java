package dev.tiltrikt.tetravex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
    "dev.tiltrikt.tetravex",
    "dev.tiltrikt.commons"
})
@EnableJpaRepositories(basePackages = "dev.tiltrikt.tetravex.repository")
@EntityScan(basePackages = "dev.tiltrikt.tetravex.model")
public class ScoreServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ScoreServiceApplication.class);
  }
}
