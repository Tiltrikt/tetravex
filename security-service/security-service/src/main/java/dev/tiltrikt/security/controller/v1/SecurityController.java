package dev.tiltrikt.security.controller.v1;

import dev.tiltrikt.security.service.authentication.AuthenticationService;
import dev.tiltrikt.security.service.security.token.TokenFactory;
import dev.tiltrikt.tetravex.dto.request.AuthenticationRequest;
import dev.tiltrikt.tetravex.dto.request.RegistrationRequest;
import dev.tiltrikt.tetravex.dto.response.TokenResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityController {

  @NotNull AuthenticationService authenticationService;

  @NotNull TokenFactory tokenFactory;

  @PostMapping("/authenticate")
  public TokenResponse authenticate(@NotNull @RequestBody AuthenticationRequest request) {

    authenticationService.authenticate(request);
    String token = tokenFactory.generateToken(request.getUsername());
    return new TokenResponse(token);
  }

  @PostMapping("/register")
  public TokenResponse register(@NotNull @RequestBody RegistrationRequest request) {

    authenticationService.register(request);
    String token = tokenFactory.generateToken(request.getUsername());
    return new TokenResponse(token);
  }

  @GetMapping("/key")
  public Map<String, Object> publicKey() {
    return tokenFactory.getJwks();
  }
}
