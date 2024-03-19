package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ActionClass
public class PlayerAction extends Action {

  public PlayerAction(@Nullable Action action) {
    super(action, ActionType.PLAYER);
  }

  public @NotNull String doAction(@NotNull String input) {

    List<String> startList = regexService.getParameters(input);
    validationService.validatePlayerInput(startList);

    player = startList.getFirst();

    return player + " have fun spending your time!\n";
  }
}
