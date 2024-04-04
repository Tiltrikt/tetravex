package dev.tiltrikt.tetravex.client.rating;

import dev.tiltrikt.tetravex.dto.RatingAddRequest;
import org.jetbrains.annotations.NotNull;

public interface RatingRestClient {

  void setRating(@NotNull RatingAddRequest ratingAddRequest);

  int getRating(@NotNull String game);

  int getAverageRating(@NotNull String game);

  void reset();

}
