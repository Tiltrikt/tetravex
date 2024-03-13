package dev.tiltrikt.tetravex.service.validation;

import dev.tiltrikt.tetravex.exception.OutOfBoundsException;
import dev.tiltrikt.tetravex.action.details.Move;
import org.jetbrains.annotations.NotNull;

public class ValidationServiceImpl {

  public void validateFieldSize(int min, int max, int input) throws OutOfBoundsException {

    if (input < min || input > max) {
      throw new OutOfBoundsException("Out of bounds");
    }
  }

  public void validateMoveInput(@NotNull Move move, int size) throws OutOfBoundsException {

    if (    move.getFromRow()     < 1 || move.getFromRow()    > size ||
            move.getFromColumn()  < 1 || move.getFromColumn() > size ||
            move.getToRow()       < 1 || move.getToRow()      > size ||
            move.getToColumn()    < 1 ||  move.getToColumn()   > size) {
      throw new OutOfBoundsException("Out of bounds");
    }
  }
}
