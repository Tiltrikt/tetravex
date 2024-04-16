package dev.tiltrikt.tetravex.client.rest.score;

import dev.tiltrikt.tetravex.dto.ScoreDto;
import dev.tiltrikt.tetravex.exception.RestResponseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreRestClientImpl implements ScoreRestClient {

  @NotNull RestTemplate restTemplate;

  @Value("${address.gateway}")
  @NonFinal
  String gateway;

  @Value("${address.score}")
  @NonFinal
  String score;

  public @NotNull List<ScoreDto> getTopScores(@NotNull String game) {

    List<ScoreDto> scoreList = restTemplate.exchange(gateway + score + "/" + game, HttpMethod.GET, HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<ScoreDto>>() {}).getBody();

    if (scoreList == null) {
      throw new RestResponseException("Get top scores returned null");
    }
    return scoreList;
  }

  public void reset() {
    restTemplate.delete(gateway + score);
  }
}