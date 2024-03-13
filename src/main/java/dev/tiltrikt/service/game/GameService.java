package dev.tiltrikt.service.game;

import dev.tiltrikt.model.Field;
import org.jetbrains.annotations.NotNull;

public interface GameService {

  void replaceTile(FieldType fromFieldPlayground, int fromRow, int fromColumn,
                   FieldType toFieldPlayground, int toRow, int toColumn);

  @NotNull Field getGeneratedField();

  @NotNull Field getPlayField();

  boolean isWin();
}
