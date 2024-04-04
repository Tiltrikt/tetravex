package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class GameAlreadyStartedException extends GameException {

  public GameAlreadyStartedException(String message) {
    super(message);
  }
}
