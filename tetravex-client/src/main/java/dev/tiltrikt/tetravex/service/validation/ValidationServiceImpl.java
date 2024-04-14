package dev.tiltrikt.tetravex.service.validation;

import dev.tiltrikt.tetravex.exception.InputException;
import dev.tiltrikt.tetravex.exception.OutOfBoundsException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationServiceImpl implements ValidationService {

  public void validateFieldSize(int min, int max, int input) throws OutOfBoundsException {

    if (input < min || input > max) {
      throw new OutOfBoundsException("Out of bounds");
    }
  }


  public void validateStartInput(@NotNull List<String> list) {

    if (list.size() != 1) {
      throw new InputException("Wrong number of parameters");
    }

    try {
      int size = Integer.parseInt(list.getFirst());
    } catch (NumberFormatException exception) {
      throw new InputException("Wrong type of parameter");
    }
  }


  public void validatePlayerInput(@NotNull List<String> list) {

    if (list.size() != 1) {
      throw new InputException("Wrong number of parameters");
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

    if (list.size() != 2 && list.getFirst().equalsIgnoreCase("add")) {
      throw new InputException("Wrong number of parameters");
    }

    if (list.size() != 1 && !list.getFirst().equalsIgnoreCase("add")) {
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

  @Override
  public void validateAuthenticationRequest(@NotNull List<String> list) {
    if (list.size() != 2) {
      throw new InputException("Wrong number of parameters");
    }
  }
}
