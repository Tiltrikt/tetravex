package dev.tiltrikt.security.service.security.token.key;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyKeyFactoryImpl implements MyKeyFactory {

  @Value("${jwt.access-token.jwtPublicKey}")
  String jwtPublicKey;

  @Value("${jwt.access-token.jwtPrivateKey}")
  String jwtPrivateKey;

  @Override
  @SneakyThrows
  @Cacheable("public_key")
  public @NotNull PublicKey getPublicKey() {

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    byte[] keyBytes = Base64.decodeBase64(jwtPublicKey);
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
    return keyFactory.generatePublic(x509EncodedKeySpec);
  }

  @Override
  @SneakyThrows
  @Cacheable("private_key")
  public @NotNull PrivateKey getPrivateKey() {

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    byte[] keyBytes = Base64.decodeBase64(jwtPrivateKey);
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
  }
}
