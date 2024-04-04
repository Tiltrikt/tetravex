package dev.tiltrikt.tetravex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScoreDto {

  @NotBlank(message = "Game cannot be empty")
  String game;

  @NotBlank(message = "Player cannot be empty")
  String player;

  @NotNull(message = "Points cannot be null")
  Integer points;

  @NotNull(message = "Date cannot be null")
  Date playedOn;
}
