package dev.tiltrikt.tetravex.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameStartRequest {

  @Min(1)
  @Max(5)
  @NotNull(message = "Size cannot be null")
  Integer size;
}
