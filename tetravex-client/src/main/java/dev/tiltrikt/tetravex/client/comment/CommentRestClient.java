package dev.tiltrikt.tetravex.client.comment;

import dev.tiltrikt.tetravex.dto.CommentAddRequest;
import dev.tiltrikt.tetravex.dto.RatingDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CommentRestClient {

  void addComment(@NotNull CommentAddRequest commentAddRequest);

  List<RatingDto> getComments(@NotNull String game);

  void reset();

}
