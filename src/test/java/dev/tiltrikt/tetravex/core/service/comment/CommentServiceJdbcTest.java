package dev.tiltrikt.tetravex.core.service.comment;

import dev.tiltrikt.tetravex.core.exception.CommentException;
import dev.tiltrikt.tetravex.core.model.Comment;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static dev.tiltrikt.tetravex.core.configuration.GameConfiguration.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SqlDialectInspection")
class CommentServiceJdbcTest {

  CommentService commentService;

  @BeforeEach
  void createService() {

    // connection error check
    assertDoesNotThrow(() -> commentService = new CommentServiceJdbc());
    commentService.reset();
  }

  @Test
  void onAddingCommentMustBeAdded() {

    Comment comment = new Comment("x", "x", "x");
    commentService.addComment(comment);
    System.out.println(comment.getComment());
    System.out.println(commentService.getComments("x").getFirst().getComment());
    assertEquals(commentService.getComments("x").getFirst(), comment);
  }

  @Test
  void afterResettingTableMustBeEmpty() {

    Comment comment = new Comment("x", "x", "x");
    commentService.addComment(comment);
    commentService.reset();
    assertEquals(0, commentService.getComments("x").size());
  }

  @SneakyThrows
  @Test
  void onCreatingServiceIfNoTablePresentNewMustBeCreated() {

    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    connection.prepareStatement("DROP TABLE IF EXISTS comment").execute();

    commentService = new CommentServiceJdbc();
    assertDoesNotThrow(() -> connection.prepareStatement("SELECT * FROM comment").execute());
  }

  @SneakyThrows
  @Test
  void ifNoTablePresentEveryMethodMustThrowException() {

    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    connection.prepareStatement("DROP TABLE IF EXISTS comment").execute();

    assertThrows(CommentException.class, () -> commentService.addComment(new Comment("x", "x", "x")));
    assertThrows(CommentException.class, () -> commentService.getComments("x"));
    assertThrows(CommentException.class, () -> commentService.reset());
  }
}