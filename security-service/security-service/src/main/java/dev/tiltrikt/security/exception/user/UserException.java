package dev.tiltrikt.security.exception.user;

import dev.tiltrikt.commons.exception.GameException;
import org.jetbrains.annotations.NotNull;

public abstract class UserException extends GameException {

  public UserException(@NotNull String message) {
    super(message);
  }

  public UserException(@NotNull String message, Object @NotNull ... args) {
    super(String.format(message, args));
  }
}
