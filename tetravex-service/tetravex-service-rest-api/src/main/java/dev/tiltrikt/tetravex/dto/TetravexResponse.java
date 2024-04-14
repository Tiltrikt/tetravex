package dev.tiltrikt.tetravex.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TetravexResponse {

  @Nullable FieldDto generatedField;

  @Nullable FieldDto playField;

  @Builder.Default
  boolean win = false;

  int score;
}
