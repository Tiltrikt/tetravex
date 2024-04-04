package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class OutOfBoundsException extends GameException {

  public OutOfBoundsException(String message) {
    super(message);
  }
}
