package dev.tiltrikt.security.service.authentication;

import dev.tiltrikt.security.exception.user.UserAlreadyRegisteredException;
import dev.tiltrikt.tetravex.dto.request.AuthenticationRequest;
import dev.tiltrikt.tetravex.dto.request.RegistrationRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {

  @NotNull Authentication authenticate(@NotNull AuthenticationRequest request) throws AuthenticationException;

  @NotNull Authentication register(@NotNull RegistrationRequest request) throws UserAlreadyRegisteredException;
}