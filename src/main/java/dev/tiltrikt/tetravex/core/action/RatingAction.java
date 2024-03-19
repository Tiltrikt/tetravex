package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.NoPlayerException;
import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Rating;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ActionClass
public class RatingAction extends Action {

  public RatingAction(@Nullable Action action) {
    super(action, ActionType.RATING);
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
