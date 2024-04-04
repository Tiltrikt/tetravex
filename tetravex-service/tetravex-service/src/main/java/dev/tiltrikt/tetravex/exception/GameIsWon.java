package dev.tiltrikt.tetravex.exception;

import dev.tiltrikt.commons.exception.GameException;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameIsWon extends GameException {

  int points;

  String player;

  public GameIsWon() {
    super();
  }
}
