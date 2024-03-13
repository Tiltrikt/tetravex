package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.entity.Score;
import dev.tiltrikt.tetravex.exception.CommentException;
import dev.tiltrikt.tetravex.service.game.GameService;
import dev.tiltrikt.tetravex.service.score.ScoreService;
import dev.tiltrikt.tetravex.service.score.ScoreServiceJdbc;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static dev.tiltrikt.tetravex.configuration.GameConfiguration.GAME;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreAction extends Action {

  @NotNull ScoreService scoreService = new ScoreServiceJdbc();

  public ScoreAction(@Nullable Action action) {
    super(action);
  }

  @Override
  public void doAction(@NotNull ActionType actionType, @NotNull List<Objects> actionArgs, @NotNull GameService gameService) {
    if (actionType == ActionType.SCORE) {

      List<Score> scoreAction = scoreService.getTopScores(GAME);
      consoleService.printTopScore(scoreAction);

    } else if (action != null) {
      action.doAction(actionType, actionArgs, gameService);
    } else {
      throw new CommentException("End of chain, wrong action");
    }
  }
}
