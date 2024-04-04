package dev.tiltrikt.tetravex.service.game;

import dev.tiltrikt.tetravex.exception.BusyFieldException;
import dev.tiltrikt.tetravex.exception.GameIsWon;
import dev.tiltrikt.tetravex.exception.GameNotStartedException;
import dev.tiltrikt.tetravex.exception.GameNotWonException;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.model.factory.FieldFactory;
import dev.tiltrikt.tetravex.model.Field;
import dev.tiltrikt.tetravex.field.FieldType;
import dev.tiltrikt.tetravex.model.Tile;
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

  private boolean isWin() {

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

  private int getPoints() {

    if (!isWin()) {
      throw new GameNotWonException("No points 'cause game not finished");
    }

    long playingTimeInSeconds = Duration.between(startTime.toInstant(), finishTime.toInstant()).getSeconds();
    int generatedFieldSize = getGeneratedField().getSize();
    return (int) Math.round((Math.pow(2, generatedFieldSize) / playingTimeInSeconds) * 100);
  }

  @Override
  public void replaceTile(@NotNull MoveMakeRequest moveMakeRequest) {

    if (isWin()) {
      throw new GameNotStartedException("Start new game please");
    }

    Field fromField = moveMakeRequest.getFromField() == FieldType.GENERATED ? generatedField : playField;
    Field toField = moveMakeRequest.getToField() == FieldType.GENERATED ? generatedField : playField;

    Tile tile = fromField.removeTile(moveMakeRequest.getFromRow() - 1, moveMakeRequest.getFromColumn() - 1);
    try {
      toField.addTile(tile, moveMakeRequest.getToRow() - 1, moveMakeRequest.getToColumn() - 1);
    } catch (BusyFieldException exception) {
      fromField.addTile(tile, moveMakeRequest.getFromRow() - 1, moveMakeRequest.getFromColumn() - 1);
      throw exception;
    }

    if (isWin()) {
      GameIsWon ex = new GameIsWon();
      ex.setPoints(getPoints());
      throw ex;
    }
  }

}
