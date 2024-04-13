package dev.tiltrikt.security.service.authentication;

import dev.tiltrikt.security.api.request.AuthenticationRequest;
import dev.tiltrikt.security.api.request.RegistrationRequest;
import dev.tiltrikt.security.exception.user.UserAlreadyRegisteredException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {

  void authenticate(@NotNull AuthenticationRequest request) throws AuthenticationException;

  void register(@NotNull RegistrationRequest request) throws UserAlreadyRegisteredException;
}
