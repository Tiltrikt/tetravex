package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.exception.GameException;
import dev.tiltrikt.tetravex.service.validation.ValidationServiceImpl;
import dev.tiltrikt.tetravex.exception.CommentException;
import dev.tiltrikt.tetravex.service.game.GameService;
import dev.tiltrikt.tetravex.action.details.Move;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MoveAction extends Action {

  @NotNull ValidationServiceImpl validationService = new ValidationServiceImpl();

  public MoveAction(@Nullable Action action) {
    super(action);
  }

  public void doAction(@NotNull ActionType actionType, @NotNull List<Objects> actionArgs, @NotNull GameService gameService) {
    if (actionType == ActionType.MOVE) {

      Move move = parsingService.parseMove(actionArgs);
      validationService.validateMoveInput(move, gameService.getPlayField().getSize());

      try {
        gameService.replaceTile(move);
      } catch (GameException exception) {
        System.out.println(exception.getMessage());
      }

    } else if (action != null) {
      action.doAction(actionType, actionArgs, gameService);
    } else {
      throw new CommentException("End of chain, wrong action");
    }
  }
}
