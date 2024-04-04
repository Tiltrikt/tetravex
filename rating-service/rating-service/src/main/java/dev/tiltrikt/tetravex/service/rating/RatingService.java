package dev.tiltrikt.tetravex.service.rating;

import dev.tiltrikt.tetravex.model.Rating;

public interface RatingService {

    void setRating(Rating rating);
    int getAverageRating(String game);
    int getRating(String game, String player);
    void reset();
}
