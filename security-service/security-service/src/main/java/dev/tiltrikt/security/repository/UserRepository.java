package dev.tiltrikt.security.repository;

import dev.tiltrikt.security.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

  @NotNull Optional<User> findUserByUsername(@NotNull String username);

}
