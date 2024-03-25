package dev.tiltrikt.tetravex.core.service.game;

import dev.tiltrikt.tetravex.core.exception.BusyFieldException;
import dev.tiltrikt.tetravex.core.exception.GameNotStartedException;
import dev.tiltrikt.tetravex.core.exception.GameNotWonException;
import dev.tiltrikt.tetravex.core.service.game.dto.Move;
import dev.tiltrikt.tetravex.core.service.game.factory.FieldFactory;
import dev.tiltrikt.tetravex.core.service.game.model.Field;
import dev.tiltrikt.tetravex.core.service.game.model.Tile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameServiceImpl implements GameService {

  @NotNull FieldFactory fieldFactory = new FieldFactory();

  @NotNull Field generatedField;

  @NotNull Field playField;

  @NotNull Date startTime;

  @NonFinal Date finishTime;

  public GameServiceImpl(int size) {
    this.generatedField = fieldFactory.getGenerated(size);
    this.playField = fieldFactory.getEmpty(size);
    this.startTime = Date.from(Instant.now());
  }

  public boolean isWin() {

    if (finishTime != null) {
      return true;
    }

    if (playField.isSolved()) {
      finishTime = Date.from(Instant.now());
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int getPoints() {

    if (!isWin()) {
      throw new GameNotWonException("No points 'cause game not finished");
    }

    long playingTimeInSeconds = Duration.between(startTime.toInstant(), finishTime.toInstant()).getSeconds();
    int generatedFieldSize = getGeneratedField().getSize();
    return (int) Math.round((Math.pow(2, generatedFieldSize) / playingTimeInSeconds) * 100);
  }

  @Override
  public void replaceTile(@NotNull Move move) {

    if (isWin()) {
      throw new GameNotStartedException("Start new game please");
    }

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
