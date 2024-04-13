package dev.tiltrikt.security.service.security.token.key;

import org.jetbrains.annotations.NotNull;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface MyKeyFactory {

  @NotNull PrivateKey getPrivateKey();

  @NotNull PublicKey getPublicKey();
}
