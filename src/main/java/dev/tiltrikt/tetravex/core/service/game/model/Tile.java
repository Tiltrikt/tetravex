package dev.tiltrikt.tetravex.core.service.game.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tile {

  int upNumber;

  int downNumber;

  int rightNumber;

  int leftNumber;

}
