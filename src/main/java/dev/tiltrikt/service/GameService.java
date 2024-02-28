package dev.tiltrikt.service;

import dev.tiltrikt.model.Field;

public interface GameService {

  void generateField(int size);

  void replaceTile(Field fromField, int fromRow, int fromColumn, Field toField, int toRow, int toColumn);
}
