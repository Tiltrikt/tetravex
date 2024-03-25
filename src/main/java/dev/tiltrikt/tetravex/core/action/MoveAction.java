package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.GameNotStartedException;
import dev.tiltrikt.tetravex.core.model.Score;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.game.dto.Move;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@ActionClass
public class MoveAction extends Action {

  public MoveAction(@NotNull CommentService commentService, @NotNull RatingService ratingService, @NotNull ScoreService scoreService, @NotNull RegexService regexService, @NotNull ValidationService validationService, @NotNull StringConvertingService stringConvertingService) {
    super(commentService, ratingService, scoreService, regexService, validationService, stringConvertingService);
    this.actionType = ActionType.MOVE;
  }

  public @NotNull String doAction(@NotNull String input) {

    if (gameService == null) {
      throw new GameNotStartedException("Please start game");
    }

    List<String> actionList = regexService.getMoveParameters(input);
    Move move = stringConvertingService.convertListToMoveRequest(actionList);
    validationService.validateMoveInput(move, gameService.getPlayField().getSize());
    gameService.replaceTile(move);

    if (gameService.isWin()) {
      int points = gameService.getPoints();
      scoreService.addScore(new Score(GameConfiguration.GAME, player, points, Date.from(Instant.now())));
      return stringConvertingService.convertFieldsToString(gameService) + String.format("You won with %d points!\n", points);
    } else {
      return stringConvertingService.convertFieldsToString(gameService);
    }
  }
}
