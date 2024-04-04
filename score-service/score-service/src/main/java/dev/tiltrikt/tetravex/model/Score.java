package dev.tiltrikt.tetravex.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "score")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score extends BaseModel {

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
    return points.equals(score.points) && Objects.equals(getGame(), score.getGame()) &&
        Objects.equals(getPlayer(), score.getPlayer());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getGame(), getPlayer(), points);
  }
}
