package dev.tiltrikt.tetravex.service.game;

import dev.tiltrikt.tetravex.model.Field;

public enum FieldType {
  GENERATED {
    @Override
    public Field chooseField(Field generatedField, Field playField) {
      return generatedField;
    }
  },
  PLAYFIELD {
    @Override
    public Field chooseField(Field generatedField, Field playField) {
      return playField;
    }
  };

  public abstract Field chooseField(Field generatedField, Field playField);
}
