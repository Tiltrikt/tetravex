package dev.tiltrikt.service.game;

import dev.tiltrikt.exception.BusyFieldException;
import dev.tiltrikt.exception.OutOfBoundsException;
import dev.tiltrikt.factory.FieldFactory;
import dev.tiltrikt.model.Field;
import dev.tiltrikt.model.Tile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameServiceImpl implements GameService {

  @NotNull FieldFactory fieldFactory = new FieldFactory();

  @NotNull Field generatedField;

  @NotNull Field playField;

  public GameServiceImpl(int size) {
    this.generatedField = fieldFactory.getGenerated(size);
    this.playField = fieldFactory.getEmpty(size);
  }

  public boolean isWin() {
    return playField.isSolved();
  }

  @Override
  public void replaceTile(FieldType fromFieldPlayground, int fromRow, int fromColumn,
                          FieldType toFieldPlayground, int toRow, int toColumn) {

    Field fromField = fromFieldPlayground.chooseField(generatedField, playField);
    Field toField = toFieldPlayground.chooseField(generatedField, playField);

    Tile tile = fromField.removeTile(fromRow - 1, fromColumn - 1);

    try {
      toField.addTile(tile, toRow - 1, toColumn - 1);
    } catch (BusyFieldException exception) {
      fromField.addTile(tile, fromRow - 1, fromColumn - 1);
      throw exception;
    }
  }

}
