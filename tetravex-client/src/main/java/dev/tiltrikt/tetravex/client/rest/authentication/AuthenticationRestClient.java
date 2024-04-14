package dev.tiltrikt.tetravex.client.rest.authentication;

import dev.tiltrikt.tetravex.dto.request.AuthenticationRequest;
import dev.tiltrikt.tetravex.dto.request.RegistrationRequest;
import dev.tiltrikt.tetravex.dto.response.TokenResponse;
import org.jetbrains.annotations.NotNull;

public interface AuthenticationRestClient {

  @NotNull TokenResponse authenticate(@NotNull AuthenticationRequest authenticationRequest);

  @NotNull TokenResponse register(@NotNull RegistrationRequest registrationRequest);
}
