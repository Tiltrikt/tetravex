package dev.tiltrikt.service;

import dev.tiltrikt.model.Field;
import org.jetbrains.annotations.NotNull;

public interface GameService {

  public void replaceTile(FieldObject fromFieldObject, int fromRow, int fromColumn,
                          FieldObject toFieldObject, int toRow, int toColumn);

  @NotNull Field getGeneratedField();

  @NotNull Field getSolvedField();
}
