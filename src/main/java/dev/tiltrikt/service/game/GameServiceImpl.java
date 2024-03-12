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
  public void replaceTile(FieldPlayground fromFieldPlayground, int fromRow, int fromColumn,
                          FieldPlayground toFieldPlayground, int toRow, int toColumn) {

    Field fromField = fromFieldPlayground.chooseField(generatedField, playField);
    Field toField = toFieldPlayground.chooseField(generatedField, playField);

    validateMove(fromRow, fromColumn, toRow, toColumn);

    Tile tile = fromField.removeTile(fromRow - 1, fromColumn - 1);

    try {
      toField.addTile(tile, toRow - 1, toColumn - 1);
    } catch (BusyFieldException exception) {
      fromField.addTile(tile, fromRow - 1, fromColumn - 1);
      throw exception;
    }
  }

  private void validateMove(int fromRow, int fromColumn, int toRow, int toColumn) throws OutOfBoundsException {
    int size = generatedField.getSize();
    if (    fromRow     < 1 || fromRow    > size ||
            fromColumn  < 1 || fromColumn > size ||
            toRow       < 1 || toRow      > size ||
            toColumn    < 1 || toColumn   > size) {
      throw new OutOfBoundsException("Out of bounds");
    }
  }

  public enum FieldPlayground {
    GENERATED {
      @Override
      public Field chooseField(Field generatedField, Field playField) {
        return generatedField;
      }
    },
    PLAYFIELD {
      @Override
      public Field chooseField(Field generatedField, Field playField) {
        return playField;
      }
    };

    public abstract Field chooseField(Field generatedField, Field playField);
  }
}
