package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.action.dto.Move;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ActionClass
public class MoveAction extends Action {

  public MoveAction(@Nullable Action action) {
    super(action, ActionType.MOVE);
  }

  public @NotNull String doAction(@NotNull String input) {

    List<String> actionList = regexService.getMoveParameters(input);
    Move move = mappingService.mapListToMoveRequest(actionList);
    validationService.validateMoveInput(move, gameService.getPlayField().getSize());
    gameService.replaceTile(move);
    return mappingService.mapFieldsToString(gameService);
  }
}
