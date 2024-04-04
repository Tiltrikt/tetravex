package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class ConnectionException extends GameException {

  public ConnectionException(String message) {
    super(message);
  }
}
