package dev.tiltrikt.tetravex.core.service.game.model;

import lombok.*;
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
