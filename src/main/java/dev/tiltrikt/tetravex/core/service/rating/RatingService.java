package dev.tiltrikt.tetravex.core.service.rating;

import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Rating;

public interface RatingService {

    void setRating(Rating rating) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, String player) throws RatingException;
    void reset() throws RatingException;
}
