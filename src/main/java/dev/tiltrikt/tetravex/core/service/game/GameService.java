package dev.tiltrikt.tetravex.core.service.game;

import dev.tiltrikt.tetravex.core.service.game.dto.Move;
import dev.tiltrikt.tetravex.core.service.game.model.Field;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface GameService {

  void replaceTile(@NotNull Move move);

  @NotNull Field getGeneratedField();

  @NotNull Field getPlayField();
  @NotNull Date getStartTime();

  boolean isWin();
  int getPoints();
}
