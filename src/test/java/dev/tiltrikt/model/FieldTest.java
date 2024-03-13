package dev.tiltrikt.model;

import dev.tiltrikt.tetravex.exception.BusyFieldException;
import dev.tiltrikt.tetravex.exception.EmptyFieldException;
import dev.tiltrikt.tetravex.model.Field;
import dev.tiltrikt.tetravex.model.Tile;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

  @Test
  void onRemovingTileFromEmptyFieldExceptionMustBeThrown() {
    Field emptyField = new Field(1);
    emptyField.getTileList().add(Optional.empty());
    assertThrows(EmptyFieldException.class, () -> emptyField.removeTile(0, 0));
  }

  @Test
  void onRemovingTileFromFieldTileMustBeReturned() {
    Field field = new Field(1);
    Tile tile = new Tile(1,2,3,4);
    field.getTileList().add(Optional.of(tile));
    assertEquals(field.removeTile(0,0), tile);
  }

  @Test
  void onAddingTileToBusyFieldExceptionMustBeThrown() {
    Field field = new Field(1);
    Tile tile = new Tile(1,2, 3,4);
    field.getTileList().add(Optional.of(tile));
    assertThrows(BusyFieldException.class, () -> field.addTile(tile, 0, 0));
  }

  @Test
  void onAddingTileToEmptyFieldNothingMustBeThrown() {
    Field emptyField = new Field(1);
    emptyField.getTileList().add(Optional.empty());
    Tile tile = new Tile(1,2, 3,4);
    assertDoesNotThrow(() -> emptyField.addTile(tile, 0, 0));
  }

  @Test
  void ifFieldIsSolvedMustReturnTrue() {
    Field field = new Field(2);
    Tile tile = new Tile(1,1,1,1);
    for (int i = 0; i < 4; i++) {
      field.getTileList().add(Optional.of(tile));
    }
    assertTrue(field.isSolved());
  }

  @Test
  void ifFieldIsNotSolvedMustReturnFalse() {
    Field field = new Field(2);
    Tile tile = new Tile(1,2,3,4);
    for (int i = 0; i < 4; i++) {
      field.getTileList().add(Optional.of(tile));
    }
    assertFalse(field.isSolved());
  }

  @Test
  void ifFieldIsNotFullMustReturnFalse() {
    Field field = new Field(2);
    for (int i = 0; i < 1; i++) {
      field.getTileList().add(Optional.empty());
    }
    assertFalse(field.isSolved());
  }
}