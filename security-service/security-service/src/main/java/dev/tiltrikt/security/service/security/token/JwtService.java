//package dev.tiltrikt.security.service.security.token;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.*;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.experimental.FieldDefaults;
//import lombok.experimental.NonFinal;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.security.*;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Date;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//public final class JwtService implements ClaimsService, TokenFactory, TokenValidator {
//
//  @NonFinal
//  @Value("${jwt.access-token.secret}")
//  String secret;
//
//  @NonFinal
//  @Value("${jwt.access-token.jwtPublicKey}")
//  String jwtPublicKey;
//
//  @NonFinal
//  @Value("${jwt.access-token.jwtPrivateKey}")
//  String jwtPrivateKey;
//
//  @NonFinal
//  @Value("${jwt.access-token.lifetime}")
//  int lifetime;
//
//  @Override
//  public @NotNull String extractUsername(@NotNull String token) {
//    return extractClaims(token).getSubject();
//  }
//
//  @Override
//  public @NotNull Claims extractClaims(@NotNull String token) {
//    return Jwts.parser()
//        .verifyWith(generateJwtPublicKey())
//        .build()
//        .parseSignedClaims(token)
//        .getPayload();
//  }
//
//  @SneakyThrows
//  @Override
//  public @NotNull String generateToken(@NotNull String username) {
//    return Jwts.builder()
//        .subject(username)
//        .issuedAt(new Date(System.currentTimeMillis()))
//        .expiration(new Date(System.currentTimeMillis() + lifetime))
//        .signWith(generateJwtPrivateKey(), Jwts.SIG.RS256)
//        .compact();
//  }
//
//  @SneakyThrows
//  public @NotNull PublicKey generateJwtPublicKey() {
//
//    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//    byte[] keyBytes = Base64.decodeBase64(jwtPublicKey);
//    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
//    return keyFactory.generatePublic(x509EncodedKeySpec);
//  }
//
//  @SneakyThrows
//  public @NotNull PrivateKey generateJwtPrivateKey() {
//
//    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//    byte[] keyBytes = Base64.decodeBase64(jwtPrivateKey);
//    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
//    return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//  }
//
//  public @NotNull JwkSet generateJwks () {
//    return Jwks.set().add(Jwks.builder().key(generateJwtPublicKey()).build()).build();
//  }
//
//  @Override
//  public boolean isValid(@NotNull String token) {
//    try {
//      Claims claims = extractClaims(token);
//      boolean expired = claims.getExpiration().before(new Date(System.currentTimeMillis()));
//      return !expired;
//    } catch (JwtException ignored) {
//      return false;
//    }
//  }
//}
