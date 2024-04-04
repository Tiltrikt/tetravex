package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class RegexNotFoundException extends GameException {
  public RegexNotFoundException(String message) {
    super(message);
  }
}
