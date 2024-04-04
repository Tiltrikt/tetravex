package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class GameNotStartedException extends GameException {

  public GameNotStartedException(String message) {
    super(message);
  }
}
