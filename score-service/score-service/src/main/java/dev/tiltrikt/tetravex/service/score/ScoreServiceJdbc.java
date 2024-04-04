package dev.tiltrikt.tetravex.service.score;

import dev.tiltrikt.tetravex.configuration.DataBaseConfiguration;
import dev.tiltrikt.tetravex.exception.ScoreException;
import dev.tiltrikt.tetravex.model.Score;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuppressWarnings("SqlDialectInspection")
public class ScoreServiceJdbc implements ScoreService {

  public static final String TABLE = "CREATE TABLE IF NOT EXISTS score(game varchar(30), player varchar(30), points int, played_on date)";
  public static final String SELECT = "SELECT game, player, points, played_on FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
  public static final String DELETE = "DELETE FROM score";
  public static final String INSERT = "INSERT INTO score (game, player, points, played_on) VALUES (?, ?, ?, ?)";

  DataBaseConfiguration configuration;

  public ScoreServiceJdbc(@NotNull DataBaseConfiguration configuration) {
    this.configuration = configuration;
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
         PreparedStatement statement = connection.prepareStatement(TABLE)
    ) {
      statement.execute();
    } catch (SQLException e) {
      throw new ScoreException("Problem creating table", e);
    }
  }

  @Override
  public void addScore(Score score) {
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
         PreparedStatement statement = connection.prepareStatement(INSERT)
    ) {
      statement.setString(1, score.getGame());
      statement.setString(2, score.getPlayer());
      statement.setInt(3, score.getPoints());
      statement.setTimestamp(4, new Timestamp(score.getPlayedOn().getTime()));
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new ScoreException("Problem adding score", e);
    }
  }

  @Override
  public List<Score> getTopScores(String game) {
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
         PreparedStatement statement = connection.prepareStatement(SELECT)
    ) {
      statement.setString(1, game);
      try (ResultSet rs = statement.executeQuery()) {
        List<Score> scores = new ArrayList<>();
        while (rs.next()) {
          scores.add(Score.builder()
              .game(rs.getString(1))
              .player(rs.getString(2))
              .points(rs.getInt(3))
              .playedOn(rs.getTimestamp(4))
              .build());
        }
        return scores;
      }
    } catch (SQLException e) {
      throw new ScoreException("Problem selecting score", e);
    }
  }

  @Override
  public void reset() {
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
         Statement statement = connection.createStatement()
    ) {
      statement.executeUpdate(DELETE);
    } catch (SQLException e) {
      throw new ScoreException("Problem deleting score", e);
    }
  }
}
