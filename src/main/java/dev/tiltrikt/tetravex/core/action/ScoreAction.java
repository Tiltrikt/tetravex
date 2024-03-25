package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Score;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@ActionClass
public class ScoreAction extends Action {

  public ScoreAction(@NotNull CommentService commentService, @NotNull RatingService ratingService, @NotNull ScoreService scoreService, @NotNull RegexService regexService, @NotNull ValidationService validationService, @NotNull StringConvertingService stringConvertingService) {
    super(commentService, ratingService, scoreService, regexService, validationService, stringConvertingService);
    this.actionType = ActionType.SCORE;
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
