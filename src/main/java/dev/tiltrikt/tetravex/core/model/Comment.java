package dev.tiltrikt.tetravex.core.model;

import jakarta.persistence.*;
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
@Table(name = "comment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment{

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
  @Column(name = "comment", nullable = false)
  String comment;
}
