package dev.tiltrikt.service.comment;

import dev.tiltrikt.entity.Comment;
import dev.tiltrikt.exception.CommentException;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
    void reset() throws CommentException;
}
