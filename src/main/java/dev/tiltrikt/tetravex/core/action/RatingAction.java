package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.NoPlayerException;
import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Rating;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@ActionClass
public class RatingAction extends Action {

  public RatingAction(@NotNull CommentService commentService, @NotNull RatingService ratingService, @NotNull ScoreService scoreService, @NotNull RegexService regexService, @NotNull ValidationService validationService, @NotNull StringConvertingService stringConvertingService) {
    super(commentService, ratingService, scoreService, regexService, validationService, stringConvertingService);
    this.actionType = ActionType.RATING;
  }

  @Override
  protected @NotNull String doAction(@NotNull String input) {

    if (player == null) {
      throw new NoPlayerException("Please set player");
    }

    List<String> parameterList = regexService.getParameters(input);
    validationService.validateRatingInput(parameterList);

    switch (parameterList.getFirst().toLowerCase()) {

      case "reset" -> {
        ratingService.reset();
        return "Reset\n";
      }

      case "add" -> {
        ratingService.setRating(new Rating(GameConfiguration.GAME, player, Integer.parseInt(parameterList.getLast())));
        return "Added\n";
      }

      case "get" -> {
        return String.valueOf(ratingService.getRating(GameConfiguration.GAME, player));
      }

      case "average" -> {
         return String.valueOf(ratingService.getAverageRating(GameConfiguration.GAME));
      }

      default -> {
        throw new RatingException("Wrong action parameter");
      }
    }
  }
}
