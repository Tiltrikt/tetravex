package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Score;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@ActionClass
public class ScoreAction extends Action {

  public ScoreAction(@Nullable Action action) {
    super(action, ActionType.SCORE);
  }

  @Override
  protected @NotNull String doAction(@NotNull String input) {

    List<String> parameterList = regexService.getParameters(input);
    validationService.validateScoreInput(parameterList);

    switch (parameterList.getFirst().toLowerCase()) {

      case "reset" -> {
        scoreService.reset();
        return "Reset\n";
      }

      case "add" -> {
        scoreService.addScore(new Score(GameConfiguration.GAME, player,
                Integer.parseInt(parameterList.getLast()), Date.from(Instant.now())));
        return "Added";
      }

      case "get" -> {
        List<Score> scoreList = scoreService.getTopScores(GameConfiguration.GAME);
        return stringConvertingService.convertScoresToString(scoreList);
      }

      default -> {
        throw new RatingException("Wrong action parameter");
      }
    }
  }
}
