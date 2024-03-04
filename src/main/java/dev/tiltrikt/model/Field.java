package dev.tiltrikt.model;

import dev.tiltrikt.exception.BusyFieldException;
import dev.tiltrikt.exception.EmptyFieldException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Getter
@RequiredArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Field {

  int size;

  @NotNull List<Optional<Tile>> tileList = new ArrayList<>();


  public Tile removeTile(int y, int x) throws EmptyFieldException {
    Tile tile = tileList.get(y * size + x).orElseThrow(() -> new EmptyFieldException("This position is empty"));
    tileList.set(y * size + x, Optional.empty());
    return tile;
  }

  public void addTile(Tile tile, int y, int x) throws BusyFieldException {
    if (tileList.get(y * size + x).isPresent()) throw new BusyFieldException("This position already have tile");
    tileList.set(y * size + x, Optional.of(tile));
  }

  public boolean isSolved() {
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        Tile currentTile = tileList.get(y * size + x).orElse(null);
        if (currentTile == null) return false;

        if (x != 0) {
          Tile leftTile = tileList.get(y * size + x - 1).get();
          if (currentTile.getLeftNumber() != leftTile.getRightNumber()) return false;
        }
        if (y != 0) {
          Tile aboveTile = tileList.get((y - 1) * size + x).get();
          if (currentTile.getUpNumber() != aboveTile.getDownNumber()) return false;
        }
      }
    }
    return true;
  }
}
