package dev.tiltrikt.tetravex.core.repository;

import dev.tiltrikt.tetravex.core.model.Rating;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

  @Query(value = "SELECT ROUND(AVG(r.rating)) FROM rating AS r WHERE r.game = ?", nativeQuery = true)
  int getAverageRating(@Param("game") @NotNull String game);

  int getRatingByGameAndPlayer(@NotNull String game, @NotNull String player);

  void deleteByGameAndPlayer(@NotNull String game, @NotNull String player);
}
