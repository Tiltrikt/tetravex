package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.exception.CommentException;
import dev.tiltrikt.tetravex.core.exception.NoPlayerException;
import dev.tiltrikt.tetravex.core.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

@ActionClass
public class CommentAction extends Action {

  public CommentAction(@Nullable Action action) {
    super(action, ActionType.COMMENT);
  }

  public @NotNull String doAction(@NotNull String input) {

    if (player == null) {
      throw new NoPlayerException("Please set player");
    }

    List<String> parameterList = regexService.getParameters(input);

    for (String string : parameterList) {
      System.out.println(string);
    }

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
        return stringConvertingServiceImpl.convertCommentsToString(commentList);
      }

      default -> {
        throw new CommentException("Wrong action parameter");
      }
    }
  }

}
