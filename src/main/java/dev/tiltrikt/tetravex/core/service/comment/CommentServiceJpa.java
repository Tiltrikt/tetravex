package dev.tiltrikt.tetravex.core.service.comment;

import dev.tiltrikt.tetravex.core.exception.CommentException;
import dev.tiltrikt.tetravex.core.model.Comment;
import dev.tiltrikt.tetravex.core.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceJpa implements CommentService {

  CommentRepository commentRepository;

  @Override
  public void addComment(Comment comment) throws CommentException {
    commentRepository.save(comment);
  }

  @Override
  public List<Comment> getComments(String game) throws CommentException {
    return commentRepository.findAll();
  }

  @Override
  public void reset() throws CommentException {
    commentRepository.deleteAll();
  }
}
