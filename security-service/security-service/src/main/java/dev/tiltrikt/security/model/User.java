package dev.tiltrikt.security.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @Column(nullable = false, unique = true)
  @NotNull String username;

  @Column(unique = true)
  @Nullable String email;

  @Column(nullable = false, updatable = false)
  @NotNull String password;
}
