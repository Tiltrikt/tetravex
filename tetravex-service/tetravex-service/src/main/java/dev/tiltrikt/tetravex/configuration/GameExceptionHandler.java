package dev.tiltrikt.tetravex.configuration;

import dev.tiltrikt.tetravex.client.rest.score.ScoreRestClient;
import dev.tiltrikt.tetravex.dto.ScoreAddRequest;
import dev.tiltrikt.tetravex.dto.TetravexResponse;
import dev.tiltrikt.tetravex.exception.GameIsWon;
import dev.tiltrikt.tetravex.producer.ScoreProducer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameExceptionHandler {

  @NotNull ScoreRestClient scoreRestClient;

  @NotNull ScoreProducer scoreProducer;

  @NotNull
  @ExceptionHandler(GameIsWon.class)
  @ResponseStatus(HttpStatus.OK)
  public TetravexResponse gameIsWon(@NotNull GameIsWon ex) {

    scoreProducer.addScore(new ScoreAddRequest("tetravex", ex.getPoints(), Date.from(Instant.now())), ex.getPlayer());
    return TetravexResponse.builder()
        .win(true)
        .score(ex.getPoints())
        .build();
  }
}
