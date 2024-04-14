package dev.tiltrikt.tetravex.client.rest.game;

import dev.tiltrikt.tetravex.dto.FieldDto;
import dev.tiltrikt.tetravex.dto.GameStartRequest;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.dto.TetravexResponse;
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
public class GameRestClientImpl implements GameRestClient {

  @NotNull RestTemplate restTemplate;

  @Value("${address.gateway}")
  @NonFinal
  String gateway;

  @Value("${address.tetravex}")
  @NonFinal
  String game;

  @Override
  public @NotNull List<FieldDto> startGame(@NotNull GameStartRequest gameStartRequest) {

    List<FieldDto> fields = restTemplate.exchange(gateway + game + "/start", HttpMethod.PUT, new HttpEntity<>(gameStartRequest),
        new ParameterizedTypeReference<List<FieldDto>>() {}).getBody();

    if (fields == null) {
      throw new RestResponseException("Get rating returned null");
    }
    return fields;
  }


  @Override
  public @NotNull TetravexResponse replaceTile(@NotNull MoveMakeRequest moveMakeRequest) {

    TetravexResponse response = restTemplate.exchange(gateway + game + "/replace", HttpMethod.PUT, new HttpEntity<>(moveMakeRequest),
        TetravexResponse.class).getBody();

    if (response == null) {
      throw new RestResponseException("Error returning fields");
    }
    return response;
  }

  @Override
  public @NotNull FieldDto getGeneratedField() {

    FieldDto fieldDto = restTemplate.getForEntity(gateway + game + "/generatedfield", FieldDto.class).getBody();

    if (fieldDto == null) {
      throw new RestResponseException("Field is null");
    }
    return fieldDto;
  }

  @Override
  public @NotNull FieldDto getPlayField() {

    FieldDto fieldDto = restTemplate.getForEntity(gateway + game + "/playfield", FieldDto.class).getBody();

    if (fieldDto == null) {
      throw new RestResponseException("Field is null");
    }
    return fieldDto;
  }
}
