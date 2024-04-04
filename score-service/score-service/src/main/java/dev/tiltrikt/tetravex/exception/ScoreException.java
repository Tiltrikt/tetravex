package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;

public class ScoreException extends GameException {

    public ScoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
