package dev.tiltrikt.tetravex.service.game;

import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.model.Field;
import org.jetbrains.annotations.NotNull;

public interface GameService {

  void replaceTile(@NotNull MoveMakeRequest moveMakeRequest);

  @NotNull Field getGeneratedField();

  @NotNull Field getPlayField();
}
