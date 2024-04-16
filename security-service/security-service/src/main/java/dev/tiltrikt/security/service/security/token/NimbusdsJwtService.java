package dev.tiltrikt.security.service.security.token;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import dev.tiltrikt.security.service.security.token.key.MyKeyFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Primary
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NimbusdsJwtService implements TokenFactory {

  @NotNull MyKeyFactory myKeyFactory;

  @NonFinal
  @Value("${jwt.access-token.lifetime}")
  int lifetime;

  @Override
  @SneakyThrows
  public @NotNull String generateToken(@NotNull String username, @NotNull List<String> authorities) {

    JWSSigner signer = new RSASSASigner(myKeyFactory.getPrivateKey());
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .subject(username)
        .claim("ath", String.join(" ", authorities))
        .issueTime(new Date(System.currentTimeMillis()))
        .expirationTime(new Date(System.currentTimeMillis() + lifetime))
        .build();

    SignedJWT signedJWT = new SignedJWT(
        new JWSHeader.Builder(JWSAlgorithm.RS256).build(),
        claimsSet
    );
    signedJWT.sign(signer);
    return signedJWT.serialize();
  }

  @Override
  @Cacheable("jwks")
  public @NotNull Map<String, Object> getJwks() {

    JWK jwk = new RSAKey.Builder((RSAPublicKey) myKeyFactory.getPublicKey()).build();
    return new JWKSet(jwk).toJSONObject();
  }
}
