package dev.tiltrikt.service;

import dev.tiltrikt.exception.BusyField;
import dev.tiltrikt.exception.EmptyField;
import dev.tiltrikt.model.Field;
import dev.tiltrikt.model.Tile;

public class GameServiceImpl implements GameService {
  @Override
  public void generateField(int size) {

  }

  @Override
  public void replaceTile(Field fromField, int fromRow, int fromColumn, Field toField, int toRow, int toColumn) {
    Tile tile;
    try {
      tile = fromField.removeTile(fromRow - 1, fromColumn - 1);
    } catch (EmptyField exception)
    {
      throw exception;
    }

    try {
      toField.addTile(tile, toRow - 1, toColumn - 1);
    } catch (BusyField exception)
    {
      fromField.addTile(tile, fromRow - 1, fromColumn - 1);
    }
  }

}
