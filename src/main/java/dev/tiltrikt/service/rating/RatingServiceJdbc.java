package dev.tiltrikt.service.rating;

import dev.tiltrikt.entity.Score;
import dev.tiltrikt.exception.RatingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingServiceJdbc implements RatingService {

  public static final String URL = "jdbc:postgresql://localhost:5432/tetravex";
  public static final String USER = "postgres";
  public static final String PASSWORD = "postgres";

  public static final String TABLE = "CREATE TABLE IF NOT EXISTS rating(id int AUTO_INCREMENT, game varchar(30),player varchar(30), points int, played_on date)";
  public static final String SELECT = "SELECT game, player, points, playedOn FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
  public static final String DELETE = "DELETE FROM score";
  public static final String INSERT = "INSERT INTO score (game, player, points, playedOn) VALUES (?, ?, ?, ?)";

  public RatingServiceJdbc() {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(TABLE);
    ) {
      statement.execute();
    } catch (SQLException e) {
      throw new RatingException("Problem creating table", e);
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
      throw new RatingException("Problem inserting score", e);
    }
  }

  @Override
  public List<Score> getTopRating(String game) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(SELECT);
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
      throw new RatingException("Problem selecting score", e);
    }
  }

  @Override
  public void reset() {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement statement = connection.createStatement();
    ) {
      statement.executeUpdate(DELETE);
    } catch (SQLException e) {
      throw new RatingException("Problem deleting score", e);
    }
  }
}
