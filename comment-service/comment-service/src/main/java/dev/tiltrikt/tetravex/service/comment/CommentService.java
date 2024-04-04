package dev.tiltrikt.tetravex.service.comment;

import dev.tiltrikt.tetravex.exception.CommentException;
import dev.tiltrikt.tetravex.model.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
    void reset() throws CommentException;
}
