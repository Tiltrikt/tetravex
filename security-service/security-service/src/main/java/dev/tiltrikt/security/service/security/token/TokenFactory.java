package dev.tiltrikt.security.service.security.token;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface TokenFactory {

  @NotNull String generateToken(@NotNull String username);

  @NotNull Map<String, Object> getJwks();
}
