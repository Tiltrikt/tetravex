package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.exception.ChainException;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.game.GameService;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class Action {

  @Setter
  @Getter
  @Nullable @NonFinal Action action;

  @Nullable @NonFinal ActionType actionType;

  @NonFinal static protected String player;
  @NonFinal static protected GameService gameService;

  @NotNull CommentService commentService;

  @NotNull RatingService ratingService;

  @NotNull ScoreService scoreService;

  @NotNull RegexService regexService;

  @NotNull ValidationService validationService;

  @NotNull StringConvertingService stringConvertingService;


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
