package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.CommentException;
import dev.tiltrikt.tetravex.core.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ActionClass
public class CommentAction extends Action {

  public CommentAction(@Nullable Action action) {
    super(action, ActionType.COMMENT);
  }

  public @NotNull String doAction(@NotNull String input) {

    List<String> parameterList = regexService.getParameters(input);
    validationService.validateCommentInput(parameterList);

    switch (parameterList.getFirst().toLowerCase()) {

      case "reset" -> {
        commentService.reset();
        return "Reseted\n";
      }

      case "add" -> {
        commentService.addComment(new Comment(GameConfiguration.GAME, player,
                parameterList.stream().skip(1).toString()));
        return "Added";
      }

      case "get" -> {
        List<Comment> commentList = commentService.getComments(GameConfiguration.GAME);
        return mappingService.mapCommentsToString(commentList);
      }

      default -> {
        throw new CommentException("Wrong action parameter");
      }
    }
  }

}
