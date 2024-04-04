package dev.tiltrikt.tetravex.service.console;

import dev.tiltrikt.commons.exception.GameException;
import org.jetbrains.annotations.NotNull;

public interface ConsoleService {

  @NotNull String getUserInput(@NotNull String format, Object @NotNull ... args);

  void printResponse(@NotNull String response);

  void printException(@NotNull GameException exception);
}
