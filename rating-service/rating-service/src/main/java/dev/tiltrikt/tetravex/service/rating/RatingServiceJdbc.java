package dev.tiltrikt.tetravex.service.rating;

import dev.tiltrikt.tetravex.configuration.DataBaseConfiguration;
import dev.tiltrikt.tetravex.exception.RatingException;
import dev.tiltrikt.tetravex.model.Rating;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.sql.*;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuppressWarnings("SqlDialectInspection")
public class RatingServiceJdbc implements RatingService {

  public static final String TABLE = "CREATE TABLE IF NOT EXISTS rating(game varchar(30), player varchar(30), rating int CHECK(rating >= 0 AND rating <= 100), PRIMARY KEY (game, player))";
  public static final String RATING = "SELECT rating FROM rating WHERE game = ? AND player = ?";
  public static final String AVERAGE = "SELECT ROUND(AVG(rating)) FROM rating WHERE game = ?";
  public static final String DELETE = "DELETE FROM rating";
  public static final String INSERT = "INSERT INTO rating (game, player, rating) VALUES (?, ?, ?)";

  DataBaseConfiguration configuration;

  public RatingServiceJdbc(@NotNull DataBaseConfiguration configuration) {
    this.configuration = configuration;
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
         PreparedStatement statement = connection.prepareStatement(TABLE)
    ) {
      statement.execute();
    } catch (SQLException e) {
      throw new RatingException("Problem creating table", e);
    }
  }

  @Override
  public void setRating(Rating rating) {
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
         PreparedStatement statement = connection.prepareStatement(INSERT)
    ) {
      statement.setString(1, rating.getGame());
      statement.setString(2, rating.getPlayer());
      statement.setInt(3, rating.getPoints());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RatingException("Problem inserting rating", e);
    }
  }

  @Override
  public int getAverageRating(String game) {
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
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
  public int getRating(String game, String player) {
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
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
    try (Connection connection = DriverManager.getConnection(
        configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
         Statement statement = connection.createStatement()
    ) {
      statement.executeUpdate(DELETE);
    } catch (SQLException e) {
      throw new RatingException("Problem deleting score", e);
    }
  }
}
