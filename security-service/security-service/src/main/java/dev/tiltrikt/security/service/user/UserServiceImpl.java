package dev.tiltrikt.security.service.user;

import dev.tiltrikt.security.exception.user.UserNotFoundException;
import dev.tiltrikt.security.model.User;
import dev.tiltrikt.security.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

  @NotNull UserRepository userRepository;

  @Override
  public @NotNull User getUser(@NotNull String username) throws UserNotFoundException {
    return userRepository.findUserByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("User with username %s not found!", username));
  }

  @Override
  public @NotNull User getUser(int id) throws UserNotFoundException {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id %d not found!", id));
  }

  @Override
  public @NotNull Optional<User> getUserOptional(@NotNull String username) {
    return userRepository.findUserByUsername(username);
  }

  @Override
  public @NotNull Optional<User> getUserOptional(int id) {
    return userRepository.findById(id);
  }

  @Override
  public @NotNull User save(@NotNull User user) {
    return userRepository.save(user);
  }
}
