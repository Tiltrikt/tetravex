package dev.tiltrikt.tetravex.repository;

import dev.tiltrikt.tetravex.model.Rating;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

  @Query(value = "SELECT ROUND(AVG(r.rating)) FROM rating AS r WHERE r.game = ?", nativeQuery = true)
  int getAverageRating(@Param("game") @NotNull String game);

  @Query(value = "SELECT rating FROM rating WHERE game = ? AND player = ?", nativeQuery = true)
  int getRating(@NotNull String game, @NotNull String player);

  void deleteByGameAndPlayer(@NotNull String game, @NotNull String player);
}
