package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@ActionClass
public class PlayerAction extends Action {

  public PlayerAction(@NotNull CommentService commentService, @NotNull RatingService ratingService, @NotNull ScoreService scoreService, @NotNull RegexService regexService, @NotNull ValidationService validationService, @NotNull StringConvertingService stringConvertingService) {
    super(commentService, ratingService, scoreService, regexService, validationService, stringConvertingService);
    this.actionType = ActionType.PLAYER;
  }

  public @NotNull String doAction(@NotNull String input) {

    List<String> startList = regexService.getParameters(input);
    validationService.validatePlayerInput(startList);

    player = startList.getFirst();

    return player + " have fun spending your time!\n";
  }
}
