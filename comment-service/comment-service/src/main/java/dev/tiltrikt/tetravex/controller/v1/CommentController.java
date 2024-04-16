package dev.tiltrikt.tetravex.controller.v1;//package dev.tiltrikt.tetravex.controller;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.tetravex.dto.CommentAddRequest;
import dev.tiltrikt.tetravex.dto.RatingDto;
import dev.tiltrikt.tetravex.model.Comment;
import dev.tiltrikt.tetravex.service.comment.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

  CommentService commentService;

  Mapper mapper;

  @Operation(summary = "Add new comment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Comment is added"),
      @ApiResponse(responseCode = "400", description = "Wrong request")}
  )

  @PostMapping
  public void addComment(Principal principal, @Valid @RequestBody CommentAddRequest commentAddRequest) {

    Comment comment = mapper.map(commentAddRequest, Comment.class);
    comment.setPlayer(principal.getName());
    commentService.addComment(comment);
  }

  @Operation(summary = "Get all comments for the specific game")
  @ApiResponse(responseCode = "200", description = "Comments for this game",
      content = {@Content(mediaType = "application/json",
          array = @ArraySchema(schema = @Schema(implementation = Comment.class)))})
  @GetMapping("{game}")
  public List<RatingDto> getComments(@PathVariable String game) {

    return commentService.getComments(game).stream()
        .map((comment) -> mapper.map(comment, RatingDto.class))
        .toList();
  }

  @Operation(summary = "Delete all comments")
  @ApiResponse(responseCode = "200", description = "All comments were deleted")
  @DeleteMapping
  public void reset() {

    commentService.reset();
  }
}
