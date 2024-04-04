package dev.tiltrikt.tetravex.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingAddRequest {

  @NotBlank(message = "Game cannot be empty")
  String game;

  @Min(1)
  @Max(100)
  @NotNull(message = "Rating cannot be null")
  Integer rating;
}
