package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class NoPlayerException extends GameException {

  public NoPlayerException(String message) {
    super(message);
  }
}
