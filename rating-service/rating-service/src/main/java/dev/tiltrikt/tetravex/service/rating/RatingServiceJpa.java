package dev.tiltrikt.tetravex.service.rating;

import dev.tiltrikt.mapper.core.Mapper;
import dev.tiltrikt.tetravex.exception.RatingException;
import dev.tiltrikt.tetravex.model.Rating;
import dev.tiltrikt.tetravex.repository.RatingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingServiceJpa implements RatingService {

  @NotNull RatingRepository ratingRepository;

  @NotNull Mapper mapper;

  @Override
  public void setRating(Rating rating) {
    Rating existingRating = ratingRepository.findByGameAndPlayer(rating.getGame(), rating.getPlayer()).orElse(new Rating());
    mapper.map(rating, existingRating);
    ratingRepository.save(existingRating);
  }

  @Override
  public int getAverageRating(String game) {
    return ratingRepository.getAverageRating(game);
  }

  @Override
  public int getRating(String game, String player) {
    return ratingRepository.findByGameAndPlayer(game, player)
        .orElseThrow(() -> new RatingException("No value present"))
        .getPoints();
  }

  @Override
  public void reset() {
    ratingRepository.deleteAll();
  }
}
