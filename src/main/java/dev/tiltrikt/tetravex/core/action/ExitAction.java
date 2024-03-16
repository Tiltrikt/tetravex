package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ActionClass
public class ExitAction extends Action {

  public ExitAction(@Nullable Action action) {
    super(action, ActionType.EXIT);
  }

  @Override
  protected @NotNull String doAction(@NotNull String input) {
    System.exit(0);
    return "Exited";
  }
}
