package dev.tiltrikt.tetravex.client.rest.rating;

import dev.tiltrikt.tetravex.dto.RatingAddRequest;
import dev.tiltrikt.tetravex.exception.RestResponseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingRestClientImpl implements RatingRestClient {

  @NotNull RestTemplate restTemplate;

  @Value("${address.gateway}")
  @NonFinal
  String gateway;

  @Value("${address.rating}")
  @NonFinal
  String rating;

  public void setRating(@NotNull RatingAddRequest ratingAddRequest) {
    restTemplate.put(gateway + rating, ratingAddRequest);
  }

  public int getRating(@NotNull String game) {

    Integer rating = restTemplate.getForEntity(gateway + this.rating + "/" + game, Integer.class).getBody();

    if (rating == null) {
      throw new RestResponseException("Get rating returned null");
    }
    return rating;
  }

  public int getAverageRating(@NotNull String game) {

    Integer rating = restTemplate.getForEntity(this.rating + "/average/" + game, Integer.class).getBody();

    if (rating == null) {
      throw new RestResponseException("Get average rating returned null");
    }
    return rating;
  }

  public void reset() {
    restTemplate.delete(this.rating);
  }
}
