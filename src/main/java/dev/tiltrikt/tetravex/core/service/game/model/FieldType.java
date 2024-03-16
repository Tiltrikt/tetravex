package dev.tiltrikt.tetravex.core.service.game.model;

import org.jetbrains.annotations.NotNull;

public enum FieldType {
  GENERATED {
    @Override
    public @NotNull Field chooseField(@NotNull Field generatedField, @NotNull Field playField) {
      return generatedField;
    }
  },
  PLAYFIELD {
    public @NotNull Field chooseField(@NotNull Field generatedField, @NotNull Field playField) {
      return playField;
    }
  };

  public abstract @NotNull Field chooseField(@NotNull Field generatedField, @NotNull Field playField);

//  public static @NotNull FieldType resolveFieldType(@NotNull String input, @NotNull FieldType defaultType) {
//    FieldType fromField = input.equalsIgnoreCase("above") ? FieldType.PLAYFIELD : FieldType.GENERATED;
//  }
}
