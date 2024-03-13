package dev.tiltrikt.tetravex.service.game;

import dev.tiltrikt.tetravex.action.details.Move;
import dev.tiltrikt.tetravex.model.Field;
import org.jetbrains.annotations.NotNull;

public interface GameService {

  void replaceTile(@NotNull Move move);

  @NotNull Field getGeneratedField();

  @NotNull Field getPlayField();

  boolean isWin();
}
