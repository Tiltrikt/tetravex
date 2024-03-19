package dev.tiltrikt.tetravex.core.service.score;

import dev.tiltrikt.tetravex.core.exception.ScoreException;
import dev.tiltrikt.tetravex.core.model.Score;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.*;
import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SqlDialectInspection")
class ScoreServiceJdbcTest {


  ScoreService scoreService;

  @BeforeEach
  void createService() {

    // connection error check
    assertDoesNotThrow(() -> scoreService = new ScoreServiceJdbc());
    scoreService.reset();
  }

  @Test
  void onAddingScoreMustBeAdded() {

    Score score = new Score("x", "x", 5, Date.from(Instant.now()));
    scoreService.addScore(score);
    assertEquals(score, scoreService.getTopScores("x").getFirst());
  }

  @Test
  void onGettingTopScoresOnlyFirstTenMustBeReturned() {

    for (int i = 0; i < 20; i++) {
      Score score = new Score("x", "x", i, Date.from(Instant.now()));
      scoreService.addScore(score);
    }

    List<Score> scoreList = scoreService.getTopScores("x");
    assertEquals(10, scoreList.size());
    for (int i = 0; i < scoreList.size(); i++) {
      assertEquals(scoreList.get(i).getPoints(), 19 - i);
    }

  }

  @Test
  void afterResettingTableMustBeEmpty() {

    Score score = new Score("x", "x", 5, Date.from(Instant.now()));
    scoreService.addScore(score);
    scoreService.reset();
    assertEquals(0, scoreService.getTopScores("x").size());
  }

  @SneakyThrows
  @Test
  void onCreatingServiceIfNoTablePresentNewMustBeCreated() {

    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    connection.prepareStatement("DROP TABLE IF EXISTS score").execute();

    scoreService = new ScoreServiceJdbc();
    assertDoesNotThrow(() -> connection.prepareStatement("SELECT * FROM score").execute());
  }

  @SneakyThrows
  @Test
  void ifNoTablePresentEveryMethodMustThrowException() {

    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    connection.prepareStatement("DROP TABLE IF EXISTS score").execute();

    assertThrows(ScoreException.class, () -> scoreService.addScore(new Score("x", "x", 5, Date.from(Instant.now()))));
    assertThrows(ScoreException.class, () -> scoreService.getTopScores("x"));
    assertThrows(ScoreException.class, () -> scoreService.reset());
  }
}