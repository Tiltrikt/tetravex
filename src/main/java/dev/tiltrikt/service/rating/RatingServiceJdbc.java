package dev.tiltrikt.service.rating;

import dev.tiltrikt.entity.Rating;
import dev.tiltrikt.exception.RatingException;

import java.sql.*;

public class RatingServiceJdbc implements RatingService {

  public static final String URL = "jdbc:postgresql://localhost:5432/tetravex";
  public static final String USER = "postgres";
  public static final String PASSWORD = "postgres";

  public static final String TABLE = "CREATE TABLE IF NOT EXISTS rating(game varchar(30), player varchar(30), rating int, PRIMARY KEY (game, player))";
  public static final String RATING = "SELECT rating FROM rating WHERE game = ? AND player = ?";
  public static final String AVERAGE = "SELECT ROUND(AVG(rating)) FROM rating WHERE game = ?";
  public static final String DELETE = "DELETE FROM rating";
  public static final String INSERT = "INSERT INTO rating (game, player, rating) VALUES (?, ?, ?)";

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
  public void setRating(Rating rating) throws RatingException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(INSERT)
    ) {
      statement.setString(1, rating.getGame());
      statement.setString(2, rating.getPlayer());
      statement.setInt(3, rating.getRating());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RatingException("Problem inserting score", e);
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
         Statement statement = connection.createStatement();
    ) {
      statement.executeUpdate(DELETE);
    } catch (SQLException e) {
      throw new RatingException("Problem deleting score", e);
    }
  }
}
