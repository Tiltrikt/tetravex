package dev.tiltrikt.tetravex.client.service.console;

import dev.tiltrikt.tetravex.core.exception.GameException;
import org.jetbrains.annotations.NotNull;

public interface ConsoleService {

  @NotNull String getUserInput(@NotNull String format, Object @NotNull ... args);

  void printResponse(@NotNull String response);

  void printException(@NotNull GameException exception);
}
