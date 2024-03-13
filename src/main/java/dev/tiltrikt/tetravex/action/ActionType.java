package dev.tiltrikt.tetravex.action;

import org.jetbrains.annotations.NotNull;

public enum ActionType {

  MOVE,
  SCORE,
  RATING;

  public static @NotNull ActionType resolveAction(@NotNull String action) {
    return switch (action.toLowerCase()) {
      case "move" -> MOVE;
      case "score" -> SCORE;
      case "rating" -> RATING;
      default -> RATING;
    };
  }
}
