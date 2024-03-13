package dev.tiltrikt.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

  @NotNull String game;

  @NotNull String player;

  @NotNull String comment;
}
