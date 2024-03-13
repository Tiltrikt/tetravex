package dev.tiltrikt.tetravex.action.details;

import dev.tiltrikt.tetravex.service.game.FieldType;
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
