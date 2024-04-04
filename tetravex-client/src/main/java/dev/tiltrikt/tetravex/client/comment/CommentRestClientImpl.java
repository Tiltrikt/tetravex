package dev.tiltrikt.tetravex.client.comment;

import dev.tiltrikt.tetravex.dto.CommentAddRequest;
import dev.tiltrikt.tetravex.dto.RatingDto;
import dev.tiltrikt.tetravex.exception.RestResponseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentRestClientImpl implements CommentRestClient {

  @NotNull RestTemplate restTemplate;

  @Value("${address.gateway}")
  @NonFinal
  String gateway;

  @Value("${address.comment}")
  @NonFinal
  String comment;

  public void addComment(@NotNull CommentAddRequest commentAddRequest) {
    restTemplate.postForEntity(gateway + comment, commentAddRequest, CommentAddRequest.class);
  }

  public @NotNull List<RatingDto> getComments(@NotNull String game) {

    List<RatingDto> commentList = restTemplate.exchange(gateway + comment + "/" + game, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<RatingDto>>() {}).getBody();

    if (commentList == null) {
      throw new RestResponseException("Get rating returned null");
    }
    return commentList;
  }

  public void reset() {
    restTemplate.delete(gateway + comment);
  }
}
