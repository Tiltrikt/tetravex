package dev.tiltrikt.security.service.security.token;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Primary
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NimbusdsJwtService implements TokenFactory, TokenValidator, ClaimsService {

  @NonFinal
  @Value("${jwt.access-token.jwtPublicKey}")
  String jwtPublicKey;

  @NonFinal
  @Value("${jwt.access-token.jwtPrivateKey}")
  String jwtPrivateKey;

  @NonFinal
  @Value("${jwt.access-token.lifetime}")
  int lifetime;
  @Override
  public @NotNull String extractUsername(@NotNull String token) {
    return null;
  }

  @Override
  public @NotNull Claims extractClaims(@NotNull String token) {
    return null;
  }

  @SneakyThrows
  public @NotNull PublicKey generateJwtPublicKey() {

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    byte[] keyBytes = Base64.decodeBase64(jwtPublicKey);
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
    return keyFactory.generatePublic(x509EncodedKeySpec);
  }

  @SneakyThrows
  public @NotNull PrivateKey generateJwtPrivateKey() {

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    byte[] keyBytes = Base64.decodeBase64(jwtPrivateKey);
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
  }

  @SneakyThrows
  @Override
  public @NotNull String generateToken(@NotNull String username) {
    JWSSigner signer = new RSASSASigner(generateJwtPrivateKey());
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .claim("name", username)
        .issueTime(new Date(System.currentTimeMillis()))
        .expirationTime(new Date(System.currentTimeMillis() + lifetime))
        .build();
    SignedJWT signedJWT = new SignedJWT(
        new JWSHeader.Builder(JWSAlgorithm.RS256).build(),
        claimsSet);
    signedJWT.sign(signer);
    return signedJWT.serialize();
  }

  @Override
  public @NotNull Map<String, Object> generateJwks() {
    JWK jwk = new RSAKey.Builder((RSAPublicKey) generateJwtPublicKey()).build();
    return new JWKSet(jwk).toJSONObject();
  }

  @Override
  public boolean isValid(@NotNull String token) {
    return false;
  }
}
