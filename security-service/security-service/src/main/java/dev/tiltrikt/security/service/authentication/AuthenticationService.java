package dev.tiltrikt.security.service.authentication;

import dev.tiltrikt.security.exception.user.UserAlreadyRegisteredException;
import dev.tiltrikt.tetravex.dto.request.AuthenticationRequest;
import dev.tiltrikt.tetravex.dto.request.RegistrationRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {

  void authenticate(@NotNull AuthenticationRequest request) throws AuthenticationException;

  void register(@NotNull RegistrationRequest request) throws UserAlreadyRegisteredException;
}