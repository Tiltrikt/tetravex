package dev.tiltrikt.tetravex.action;

//import dev.tiltrikt.tetravex.configuration.CoreConfiguration;
//import dev.tiltrikt.tetravex.service.comment.CommentService;
//import dev.tiltrikt.tetravex.service.rendering.StringRenderingService;
//import dev.tiltrikt.tetravex.service.rating.RatingService;
//import dev.tiltrikt.tetravex.service.regex.RegexService;
//import dev.tiltrikt.tetravex.service.score.ScoreService;
import dev.tiltrikt.tetravex.player.Player;
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
public class PlayerAction implements Action {

  @NotNull Player player;

  @NotNull ValidationService validationService;

  @NotNull RegexService regexService;

  public @NotNull String doAction(@NotNull String input) {

    List<String> startList = regexService.getParameters(input);
    validationService.validatePlayerInput(startList);
    player.setName(startList.getFirst());

    return player.getName() + " have fun spending your time!\n";
  }
}
