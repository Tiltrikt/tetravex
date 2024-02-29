package dev.tiltrikt.model;

import dev.tiltrikt.exception.BusyField;
import dev.tiltrikt.exception.EmptyField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@SuppressWarnings("OptionalGetWithoutIsPresent")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Field {

  int size;

  @NotNull
  List<Optional<Tile>> tileList = new ArrayList<>();

  public static Field getEmptyField(int size) {
    Field field = new Field(size);
    for (int i = 0; i < size * size; i++) {
      field.getTileList().add(Optional.empty());
    }
    return field;
  }

  public static Field getFullField(int size) {
    Field field = new Field(size);
    for (int i = 0; i < size * size; i++) {
      field.fulfillField();
    }
    return field;
  }

  public boolean isSolved() {
    if (!isFull()) return false;

    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {

        if (!inCompositeWithUp(y, x)) return false;
        if (!inCompositeWithDown(y, x)) return false;
        if (!inCompositeWithLeft(y, x)) return false;
        if (!inCompositeWithRight(y, x)) return false;

      }
    }

    return true;
  }

  private void fulfillField() {
    tileList.clear();
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        Tile tile = Tile.generate();
        tileList.add(Optional.of(tile));
        makeCompositeWithUp(tile, y, x);
        makeCompositeWithLeft(tile, y, x);
      }
    }
    Collections.shuffle(tileList);
  }

  public Tile removeTile(int y, int x) {
    Tile tile =  tileList.get(y * size + x).orElseThrow(EmptyField::new);
    tileList.set(y * size + x, Optional.empty());
    return tile;
  }

  public void addTile(Tile tile, int y, int x) {
    if (tileList.get(y * size + x).isPresent())
      throw new BusyField();
    tileList.set(y * size + x, Optional.of(tile));
  }

  private boolean isFull() {
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        if (tileList.get(y * size + x).isEmpty()) return false;
      }
    }
    return true;
  }

  private boolean inCompositeWithUp(int row, int column) {
    if (row == 0) return true;
    return getTileNumber(row, column, 'U') == tileList.get(row * size + column).get().getUpNumber();
  }

  private boolean inCompositeWithDown(int row, int column) {
    if (row == size - 1) return true;
    return getTileNumber(row, column, 'D') == tileList.get(row * size + column).get().getDownNumber();
  }

  private boolean inCompositeWithLeft(int row, int column) {
    if (column == 0) return true;
    return getTileNumber(row, column, 'L') == tileList.get(row * size + column).get().getLeftNumber();
  }

  private boolean inCompositeWithRight(int row, int column) {
    if (column == size - 1) return true;
    return getTileNumber(row, column, 'R') == tileList.get(row * size + column).get().getRightNumber();
  }

  private void makeCompositeWithUp(Tile tile, int row, int column) {
    if (inCompositeWithUp(row, column)) return;
    tile.setUpNumber(getTileNumber(row, column, 'U'));
  }

  private void makeCompositeWithLeft(Tile tile, int row, int column) {
    if (inCompositeWithLeft(row, column)) return;
    tile.setLeftNumber(getTileNumber(row, column, 'L'));
  }

  private int getTileNumber(int row, int column, char direction) {
    return switch (direction) {
      case 'U' -> tileList.get((row - 1) * size + column).get().getDownNumber();
      case 'D' -> tileList.get((row + 1) * size + column).get().getUpNumber();
      case 'L' -> tileList.get(row * size + column - 1).get().getRightNumber();
      case 'R' -> tileList.get(row * size + column + 1).get().getLeftNumber();
      default -> throw new IllegalStateException("Unexpected value: " + direction);
    };
  }
}
