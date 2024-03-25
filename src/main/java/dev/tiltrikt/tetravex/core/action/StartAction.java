package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.game.GameServiceImpl;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@ActionClass
public class StartAction extends Action {

  public StartAction(@NotNull CommentService commentService, @NotNull RatingService ratingService, @NotNull ScoreService scoreService, @NotNull RegexService regexService, @NotNull ValidationService validationService, @NotNull StringConvertingService stringConvertingService) {
    super(commentService, ratingService, scoreService, regexService, validationService, stringConvertingService);
    this.actionType = ActionType.START;
  }

  public @NotNull String doAction(@NotNull String input) {

    if (player == null) {
      return "Please set player first\n";
    }

    List<String> startList = regexService.getParameters(input);
    validationService.validateStartInput(startList);

    int size = Integer.parseInt(startList.getFirst());
    validationService.validateFieldSize(GameConfiguration.MIN, GameConfiguration.MAX, size);
    gameService = new GameServiceImpl(size); //------------------

    return stringConvertingService.convertFieldsToString(gameService);
  }
}
