package dev.tiltrikt.tetravex.client.ui;

import dev.tiltrikt.tetravex.client.service.console.ConsoleService;
import dev.tiltrikt.tetravex.core.action.Action;
import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import dev.tiltrikt.tetravex.core.exception.GameException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@SuppressWarnings("InfiniteLoopStatement")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleUi implements UserInterface {

//  @NotNull Reflections reflections = new Reflections("dev.tiltrikt.tetravex.core.action");
  @NotNull ConsoleService consoleService;

  @NotNull ApplicationContext applicationContext;
  @NonFinal
  Action action;

  @SneakyThrows
  @Override
  public void bootstrap() {

//    Set<Class<?>> types = reflections.getTypesAnnotatedWith(ActionClass.class);
//    action = null;
//    for (Class<?> clazz : types) {
//      action = (Action) clazz.getDeclaredConstructor().newInstance();
//    }

    List<Action> actions = applicationContext.getBeansWithAnnotation(ActionClass.class).values().stream()
        .map(value -> (Action) value)
        .toList();

    for (Action a : actions) {

      if (action == null) {
        action = a;
        continue;
      }

      Action currentAction = action;
      while (currentAction.getAction() != null) {
        currentAction = currentAction.getAction();
      }
      currentAction.setAction(a);
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
