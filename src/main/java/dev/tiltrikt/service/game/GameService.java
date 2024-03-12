package dev.tiltrikt.service.game;

import dev.tiltrikt.model.Field;
import org.jetbrains.annotations.NotNull;

public interface GameService {

  void replaceTile(GameServiceImpl.FieldPlayground fromFieldPlayground, int fromRow, int fromColumn,
                   GameServiceImpl.FieldPlayground toFieldPlayground, int toRow, int toColumn);

  @NotNull Field getGeneratedField();

  @NotNull Field getPlayField();

  boolean isWin();
}
