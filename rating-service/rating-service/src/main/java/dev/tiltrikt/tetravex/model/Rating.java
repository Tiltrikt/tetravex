package dev.tiltrikt.tetravex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "rating", uniqueConstraints =
@UniqueConstraint(columnNames = {"player", "game"}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rating extends BaseModel {

  @NotNull
  @Column(name = "rating", nullable = false)
  @Min(1)
  @Max(100)
  Integer rating;
}
