package dev.tiltrikt.tetravex.core.service.validation;

import dev.tiltrikt.tetravex.core.action.dto.Move;
import dev.tiltrikt.tetravex.core.exception.InputException;
import dev.tiltrikt.tetravex.core.exception.OutOfBoundsException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ValidationServiceImpl {

  public void validateFieldSize(int min, int max, int input) throws OutOfBoundsException {

    if (input < min || input > max) {
      throw new OutOfBoundsException("Out of bounds");
    }
  }

  public void validateStartInput(@NotNull List<String> list) {

    if (list.size() != 2) {
      throw new InputException("Wrong number of parameters");
    }

    try {
      int size = Integer.parseInt(list.getFirst());
    } catch (NumberFormatException exception) {
      throw new InputException("Wrong type of first parameter");
    }
  }

  public void validateCommentInput(@NotNull List<String> list) {

    if (list.size() < 2 && list.getFirst().equalsIgnoreCase("add")) {
      throw new InputException("Wrong number of parameters");
    }

    if (list.size() != 1 && !list.getFirst().equalsIgnoreCase("add")) {
      throw new InputException("Wrong number of parameters");
    }
  }

  public void validateRatingInput(@NotNull List<String> list) {

    if (list.size() != 2 && list.getFirst().equalsIgnoreCase("set")) {
      throw new InputException("Wrong number of parameters");
    }

    if (list.size() != 1 && !list.getFirst().equalsIgnoreCase("set")) {
      throw new InputException("Wrong number of parameters");
    }

    if (list.size() == 2) {
      int rating;
      try {
        rating = Integer.parseInt(list.getLast());
      } catch (NumberFormatException exception) {
        throw new InputException("Wrong type of last parameter");
      }
      if (rating < 0 || rating > 100) {
        throw new OutOfBoundsException("Rating is out of bounds");
      }
    }

  }
  public void validateScoreInput(@NotNull List<String> list) {

    if (list.size() != 2 && list.getFirst().equalsIgnoreCase("add")) {
      throw new InputException("Wrong number of parameters");
    }

    if (list.size() != 1 && !list.getFirst().equalsIgnoreCase("add")) {
      throw new InputException("Wrong number of parameters");
    }

    if (list.size() == 2) {
      int points;
      try {
        points = Integer.parseInt(list.getLast());
      } catch (NumberFormatException exception) {
        throw new InputException("Wrong type of last parameter");
      }
      if (points < 0 || points > 100) {
        throw new OutOfBoundsException("Points are out of bounds");
      }
    }

  }


  public void validateMoveInput(@NotNull Move move, int size) throws OutOfBoundsException {

    if (move.getFromRow() < 1 || move.getFromRow() > size ||
            move.getFromColumn() < 1 || move.getFromColumn() > size ||
            move.getToRow() < 1 || move.getToRow() > size ||
            move.getToColumn() < 1 || move.getToColumn() > size) {
      throw new OutOfBoundsException("Out of bounds");
    }
  }
}
