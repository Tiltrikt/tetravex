package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class BusyFieldException extends GameException {

  public BusyFieldException(String message) {
    super(message);
  }
}
