package dev.tiltrikt.tetravex.service.comment;

import dev.tiltrikt.tetravex.exception.CommentException;
import dev.tiltrikt.tetravex.model.Comment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CommentService {

    void addComment(@NotNull Comment comment) throws CommentException;
    @NotNull List<Comment> getComments(@NotNull String game) throws CommentException;
    void reset() throws CommentException;
}
