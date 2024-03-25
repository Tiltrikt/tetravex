package dev.tiltrikt.tetravex.core.service.rating;

import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Rating;
import dev.tiltrikt.tetravex.core.repository.RatingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingServiceJpa implements RatingService {

  RatingRepository ratingRepository;

  @Override
  public void setRating(Rating rating) throws RatingException {
    ratingRepository.deleteByGameAndPlayer(rating.getGame(), rating.getPlayer());
    ratingRepository.save(rating);
  }

  @Override
  public int getAverageRating(String game) throws RatingException {
    return ratingRepository.getAverageRating(game);
  }

  @Override
  public int getRating(String game, String player) throws RatingException {
    return ratingRepository.getRatingByGameAndPlayer(game, player);
  }

  @Override
  public void reset() throws RatingException {
    ratingRepository.deleteAll();
  }
}
