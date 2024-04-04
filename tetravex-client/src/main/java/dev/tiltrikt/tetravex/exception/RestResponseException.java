package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class RestResponseException extends GameException {
  public RestResponseException(String message) {
    super(message);
  }

}
