package dev.tiltrikt.tetravex.core.service.score;

import dev.tiltrikt.tetravex.core.exception.ScoreException;
import dev.tiltrikt.tetravex.core.model.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.*;


@SuppressWarnings("SqlDialectInspection")
public class ScoreServiceJdbc implements ScoreService {

  public static final String TABLE = "CREATE TABLE IF NOT EXISTS score(game varchar(30), player varchar(30), points int, played_on date)";
  public static final String SELECT = "SELECT game, player, points, played_on FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
  public static final String DELETE = "DELETE FROM score";
  public static final String INSERT = "INSERT INTO score (game, player, points, played_on) VALUES (?, ?, ?, ?)";

  public ScoreServiceJdbc() {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(TABLE)
    ) {
      statement.execute();
    } catch (SQLException e) {
      throw new ScoreException("Problem creating table", e);
    }
  }

  @Override
  public void addScore(Score score) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(SELECT)
    ) {
      statement.setString(1, game);
      try (ResultSet rs = statement.executeQuery()) {
        List<Score> scores = new ArrayList<>();
        while (rs.next()) {
          scores.add(new Score(rs.getString(1),
                  rs.getString(2),
                  rs.getInt(3),
                  rs.getTimestamp(4)));
        }
        return scores;
      }
    } catch (SQLException e) {
      throw new ScoreException("Problem selecting score", e);
    }
  }

  @Override
  public void reset() {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement statement = connection.createStatement()
    ) {
      statement.executeUpdate(DELETE);
    } catch (SQLException e) {
      throw new ScoreException("Problem deleting score", e);
    }
  }
}
