package dev.tiltrikt.tetravex.core.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "score")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score {

  @Id
  @GeneratedValue
  int id;

  @NotNull
  @Column(name = "game", nullable = false)
  String game;

  @NotNull
  @Column(name = "player", nullable = false)
  String player;

  @NotNull
  @Column(name = "points")
  Integer points;

  @NotNull
  @Column(name = "date", nullable = false)
  Date playedOn;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Score score = (Score) o;
    return points.equals(score.points) && Objects.equals(game, score.game) && Objects.equals(player, score.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(game, player, points);
  }
}
