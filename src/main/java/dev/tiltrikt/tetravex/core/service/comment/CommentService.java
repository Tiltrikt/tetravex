package dev.tiltrikt.tetravex.core.service.comment;

import dev.tiltrikt.tetravex.core.exception.CommentException;
import dev.tiltrikt.tetravex.core.model.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
    void reset() throws CommentException;
}
