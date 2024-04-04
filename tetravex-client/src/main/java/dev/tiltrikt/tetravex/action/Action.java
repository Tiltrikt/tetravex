package dev.tiltrikt.tetravex.action;

import org.jetbrains.annotations.NotNull;

public interface Action {

  @NotNull String doAction(@NotNull String input);
}
