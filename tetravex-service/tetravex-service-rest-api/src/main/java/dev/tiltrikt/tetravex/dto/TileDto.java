package dev.tiltrikt.tetravex.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TileDto {

  int upNumber;

  int downNumber;

  int rightNumber;

  int leftNumber;

}
