package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.client.game.GameRestClient;
import dev.tiltrikt.tetravex.dto.FieldDto;
import dev.tiltrikt.tetravex.dto.GameStartRequest;
import dev.tiltrikt.tetravex.service.rendering.StringRenderingService;
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
public class StartAction implements Action {

  @NotNull GameRestClient gameRestClient;

  @NotNull RegexService regexService;

  @NotNull ValidationService validationService;

  @NotNull StringRenderingService stringRenderingService;

  public @NotNull String doAction(@NotNull String input) {

    List<String> startList = regexService.getParameters(input);
    validationService.validateStartInput(startList);

    int size = Integer.parseInt(startList.getFirst());
    validationService.validateFieldSize(0, 1000, size);
    List<FieldDto> fields = gameRestClient.startGame(new GameStartRequest(size));

    return stringRenderingService.renderFields(fields.getFirst(), fields.getLast());
  }
}
