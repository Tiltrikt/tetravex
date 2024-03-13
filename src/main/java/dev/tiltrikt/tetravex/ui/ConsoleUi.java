package dev.tiltrikt.tetravex.ui;

import dev.tiltrikt.tetravex.action.Action;
import dev.tiltrikt.tetravex.action.ActionType;
import dev.tiltrikt.tetravex.action.MoveAction;
import dev.tiltrikt.tetravex.configuration.GameConfiguration;
import dev.tiltrikt.tetravex.exception.GameException;
import dev.tiltrikt.tetravex.service.console.ConsoleServiceImpl;
import dev.tiltrikt.tetravex.service.game.GameServiceImpl;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import dev.tiltrikt.tetravex.service.regex.RegexServiceImpl;
import dev.tiltrikt.tetravex.service.validation.ValidationServiceImpl;
import dev.tiltrikt.tetravex.service.game.GameService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleUi implements UserInterface {

  @NotNull ConsoleServiceImpl consoleService = new ConsoleServiceImpl();
  @NotNull RegexService regexService = new RegexServiceImpl();
  @NotNull ValidationServiceImpl validationService = new ValidationServiceImpl();
  @NonFinal
  GameService gameService;
  @NotNull Action action = new MoveAction(null);

  @Override
  public void bootstrap() {
    String input = consoleService.getUserInput("Choose field size (between %d and %d):", GameConfiguration.MIN, GameConfiguration.MAX);
    int size = 0;

    try {
      size = regexService.getSize(input);
      validationService.validateFieldSize(GameConfiguration.MIN, GameConfiguration.MAX, size);
    } catch (GameException exception) {
      System.out.println("Wrong input, try again");
      bootstrap();
    }

    gameService = new GameServiceImpl(size);
    play();
  }

  @Override
  public void play() {
    show();
    while (!gameService.isWin()) {
      doAction();
      show();
    }
  }

  @Override
  public void show() {
    consoleService.printField(gameService.getPlayField());
    consoleService.printBorderLine(gameService.getPlayField().getSize());
    consoleService.printField(gameService.getGeneratedField());
  }

  @Override
  public void doAction() {
    String userInput = consoleService.getUserInput(
            "Do action: \n" +
                    "<comment> + text \n" +
                    "<rating> + " +
            "<move> + field (default DOWN), row (between %d and %d), column, field (default ABOVE) row, column", GameConfiguration.MIN, GameConfiguration.MAX);
    ActionType actionType = regexService.getAction(userInput);
    List<String> actionArgs = Arrays.stream(userInput.split(" ")).skip(1).toList();
    action.doAction(actionType, actionArgs, gameService);
  }
}
