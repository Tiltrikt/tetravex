
package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.client.rest.comment.CommentRestClient;
import dev.tiltrikt.tetravex.dto.CommentAddRequest;
import dev.tiltrikt.tetravex.dto.RatingDto;
import dev.tiltrikt.tetravex.exception.CommentException;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import dev.tiltrikt.tetravex.service.rendering.StringRenderingService;
import dev.tiltrikt.tetravex.service.validation.ValidationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentAction implements Action {

  @NotNull ValidationService validationService;

  @NotNull RegexService regexService;

  @NotNull CommentRestClient commentRestClient;

  @NotNull StringRenderingService stringRenderingService;

  public @NotNull String doAction(@NotNull String input) {

    List<String> parameterList = regexService.getParameters(input);

    validationService.validateCommentInput(parameterList);

    switch (parameterList.getFirst().toLowerCase()) {

      case "reset" -> {
        commentRestClient.reset();
        return "Reset\n";
      }

      case "add" -> {
        commentRestClient.addComment(new CommentAddRequest("tetarvex",
                parameterList.stream().skip(1).collect(Collectors.joining(" "))));
        return "Added\n";
      }

      case "get" -> {
        List<RatingDto> commentList = commentRestClient.getComments("tetravex");
        return stringRenderingService.renderComments(commentList);
      }

      default -> {
        throw new CommentException("Wrong action parameter");
      }
    }
  }

}
