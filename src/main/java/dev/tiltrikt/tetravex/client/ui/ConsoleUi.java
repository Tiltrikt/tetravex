package dev.tiltrikt.tetravex.client.ui;

import dev.tiltrikt.tetravex.client.service.console.ConsoleServiceImpl;
import dev.tiltrikt.tetravex.core.action.Action;
import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.exception.GameException;
import dev.tiltrikt.tetravex.core.service.regex.RegexService;
import dev.tiltrikt.tetravex.core.service.regex.RegexServiceImpl;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleUi implements UserInterface {

  @NotNull Reflections reflections = new Reflections("dev.tiltrikt.tetravex.core.action");
  @NonFinal Action action;
  @NotNull ConsoleServiceImpl consoleService = new ConsoleServiceImpl();

  @SneakyThrows
  @Override
  public void bootstrap() {

    Set<Class<?>> types = reflections.getTypesAnnotatedWith(ActionClass.class);
    action = null;
    for (Class<?> clazz : types) {
      action = (Action) clazz.getDeclaredConstructor(Action.class).newInstance(action);
    }

    consoleService.printResponse(action.handleAction("help"));
    play();
  }

  @Override
  public void play() {

    while (true) {
      doAction();
    }
  }

  @Override
  public void doAction() {

    try {
      String userInput = consoleService.getUserInput("do action");
      String response = action.handleAction(userInput);
      consoleService.printResponse(response);
    } catch (GameException exception) {
      consoleService.printException(exception);
    }
  }
}
