package dev.tiltrikt.tetravex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseModel {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.AUTO)
  int id;

  @NotNull
  @Column(name = "game", nullable = false)
  String game;

  @NotNull
  @Column(name = "player", nullable = false)
  String player;
}
