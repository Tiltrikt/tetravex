package dev.tiltrikt.service;

import dev.tiltrikt.model.Field;
import org.jetbrains.annotations.NotNull;

public interface GameService {

  void replaceTile(FieldPlayground fromFieldPlayground, int fromRow, int fromColumn,
                   FieldPlayground toFieldPlayground, int toRow, int toColumn);

  @NotNull Field getGeneratedField();

  @NotNull Field getFieldToSolve();

  boolean isWin();
}
