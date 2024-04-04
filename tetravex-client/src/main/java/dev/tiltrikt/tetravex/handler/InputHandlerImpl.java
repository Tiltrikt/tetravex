package dev.tiltrikt.tetravex.handler;

import dev.tiltrikt.tetravex.action.Action;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InputHandlerImpl implements InputHandler {

  Map<String, Action> actionMap;

  @NotNull RegexService regexService;

  @Override
  public @NotNull String handleAction(@NotNull String input) {

    String actionInput = regexService.getAction(input).toLowerCase() + "Action";
    if (actionMap.containsKey(actionInput)) {
      Action action = actionMap.get(actionInput);
      return action.doAction(input);
    }
    Action action = actionMap.get("helpAction");
    return action.doAction(input);
  }
}
