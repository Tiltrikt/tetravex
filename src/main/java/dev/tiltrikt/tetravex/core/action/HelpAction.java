package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ActionClass
public class HelpAction extends Action {

  public HelpAction(@Nullable Action action) {
    super(action, ActionType.HELP);
  }

  @Override
  protected @NotNull String doAction(@NotNull String input) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Usage info:\n");
    stringBuilder.append("help -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> shows this information\n");
    stringBuilder.append("start <size player> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> starts game\n");
    stringBuilder.append("move (FROM)<field[above/down] row column> (TO)<field row column> -> move tile\n");
    stringBuilder.append("comment [add + comment / get / reset] -> -> -> -> -> -> -> -> -> -> add / get / reset comment\n");
    stringBuilder.append("rating [add + rating / get / average / reset]  -> -> -> -> -> -> -> add / get / average / reset rating\n");
    stringBuilder.append("score [add + score / get / reset]  -> -> -> -> -> -> -> -> -> -> -> display all finished tasks\n");
    stringBuilder.append("exit -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> exit game\n");

    return stringBuilder.toString();
  }
}
