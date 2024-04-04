package dev.tiltrikt.tetravex.repository;

import dev.tiltrikt.tetravex.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>  {
}
