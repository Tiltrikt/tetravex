package dev.tiltrikt.tetravex.client.rest.authentication;

import dev.tiltrikt.tetravex.dto.request.AuthenticationRequest;
import dev.tiltrikt.tetravex.dto.request.RegistrationRequest;
import dev.tiltrikt.tetravex.dto.response.TokenResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("DataFlowIssue")
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationRestClientImpl implements AuthenticationRestClient {

  @NotNull RestTemplate restTemplate;

  @Value("${address.gateway}")
  @NonFinal
  String gateway;

  @Value("${address.authentication}")
  @NonFinal
  String authentication;

  public @NotNull TokenResponse authenticate(@NotNull AuthenticationRequest authenticationRequest) {
    return restTemplate.postForEntity(gateway + authentication + "/authenticate",
            authenticationRequest,
            TokenResponse.class).
        getBody();
  }

  public @NotNull TokenResponse register(@NotNull RegistrationRequest registrationRequest) {
    return restTemplate.postForEntity(gateway + authentication + "/register",
            registrationRequest,
            TokenResponse.class).
        getBody();
  }
}
