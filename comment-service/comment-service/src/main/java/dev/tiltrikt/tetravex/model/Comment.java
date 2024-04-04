package dev.tiltrikt.tetravex.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@Table(name = "comment")
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseModel{

  @NotNull
  @Column(name = "comment", nullable = false)
  String comment;
}
