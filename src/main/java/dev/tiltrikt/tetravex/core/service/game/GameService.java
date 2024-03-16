package dev.tiltrikt.tetravex.core.service.game;

import dev.tiltrikt.tetravex.core.action.dto.Move;
import dev.tiltrikt.tetravex.core.service.game.model.Field;
import org.jetbrains.annotations.NotNull;

public interface GameService {

  void replaceTile(@NotNull Move move);

  @NotNull Field getGeneratedField();

  @NotNull Field getPlayField();

  boolean isWin();
}
