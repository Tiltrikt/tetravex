package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.exception.ChainException;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.comment.CommentServiceJdbc;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingServiceImpl;
import dev.tiltrikt.tetravex.core.service.game.GameService;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.rating.RatingServiceJdbc;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.regex.RegexServiceImpl;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.score.ScoreServiceJdbc;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class Action {

  @Nullable Action action;

  @NotNull ActionType actionType;

  @NonFinal static protected String player;
  @NonFinal static protected GameService gameService;
  @NotNull CommentService commentService = new CommentServiceJdbc();
  @NotNull RatingService ratingService = new RatingServiceJdbc();
  @NotNull ScoreService scoreService = new ScoreServiceJdbc();
  @NotNull RegexService regexService = new RegexServiceImpl();
  @NotNull ValidationService validationService = new ValidationServiceImpl();
  @NotNull StringConvertingService stringConvertingServiceImpl = new StringConvertingServiceImpl();

  public @NotNull String handleAction(@NotNull String input) {

    ActionType actionType = ActionType.resolveAction(regexService.getAction(input));
    if(actionType == this.actionType) {
      return doAction(input);
    } else if (action != null) {
      return action.handleAction(input);
    } else {
      throw new ChainException("End of chain, wrong action");
    }
  }

  protected abstract @NotNull String doAction(@NotNull String input);

}
