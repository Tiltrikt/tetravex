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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    log.info("authenticate");
    Authentication authentication = authenticationService.authenticate(request);
    List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    String token = tokenFactory.generateToken(request.getUsername(), authorities);
    return new TokenResponse(token);
  }

  @PostMapping("/register")
  public TokenResponse register(@NotNull @RequestBody RegistrationRequest request) {

    log.info("register");
    Authentication authentication = authenticationService.register(request);
    List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    String token = tokenFactory.generateToken(request.getUsername(), authorities);
    return new TokenResponse(token);
  }

  @GetMapping("/key")
  public Map<String, Object> publicKey() {
    log.info("key set");
    return tokenFactory.getJwks();
  }
}
