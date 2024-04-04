package dev.tiltrikt.tetravex.action;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ExitAction implements Action {

  @Override
  public @NotNull String doAction(@NotNull String input) {
    System.exit(0);
    return "Exited";
  }
}
