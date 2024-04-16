package dev.tiltrikt.security.service.authentication;

import dev.tiltrikt.security.exception.MyAuthenticationException;
import dev.tiltrikt.security.exception.user.UserAlreadyRegisteredException;
import dev.tiltrikt.security.model.User;
import dev.tiltrikt.security.service.user.UserService;
import dev.tiltrikt.tetravex.dto.request.AuthenticationRequest;
import dev.tiltrikt.tetravex.dto.request.RegistrationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {

  @NotNull UserService userService;

  @NotNull AuthenticationManager authenticationManager;

  @NotNull PasswordEncoder passwordEncoder;

  @Override
  public @NotNull Authentication authenticate(@NotNull AuthenticationRequest request) {
    Authentication authentication = new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
    );

    try {
      return authenticationManager.authenticate(authentication);
    } catch (AuthenticationException exception) {
      throw new MyAuthenticationException(exception.getMessage());
    }
  }

  @NotNull
  @Override
  public Authentication register(@NotNull RegistrationRequest request) throws UserAlreadyRegisteredException {
    userService.getUserOptional(request.getUsername())
        .ifPresent(user -> {
          throw new UserAlreadyRegisteredException(
              "User with username %s already registered",
              request.getUsername()
          );
        });

    String encodedPassword = passwordEncoder.encode(request.getPassword());

    userService.save(
        User.builder()
            .username(request.getUsername())
            .password(encodedPassword)
            .build()
    );

    Authentication authentication = new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
    );
    return authenticationManager.authenticate(authentication);
  }
}
