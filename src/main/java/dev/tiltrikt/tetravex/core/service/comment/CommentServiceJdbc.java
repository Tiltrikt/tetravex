package dev.tiltrikt.tetravex.core.service.comment;

import dev.tiltrikt.tetravex.core.exception.CommentException;
import dev.tiltrikt.tetravex.core.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.*;

@SuppressWarnings("SqlDialectInspection")
public class CommentServiceJdbc implements CommentService {

  public static final String TABLE = "CREATE TABLE IF NOT EXISTS comment(game varchar(30), player varchar(30), comment varchar(500))";
  public static final String DELETE = "DELETE FROM comment";
  public static final String INSERT = "INSERT INTO comment (game, player, comment) VALUES (?, ?, ?)";
  public static final String SELECT = "SELECT game, player, comment FROM comment WHERE game = ?";


  public CommentServiceJdbc() {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(TABLE)
    ) {
      statement.execute();
    } catch (SQLException e) {
      throw new CommentException("Problem creating table", e);
    }
  }

  @Override
  public void addComment(Comment comment) throws CommentException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(INSERT)
    ) {
      statement.setString(1, comment.getGame());
      statement.setString(2, comment.getPlayer());
      statement.setString(3, comment.getComment());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new CommentException("Problem inserting comment", e);
    }
  }

  @Override
  public List<Comment> getComments(String game) throws CommentException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(SELECT)
    ) {
      statement.setString(1, game);
      try (ResultSet rs = statement.executeQuery()) {
        List<Comment> comments = new ArrayList<>();
        while (rs.next()) {
          comments.add(Comment.builder()
                  .game(rs.getString(1))
                  .player(rs.getString(2))
                  .comment(rs.getString(3))
                  .build());
        }
        return comments;
      }
    } catch (SQLException e) {
      throw new CommentException("Problem selecting comment", e);
    }
  }

  @Override
  public void reset() throws CommentException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement statement = connection.createStatement()
    ) {
      statement.executeUpdate(DELETE);
    } catch (SQLException e) {
      throw new CommentException("Problem deleting comment", e);
    }
  }
}
