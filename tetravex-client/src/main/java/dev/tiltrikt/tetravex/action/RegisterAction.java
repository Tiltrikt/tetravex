package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.client.rest.authentication.AuthenticationRestClient;
import dev.tiltrikt.tetravex.dto.request.RegistrationRequest;
import dev.tiltrikt.tetravex.player.Player;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import dev.tiltrikt.tetravex.service.validation.ValidationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterAction implements Action {

  @NotNull Player player;

  @NotNull ValidationService validationService;

  @NotNull RegexService regexService;

  @NotNull AuthenticationRestClient authenticationRestClient;

  public @NotNull String doAction(@NotNull String input) {

    List<String> startList = regexService.getParameters(input);
    validationService.validateAuthenticationRequest(startList);
    String jwt = authenticationRestClient
        .register(new RegistrationRequest(startList.getFirst(), startList.getLast()))
        .getToken();
    player.setJwt(jwt);

    return startList.getFirst() + " have fun spending your time!\n";
  }
}