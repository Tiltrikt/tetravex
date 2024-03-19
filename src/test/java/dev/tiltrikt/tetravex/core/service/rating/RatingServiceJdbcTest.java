package dev.tiltrikt.tetravex.core.service.rating;

import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Rating;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.*;
import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SqlDialectInspection")
class RatingServiceJdbcTest {

  RatingService ratingService;

  @BeforeEach
  void createService() {

    // connection error check
    assertDoesNotThrow(() -> ratingService = new RatingServiceJdbc());
    ratingService.reset();
  }

  @Test
  void onAddingCommentMustBeAdded() {

    Rating rating = new Rating("x", "x", 5);
    ratingService.setRating(rating);
    assertEquals(ratingService.getRating("x", "x"), rating.getRating());
  }

  @Test
  void onAddingTooHighOrTooLowRatingExceptionMustBeThrown() {

    assertThrows(RatingException.class, () -> ratingService.setRating(new Rating("x", "x", 105)));
    assertThrows(RatingException.class, () -> ratingService.setRating(new Rating("x", "x", -5)));
  }

  @Test
  void calculatingAverageRating() {

    ratingService.setRating(new Rating("x", "x", 5));
    ratingService.setRating(new Rating("x", "f", 10));
    ratingService.setRating(new Rating("x", "g", 15));
    assertEquals(10, ratingService.getAverageRating("x"));
  }

  @Test
  void afterResettingTableMustAverageRatingMustBeZero() {

    Rating rating = new Rating("x", "x", 5);
    ratingService.setRating(rating);
    ratingService.reset();
    assertEquals(0, ratingService.getAverageRating("x"));
  }

  @SneakyThrows
  @Test
  void onCreatingServiceIfNoTablePresentNewMustBeCreated() {

    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    connection.prepareStatement("DROP TABLE IF EXISTS rating").execute();

    ratingService = new RatingServiceJdbc();
    assertDoesNotThrow(() -> connection.prepareStatement("SELECT * FROM rating").execute());
  }

  @SneakyThrows
  @Test
  void ifNoTablePresentEveryMethodMustThrowException() {

    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    connection.prepareStatement("DROP TABLE IF EXISTS rating").execute();

    assertThrows(RatingException.class, () -> ratingService.setRating(new Rating("x", "x", 5)));
    assertThrows(RatingException.class, () -> ratingService.getRating("x", "x"));
    assertThrows(RatingException.class, () -> ratingService.reset());
    assertThrows(RatingException.class, () -> ratingService.getAverageRating("x"));
  }
}