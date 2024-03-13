package dev.tiltrikt.tetravex.exception;

public class EmptyFieldException extends GameException {
  public EmptyFieldException() {
    super();
  }

  public EmptyFieldException(String message) {
    super(message);
  }
}
