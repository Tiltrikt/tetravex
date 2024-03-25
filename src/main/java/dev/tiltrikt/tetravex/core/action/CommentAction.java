
package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.CommentException;
import dev.tiltrikt.tetravex.core.exception.NoPlayerException;
import dev.tiltrikt.tetravex.core.model.Comment;
import dev.tiltrikt.tetravex.core.service.comment.CommentService;
import dev.tiltrikt.tetravex.core.service.converting.StringConvertingService;
import dev.tiltrikt.tetravex.core.service.rating.RatingService;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.score.ScoreService;
import dev.tiltrikt.tetravex.core.service.validation.ValidationService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@ActionClass
public class CommentAction extends Action {

  public CommentAction(@NotNull CommentService commentService, @NotNull RatingService ratingService, @NotNull ScoreService scoreService, @NotNull RegexService regexService, @NotNull ValidationService validationService, @NotNull StringConvertingService stringConvertingService) {
    super(commentService, ratingService, scoreService, regexService, validationService, stringConvertingService);
    this.actionType = ActionType.COMMENT;
  }

  public @NotNull String doAction(@NotNull String input) {

    if (player == null) {
      throw new NoPlayerException("Please set player");
    }

    List<String> parameterList = regexService.getParameters(input);

    validationService.validateCommentInput(parameterList);

    switch (parameterList.getFirst().toLowerCase()) {

      case "reset" -> {
        commentService.reset();
        return "Reset\n";
      }

      case "add" -> {
        commentService.addComment(new Comment(GameConfiguration.GAME, player,
                parameterList.stream().skip(1).collect(Collectors.joining(" "))));
        return "Added\n";
      }

      case "get" -> {
        List<Comment> commentList = commentService.getComments(GameConfiguration.GAME);
        return stringConvertingService.convertCommentsToString(commentList);
      }

      default -> {
        throw new CommentException("Wrong action parameter");
      }
    }
  }

}
