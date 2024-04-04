package dev.tiltrikt.tetravex.client.game;

import dev.tiltrikt.tetravex.dto.FieldDto;
import dev.tiltrikt.tetravex.dto.GameStartRequest;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface GameRestClient {

  @NotNull List<FieldDto> startGame(@NotNull GameStartRequest gameStartRequest);

  @NotNull List<FieldDto> replaceTile(@NotNull MoveMakeRequest moveMakeRequest);

  @NotNull FieldDto getGeneratedField();
  @NotNull FieldDto getPlayField();
}
