package dev.tiltrikt.tetravex.dto;

import dev.tiltrikt.tetravex.field.FieldType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveMakeRequest {

  @NotNull(message = "Field cannot be null")
  FieldType fromField;
  int fromRow;
  int fromColumn;

  @NotNull(message = "Field cannot be null")
  FieldType toField;
  int toRow;
  int toColumn;
}
