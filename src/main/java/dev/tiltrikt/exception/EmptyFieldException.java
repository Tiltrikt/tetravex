package dev.tiltrikt.exception;

public class EmptyFieldException extends GameException {
  public EmptyFieldException() {
    super();
  }

  public EmptyFieldException(String message) {
    super(message);
  }
}
