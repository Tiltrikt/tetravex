package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.client.score.ScoreRestClient;
import dev.tiltrikt.tetravex.dto.ScoreDto;
import dev.tiltrikt.tetravex.exception.RatingException;
import dev.tiltrikt.tetravex.service.rendering.StringRenderingService;
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
public class ScoreAction implements Action {

  @NotNull RegexService regexService;

  @NotNull ValidationService validationService;

  @NotNull ScoreRestClient scoreRestClient;

  @NotNull StringRenderingService stringRenderingService;

  @Override
  public @NotNull String doAction(@NotNull String input) {

    List<String> parameterList = regexService.getParameters(input);
    validationService.validateScoreInput(parameterList);

    switch (parameterList.getFirst().toLowerCase()) {

      case "reset" -> {
        scoreRestClient.reset();
        return "Reset\n";
      }

      case "get" -> {
        List<ScoreDto> scoreList = scoreRestClient.getTopScores("tetravex");
        return stringRenderingService.renderScores(scoreList);
      }

      default -> {
        throw new RatingException("Wrong action parameter");
      }
    }
  }
}
