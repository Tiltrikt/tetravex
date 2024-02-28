package dev.tiltrikt.model;

import dev.tiltrikt.exception.BusyField;
import dev.tiltrikt.exception.EmptyField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@SuppressWarnings("OptionalGetWithoutIsPresent")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Field {

  int size;

  @NotNull
  List<Optional<Tile>> tileList = new ArrayList<>();

  boolean isSolved() {
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

  public Tile removeTile(int y, int x) {
    return tileList.get(y * size + x).orElseThrow(EmptyField::new);
  }

  public void addTile(Tile tile, int y, int x) {
    tileList.get(y * size + x).orElseThrow(BusyField::new);
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
    if (row == 0)
      return true;

    return tileList.get((row - 1) * size + column).get().getDownNumber() ==
            tileList.get(row * size + column).get().getUpNumber();
  }

  private boolean inCompositeWithDown(int row, int column) {
    if (row == size - 1)
      return true;
    return tileList.get((row + 1) * size + column).get().getUpNumber() ==
            tileList.get(row * size + column).get().getDownNumber();
  }

  private boolean inCompositeWithLeft(int row, int column) {
    if (column == 0)
      return true;
    return tileList.get(row * size + column - 1).get().getRightNumber() ==
            tileList.get(row * size + column).get().getLeftNumber();
  }

  private boolean inCompositeWithRight(int row, int column) {
    if (column == size - 1)
      return true;
    return tileList.get(row * size + column + 1).get().getLeftNumber() ==
            tileList.get(row * size + column).get().getRightNumber();
  }
}
