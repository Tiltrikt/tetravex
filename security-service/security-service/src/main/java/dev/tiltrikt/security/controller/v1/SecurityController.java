package dev.tiltrikt.security.controller.v1;

import dev.tiltrikt.security.api.request.AuthenticationRequest;
import dev.tiltrikt.security.api.request.RegistrationRequest;
import dev.tiltrikt.security.api.response.TokenResponse;
import dev.tiltrikt.security.service.authentication.AuthenticationService;
import dev.tiltrikt.security.service.security.token.TokenFactory;
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
//    log.info("Request for jwt");
//    return "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.jYW04zLDHfR1v7xdrW3lCGZrMIsVe0vWCfVkN2DRns2c3MN-mcp_-RE6TN9umSBYoNV-mnb31wFf8iun3fB6aDS6m_OXAiURVEKrPFNGlR38JSHUtsFzqTOj-wFrJZN4RwvZnNGSMvK3wzzUriZqmiNLsG8lktlEn6KA4kYVaM61_NpmPHWAjGExWv7cjHYupcjMSmR8uMTwN5UuAwgW6FRstCJEfoxwb0WKiyoaSlDuIiHZJ0cyGhhEmmAPiCwtPAwGeaL1yZMcp0p82cpTQ5Qb-7CtRov3N4DcOHgWYk6LomPR5j5cCkePAz87duqyzSMpCB0mCOuE3CU2VMtGeQ";
  }

  @PostMapping("/register")
  public TokenResponse register(@NotNull @RequestBody RegistrationRequest request) {

    authenticationService.register(request);
    String token = tokenFactory.generateToken(request.getUsername());
    return new TokenResponse(token);
  }

  @GetMapping("/key")
  public Map<String, Object> publicKey() {
    return tokenFactory.generateJwks();
//    return tokenFactory.generateJwks();
//    return tokenFactory.generateJwks();
//    return "{\n" +
//        "    \"keys\": [{\n" +
//        "  \"kty\": \"RSA\",\n" +
//        "  \"n\": \"u1SU1LfVLPHCozMxH2Mo4lgOEePzNm0tRgeLezV6ffAt0gunVTLw7onLRnrq0_IzW7yWR7QkrmBL7jTKEn5u-qKhbwKfBstIs-bMY2Zkp18gnTxKLxoS2tFczGkPLPgizskuemMghRniWaoLcyehkd3qqGElvW_VDL5AaWTg0nLVkjRo9z-40RQzuVaE8AkAFmxZzow3x-VJYKdjykkJ0iT9wCS0DRTXu269V264Vf_3jvredZiKRkgwlL9xNAwxXFg0x_XFw005UWVRIkdgcKWTjpBP2dPwVZ4WWC-9aGVd-Gyn1o0CLelf4rEjGoXbAAEgAqeGUxrcIlbjXfbcmw\",\n" +
//        "  \"e\": \"AQAB\",\n" +
//        "  \"alg\": \"RS512\",\n" +
//        "  \"kid\": \"dfd\",\n" +
//        "  \"use\": \"sig\"\n" +
//        "}]\n" +
//        "}";
//    return new Jwks();
//    return "{MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu1SU1LfVLPHCozMxH2Mo4lgOEePzNm0tRgeLezV6ffAt0gunVTLw7onLRnrq0/IzW7yWR7QkrmBL7jTKEn5u+qKhbwKfBstIs+bMY2Zkp18gnTxKLxoS2tFczGkPLPgizskuemMghRniWaoLcyehkd3qqGElvW/VDL5AaWTg0nLVkjRo9z+40RQzuVaE8AkAFmxZzow3x+VJYKdjykkJ0iT9wCS0DRTXu269V264Vf/3jvredZiKRkgwlL9xNAwxXFg0x/XFw005UWVRIkdgcKWTjpBP2dPwVZ4WWC+9aGVd+Gyn1o0CLelf4rEjGoXbAAEgAqeGUxrcIlbjXfbcmwIDAQAB}";
  }
}
