package dev.tiltrikt.security.exception;

import dev.tiltrikt.commons.exception.GameException;

public class MyAuthenticationException extends GameException {

  public MyAuthenticationException(String message) {
    super(message);
  }
}
