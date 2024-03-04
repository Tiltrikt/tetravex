package dev.tiltrikt.exception;

public abstract class GameException extends RuntimeException {
  public GameException() {
    super();
  }

  public GameException(String message) {
    super(message);
  }
}
