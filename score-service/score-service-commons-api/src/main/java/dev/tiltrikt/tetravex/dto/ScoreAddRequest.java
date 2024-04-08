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
public class ScoreAddRequest {

  @NotBlank(message = "Game cannot be empty")
  String game;

  @NotNull(message = "Score cannot be null")
  Integer points;

  @NotNull(message = "Score cannot be null")
  Date playedOn;
}
