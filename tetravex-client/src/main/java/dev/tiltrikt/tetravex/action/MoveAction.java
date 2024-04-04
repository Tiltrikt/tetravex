package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.client.game.GameRestClient;
import dev.tiltrikt.tetravex.dto.FieldDto;
import dev.tiltrikt.tetravex.factory.MoveRequestFactory;
import dev.tiltrikt.tetravex.service.rendering.StringRenderingService;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MoveAction implements Action {

  @NotNull GameRestClient gameRestClient;

  @NotNull RegexService regexService;

  @NotNull StringRenderingService stringRenderingService;

  @NotNull MoveRequestFactory moveRequestFactory;

  public @NotNull String doAction(@NotNull String input) {

    List<String> actionList = regexService.getMoveParameters(input);
    MoveMakeRequest moveMakeRequest = moveRequestFactory.convertListToMoveRequest(actionList);
    List<FieldDto> fields = gameRestClient.replaceTile(moveMakeRequest);

    return stringRenderingService.renderFields(fields.getFirst(), fields.getLast());
  }
}
