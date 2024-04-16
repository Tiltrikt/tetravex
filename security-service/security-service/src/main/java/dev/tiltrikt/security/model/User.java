package dev.tiltrikt.security.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id = 0;

  @Column(nullable = false, unique = true)
  @NotNull String username;

  @Column(nullable = false, updatable = false)
  @NotNull String password;

  @Singular("authority")
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @NotNull List<Authority> authoritiesList;

  @Builder
  public static User authorities(@NotNull String username, @NotNull String password, @Singular List<AuthorityType> authorities) {
    List<Authority> authorityList = new ArrayList<>();
    for (AuthorityType authority : authorities) {
      authorityList.add(new Authority(authority));
    }
    return new User(username, password, authorityList);
  }
}
