package dev.tiltrikt.tetravex.handler;

import org.jetbrains.annotations.NotNull;

public interface InputHandler {

  @NotNull String handleAction(@NotNull String input);
}
