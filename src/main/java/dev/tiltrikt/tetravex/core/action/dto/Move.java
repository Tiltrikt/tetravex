package dev.tiltrikt.tetravex.core.action.dto;

import dev.tiltrikt.tetravex.core.service.game.model.FieldType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Move {

  @NotNull FieldType fromField;
  int fromRow;
  int fromColumn;

  @NotNull FieldType toField;
  int toRow;
  int toColumn;
}
