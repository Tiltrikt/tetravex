package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.exception.GameNotStartedException;
import dev.tiltrikt.tetravex.core.service.game.dto.Move;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ActionClass
public class MoveAction extends Action {

  public MoveAction(@Nullable Action action) {
    super(action, ActionType.MOVE);
  }

  public @NotNull String doAction(@NotNull String input) {

    if (gameService == null) {
      throw new GameNotStartedException("Please start game");
    }

    List<String> actionList = regexService.getMoveParameters(input);
    Move move = stringConvertingServiceImpl.convertListToMoveRequest(actionList);
    validationService.validateMoveInput(move, gameService.getPlayField().getSize());
    gameService.replaceTile(move);

    if (gameService.isWin()) {
      int points = gameService.getPoints();
      new ScoreAction(null).doAction(String.format("score add %d", points));
      return stringConvertingServiceImpl.convertFieldsToString(gameService) + String.format("You won with %d points!\n", points);
    } else {
      return stringConvertingServiceImpl.convertFieldsToString(gameService);
    }
  }
}
