package dev.tiltrikt.model;

import dev.tiltrikt.Main;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tile {

  public static Tile generate() {
    Random random = new Random();
    return new Tile(random.nextInt(9) + 1,
            random.nextInt(9) + 1,
            random.nextInt(9) + 1,
            random.nextInt(9) + 1);
  }

  int upNumber;

  int downNumber;

  int rightNumber;

  int leftNumber;

}
