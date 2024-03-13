package dev.tiltrikt.tetravex.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score {

  String game;

  String player;

  int points;

  Date playedOn;
}
