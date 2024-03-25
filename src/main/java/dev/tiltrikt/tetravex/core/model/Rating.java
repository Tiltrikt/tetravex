package dev.tiltrikt.tetravex.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "rating")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rating {

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
  @Column(name = "rating", nullable = false)
  @Min(1)
  @Max(100)
  Integer rating;
}
