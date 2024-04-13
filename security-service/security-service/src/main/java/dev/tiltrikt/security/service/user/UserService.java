package dev.tiltrikt.security.service.user;

import dev.tiltrikt.security.exception.user.UserNotFoundException;
import dev.tiltrikt.security.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface UserService {

  @NotNull User getUser(@NotNull String username) throws UserNotFoundException;

  @NotNull User getUser(int id) throws UserNotFoundException;

  @NotNull Optional<User> getUserOptional(@NotNull String username);

  @NotNull Optional<User> getUserOptional(int id);

  @NotNull User save(@NotNull User user);
}
