package dev.tiltrikt.tetravex.service.rendering;

//import dev.tiltrikt.tetravex.exception.InputException;
//import dev.tiltrikt.tetravex.model.Comment;
import dev.tiltrikt.tetravex.dto.FieldDto;
import dev.tiltrikt.tetravex.dto.RatingDto;
import dev.tiltrikt.tetravex.dto.ScoreDto;
//import dev.tiltrikt.tetravex.service.game.GameService;
//import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StringRenderingService {

  @NotNull
  String renderFields(@NotNull FieldDto generatedField, @NotNull FieldDto playField);

  @NotNull
  String renderComments(@NotNull List<RatingDto> commentList);

  @NotNull
  String renderScores(@NotNull List<ScoreDto> scoreList);

//  @NotNull
//  MoveMakeRequest convertListToMoveRequest(@NotNull List<String> actionList) throws InputException;

}

