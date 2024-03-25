package dev.tiltrikt.tetravex.core.repository;

import dev.tiltrikt.tetravex.core.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer>  {
}
