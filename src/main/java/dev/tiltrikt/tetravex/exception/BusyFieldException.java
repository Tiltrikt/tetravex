package dev.tiltrikt.tetravex.exception;

public class BusyFieldException extends GameException {
  public BusyFieldException() {
    super();
  }

  public BusyFieldException(String message) {
    super(message);
  }
}
