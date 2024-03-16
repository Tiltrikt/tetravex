package dev.tiltrikt.tetravex.core.action;

import org.jetbrains.annotations.NotNull;

public enum ActionType {

  START,
  MOVE,
  SCORE,
  RATING,
  COMMENT,
  HELP,
  EXIT;

  public static @NotNull ActionType resolveAction(@NotNull String action) {
    return switch (action.toLowerCase()) {
      case "start" -> START;
      case "move" -> MOVE;
      case "score" -> SCORE;
      case "rating" -> RATING;
      case "comment" -> COMMENT;
      case "exit" -> EXIT;
      default -> HELP;
    };
  }
}
