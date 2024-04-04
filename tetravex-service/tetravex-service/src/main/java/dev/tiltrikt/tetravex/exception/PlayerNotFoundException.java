package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class PlayerNotFoundException extends GameException {

  public PlayerNotFoundException(String message) {
    super(message);
  }
}
