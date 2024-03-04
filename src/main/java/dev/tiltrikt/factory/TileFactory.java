package dev.tiltrikt.factory;

import dev.tiltrikt.model.Tile;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TileFactory {

  Random random = new Random();

  public Tile getRandomTile() {
    return new Tile(random.nextInt(9) + 1,
              random.nextInt(9) + 1,
              random.nextInt(9) + 1,
              random.nextInt(9) + 1);
  }
}
