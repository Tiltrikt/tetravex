package dev.tiltrikt.service;

import dev.tiltrikt.exception.BusyField;
import dev.tiltrikt.exception.EmptyField;
import dev.tiltrikt.model.Field;
import dev.tiltrikt.model.Tile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@SuppressWarnings("CaughtExceptionImmediatelyRethrown")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameServiceImpl implements GameService {

  @NotNull Field generatedField;

  @NotNull Field solvedField;

  public GameServiceImpl(int size) {
    this.generatedField = Field.getFullField(size);
    this.solvedField = Field.getEmptyField(size);
  }


  @Override
  public void replaceTile(FieldObject fromFieldObject, int fromRow, int fromColumn,
                          FieldObject toFieldObject, int toRow, int toColumn) {

    Field fromField = fromFieldObject == FieldObject.GENERATED ? generatedField : solvedField;
    Field toField = toFieldObject == FieldObject.GENERATED ? generatedField : solvedField;

    Tile tile;
    try {
      tile = fromField.removeTile(fromRow - 1, fromColumn - 1);
    } catch (EmptyField exception) {
      throw exception;
    }

    try {
      toField.addTile(tile, toRow - 1, toColumn - 1);
    } catch (BusyField exception) {
      fromField.addTile(tile, fromRow - 1, fromColumn - 1);
    }
  }

}
