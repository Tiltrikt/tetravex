package dev.tiltrikt.tetravex.service.multiplayer;

import dev.tiltrikt.tetravex.dto.GameStartRequest;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.model.Field;
import org.jetbrains.annotations.NotNull;

public interface MultiplayerGameService {

  void startGame(@NotNull String player, @NotNull GameStartRequest gameStartRequest);
  void replaceTile(@NotNull String player, @NotNull MoveMakeRequest moveMakeRequest);

  @NotNull Field getGeneratedField(@NotNull String player);

  @NotNull Field getPlayField(@NotNull String player);
}
