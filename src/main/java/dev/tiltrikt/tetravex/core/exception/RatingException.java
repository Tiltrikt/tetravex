package dev.tiltrikt.tetravex.core.exception;

public class RatingException extends GameException {
    public RatingException(String message) {
        super(message);
    }

    public RatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
