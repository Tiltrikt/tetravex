package dev.tiltrikt.tetravex.core.service.game.factory;

import dev.tiltrikt.tetravex.core.service.game.model.Field;
import dev.tiltrikt.tetravex.core.service.game.model.Tile;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@SuppressWarnings("OptionalGetWithoutIsPresent")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FieldFactory {

  TileFactory tileFactory = new TileFactory();

  public Field getEmpty(int size) {
    Field field = new Field(size);
    field.getTileList().addAll(Collections.nCopies(size * size, Optional.empty()));
    return field;
  }

  public Field getGenerated(int size) {
    Field field = new Field(size);
    List<Optional<Tile>> tileList = field.getTileList();

    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        Tile tile = tileFactory.getRandomTile();
        tileList.add(Optional.of(tile));

        if (x != 0) {
          Tile leftTile = tileList.get(y * size + x - 1).get();
          tile.setLeftNumber(leftTile.getRightNumber());
        }
        if (y != 0) {
          Tile aboveTile = tileList.get((y - 1) * size + x).get();
          tile.setUpNumber(aboveTile.getDownNumber());
        }

      }
    }

    Collections.shuffle(tileList);
    return field;
  }
}
