package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.core.service.game.GameServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ActionClass
public class StartAction extends Action {

  public StartAction(@Nullable Action action) {
    super(action, ActionType.START);
  }

  public @NotNull String doAction(@NotNull String input) {

    List<String> startList = regexService.getParameters(input);
    validationService.validateStartInput(startList);

    int size = Integer.parseInt(startList.getFirst());
    validationService.validateFieldSize(GameConfiguration.MIN, GameConfiguration.MAX, size);
    gameService = new GameServiceImpl(size);
    player = startList.getLast();

    return mappingService.mapFieldsToString(gameService);
  }
}
