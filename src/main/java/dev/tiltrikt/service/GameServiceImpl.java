package dev.tiltrikt.service;

import dev.tiltrikt.exception.BusyFieldException;
import dev.tiltrikt.exception.EmptyFieldException;
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

  @NotNull Field fieldToSolve;

  public GameServiceImpl(int size) {
    this.generatedField = Field.getShuffledField(size);
    this.fieldToSolve = Field.getEmptyField(size);
  }

  public boolean isWin() {
    return fieldToSolve.isSolved();
  }


  @Override
  public void replaceTile(FieldPlayground fromFieldPlayground, int fromRow, int fromColumn,
                          FieldPlayground toFieldPlayground, int toRow, int toColumn) {

    Field fromField = fromFieldPlayground == FieldPlayground.GENERATED ? generatedField : fieldToSolve;
    Field toField = toFieldPlayground == FieldPlayground.GENERATED ? generatedField : fieldToSolve;

    Tile tile;
    try {
      tile = fromField.removeTile(fromRow - 1, fromColumn - 1);
    } catch (EmptyFieldException exception) {
      throw exception;
    }

    try {
      toField.addTile(tile, toRow - 1, toColumn - 1);
    } catch (BusyFieldException exception) {
      fromField.addTile(tile, fromRow - 1, fromColumn - 1);
      throw exception;
    }
  }

}
