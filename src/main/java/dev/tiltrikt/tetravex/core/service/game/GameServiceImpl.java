package dev.tiltrikt.tetravex.core.service.game;

import dev.tiltrikt.tetravex.core.action.dto.Move;
import dev.tiltrikt.tetravex.core.service.game.factory.FieldFactory;
import dev.tiltrikt.tetravex.core.exception.BusyFieldException;
import dev.tiltrikt.tetravex.core.service.game.model.Field;
import dev.tiltrikt.tetravex.core.service.game.model.Tile;
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
  public void replaceTile(@NotNull Move move) {

    Field fromField = move.getFromField().chooseField(generatedField, playField);
    Field toField = move.getToField().chooseField(generatedField, playField);

    Tile tile = fromField.removeTile(move.getFromRow() - 1, move.getFromColumn() - 1);

    try {
      toField.addTile(tile, move.getToRow() - 1, move.getToColumn() - 1);
    } catch (BusyFieldException exception) {
      fromField.addTile(tile, move.getFromRow() - 1, move.getFromColumn() - 1);
      throw exception;
    }
  }

}
