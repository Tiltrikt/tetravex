package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import org.jetbrains.annotations.NotNull;

@ActionClass
public class ExitAction extends Action {

  public ExitAction(@NotNull CommentService commentService, @NotNull RatingService ratingService, @NotNull ScoreService scoreService, @NotNull RegexService regexService, @NotNull ValidationService validationService, @NotNull StringConvertingService stringConvertingService) {
    super(commentService, ratingService, scoreService, regexService, validationService, stringConvertingService);
    this.actionType = ActionType.EXIT;
  }

  @Override
  protected @NotNull String doAction(@NotNull String input) {
    System.exit(0);
    return "Exited";
  }
}
