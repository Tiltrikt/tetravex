package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class EmptyFieldException extends GameException {

  public EmptyFieldException(String message) {
    super(message);
  }
}
