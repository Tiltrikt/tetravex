package dev.tiltrikt.service.rating;

import dev.tiltrikt.entity.Rating;
import dev.tiltrikt.exception.RatingException;

public interface RatingService {

    void setRating(Rating rating) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, String player) throws RatingException;
    void reset() throws RatingException;
}
