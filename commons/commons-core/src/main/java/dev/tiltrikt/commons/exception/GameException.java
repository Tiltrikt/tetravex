package dev.tiltrikt.commons.exception;

public abstract class GameException extends RuntimeException {
  public GameException() {
    super();
  }

  public GameException(String message) {
    super(message);
  }

  public GameException(String message, Throwable cause) {
    super(message, cause);
  }
}
