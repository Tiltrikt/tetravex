package dev.tiltrikt.tetravex.client.rest.score;

import dev.tiltrikt.tetravex.dto.ScoreAddRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreRestClientImpl implements ScoreRestClient {

  @NotNull RestTemplate restTemplate;

  @Value("${address.score}")
  @NonFinal String url;

  public void addScore(@NotNull ScoreAddRequest scoreAddRequest, @NotNull String player) {

    HttpHeaders header = new HttpHeaders();
    header.add(HttpHeaders.COOKIE, "Player=" + player);
    restTemplate.postForEntity(url, new HttpEntity<>(scoreAddRequest, header), null);
  }
}