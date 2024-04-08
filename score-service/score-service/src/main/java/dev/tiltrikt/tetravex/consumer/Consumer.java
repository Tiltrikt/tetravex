package dev.tiltrikt.tetravex.consumer;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.tetravex.configuration.ScoreKafkaTopicConfiguration;
import dev.tiltrikt.tetravex.dto.ScoreAddRequest;
import dev.tiltrikt.tetravex.model.Score;
import dev.tiltrikt.tetravex.service.score.ScoreService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Consumer {

  @NotNull ScoreService scoreService;

  @NotNull Mapper mapper;

  @RetryableTopic(attempts = "1")
  @KafkaListener(topics = ScoreKafkaTopicConfiguration.SCORE_TOPIC)
  public void addScore(@Valid @Payload ScoreAddRequest scoreAddRequest, @Header("Player") String player) {

    Score score = mapper.map(scoreAddRequest, Score.class);
    score.setPlayer(player);
    scoreService.addScore(score);
  }
}
