package dev.tiltrikt.tetravex.service.rating;

import dev.tiltrikt.tetravex.model.Rating;
import dev.tiltrikt.tetravex.repository.RatingRepository;
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
  public void setRating(Rating rating) {
    ratingRepository.deleteByGameAndPlayer(rating.getGame(), rating.getPlayer());
    ratingRepository.save(rating);
  }

  @Override
  public int getAverageRating(String game) {
    return ratingRepository.getAverageRating(game);
  }

  @Override
  public int getRating(String game, String player) {
    return ratingRepository.getRating(game, player);
  }

  @Override
  public void reset() {
    ratingRepository.deleteAll();
  }
}
