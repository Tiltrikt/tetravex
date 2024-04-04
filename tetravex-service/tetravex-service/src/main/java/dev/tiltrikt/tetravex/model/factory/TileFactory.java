package dev.tiltrikt.tetravex.model.factory;

import dev.tiltrikt.tetravex.model.Tile;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
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
