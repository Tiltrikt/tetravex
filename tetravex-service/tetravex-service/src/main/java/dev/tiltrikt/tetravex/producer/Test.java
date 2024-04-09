//package dev.tiltrikt.tetravex.producer;
//
//import dev.tiltrikt.tetravex.dto.ScoreAddRequest;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.util.Date;
//
//@Component
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class Test {
//
//  @NotNull ScoreProducer scoreProducer;
//
//  @Scheduled(initialDelay = 5000, fixedDelay = 1000)
//  public void run() {
//    scoreProducer.addScore(new ScoreAddRequest("",5, Date.from(Instant.now())), "vlad");
//  }
//}
