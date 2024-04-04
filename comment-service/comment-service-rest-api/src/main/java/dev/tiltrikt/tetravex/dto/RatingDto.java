package dev.tiltrikt.tetravex.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingDto {

  @NotBlank(message = "Game cannot be empty")
  String game;

  @NotBlank(message = "Player cannot be empty")
  String player;

  @NotBlank(message = "Comment cannot be empty")
  String comment;
}

