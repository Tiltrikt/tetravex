package dev.tiltrikt.tetravex.core.service.rating;

import dev.tiltrikt.tetravex.core.exception.RatingException;
import dev.tiltrikt.tetravex.core.model.Rating;

import java.sql.*;

import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.*;

@SuppressWarnings("SqlDialectInspection")
public class RatingServiceJdbc implements RatingService {

  public static final String TABLE = "CREATE TABLE IF NOT EXISTS rating(game varchar(30), player varchar(30), rating int CHECK(rating >= 0 AND rating <= 100), PRIMARY KEY (game, player))";
  public static final String RATING = "SELECT rating FROM rating WHERE game = ? AND player = ?";
  public static final String AVERAGE = "SELECT ROUND(AVG(rating)) FROM rating WHERE game = ?";
  public static final String DELETE = "DELETE FROM rating";
  public static final String INSERT = "INSERT INTO rating (game, player, rating) VALUES (?, ?, ?)";

  public RatingServiceJdbc() {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(TABLE)
    ) {
      statement.execute();
    } catch (SQLException e) {
      throw new RatingException("Problem creating table", e);
    }
  }

  @Override
  public void setRating(Rating rating) throws RatingException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(INSERT)
    ) {
      statement.setString(1, rating.getGame());
      statement.setString(2, rating.getPlayer());
      statement.setInt(3, rating.getRating());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RatingException("Problem inserting rating", e);
    }
  }

  @Override
  public int getAverageRating(String game) throws RatingException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(AVERAGE)
    ) {
      statement.setString(1, game);
      try (ResultSet rs = statement.executeQuery()) {
        rs.next();
        return rs.getInt(1);
      }
    } catch (SQLException e) {
      throw new RatingException("Problem getting average rating", e);
    }
  }

  @Override
  public int getRating(String game, String player) throws RatingException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(RATING)
    ) {
      statement.setString(1, game);
      statement.setString(2, player);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          return rs.getInt(1);
        } else throw new RatingException("User or game not found");
      }
    } catch (SQLException e) {
      throw new RatingException("Problem getting rating", e);
    }
  }

  @Override
  public void reset() {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement statement = connection.createStatement()
    ) {
      statement.executeUpdate(DELETE);
    } catch (SQLException e) {
      throw new RatingException("Problem deleting score", e);
    }
  }
}
