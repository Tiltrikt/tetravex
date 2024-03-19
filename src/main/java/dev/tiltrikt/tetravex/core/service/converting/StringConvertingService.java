package dev.tiltrikt.tetravex.core.service.converting;

import dev.tiltrikt.tetravex.core.exception.InputException;
import dev.tiltrikt.tetravex.core.model.Comment;
import dev.tiltrikt.tetravex.core.model.Score;
import dev.tiltrikt.tetravex.core.service.game.GameService;
import dev.tiltrikt.tetravex.core.service.game.dto.Move;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StringConvertingService {

  @NotNull
  String convertFieldsToString(@NotNull GameService gameService);

  @NotNull
  String convertCommentsToString(@NotNull List<Comment> commentList);

  @NotNull
  String convertScoresToString(@NotNull List<Score> scoreList);

  @NotNull
  Move convertListToMoveRequest(@NotNull List<String> actionList) throws InputException;

}

