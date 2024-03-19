package dev.tiltrikt.tetravex.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Score score = (Score) o;
    return points == score.points && Objects.equals(game, score.game) && Objects.equals(player, score.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(game, player, points);
  }
}
