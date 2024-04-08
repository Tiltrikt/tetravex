package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.client.rest.rating.RatingRestClient;
//import dev.tiltrikt.tetravex.configuration.CoreConfiguration;
import dev.tiltrikt.tetravex.dto.RatingAddRequest;
//import dev.tiltrikt.tetravex.exception.NoPlayerException;
//import dev.tiltrikt.tetravex.exception.RatingException;
//import dev.tiltrikt.tetravex.model.Rating;
//import dev.tiltrikt.tetravex.service.comment.CommentService;
//import dev.tiltrikt.tetravex.service.rendering.StringRenderingService;
//import dev.tiltrikt.tetravex.service.rating.RatingService;
//import dev.tiltrikt.tetravex.service.regex.RegexService;
//import dev.tiltrikt.tetravex.service.score.ScoreService;
//import dev.tiltrikt.tetravex.service.validation.ValidationService;
import dev.tiltrikt.tetravex.exception.RatingException;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import dev.tiltrikt.tetravex.service.validation.ValidationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingAction implements Action {

  @NotNull RatingRestClient ratingRestClient;

  @NotNull RegexService regexService;

  @NotNull ValidationService validationService;

  @Override
  public @NotNull String doAction(@NotNull String input) {

    List<String> parameterList = regexService.getParameters(input);
    validationService.validateRatingInput(parameterList);

    switch (parameterList.getFirst().toLowerCase()) {

      case "reset" -> {
        ratingRestClient.reset();
        return "Reset\n";
      }

      case "add" -> {
        ratingRestClient.setRating(new RatingAddRequest("tetravex", Integer.parseInt(parameterList.getLast())));
        return "Added\n";
      }

      case "get" -> {
        return String.valueOf(ratingRestClient.getRating("tetravex"));
      }

      case "average" -> {
         return String.valueOf(ratingRestClient.getAverageRating("tetravex"));
      }

      default -> {
        throw new RatingException("Wrong action parameter");
      }
    }
  }
}
