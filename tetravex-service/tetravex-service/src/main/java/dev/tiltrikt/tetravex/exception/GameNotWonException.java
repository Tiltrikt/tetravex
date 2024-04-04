package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class GameNotWonException extends GameException {

  public GameNotWonException(String message) {
    super(message);
  }
}
