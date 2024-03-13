package dev.tiltrikt.tetravex.action;

import dev.tiltrikt.tetravex.service.console.ConsoleServiceImpl;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import dev.tiltrikt.tetravex.service.regex.RegexServiceImpl;
import dev.tiltrikt.tetravex.service.game.GameService;
import dev.tiltrikt.tetravex.service.parsing.ParsingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class Action {

  @Nullable Action action;
  @NotNull RegexService regexService = new RegexServiceImpl();
  @NotNull ConsoleServiceImpl consoleService = new ConsoleServiceImpl();
  @NotNull ParsingService parsingService = new ParsingService();

  public abstract void doAction(@NotNull ActionType actionType, @NotNull List<Objects> actionArgs, @NotNull GameService gameService);

}
