package dev.tiltrikt.tetravex.ui;

import dev.tiltrikt.tetravex.exception.ConnectionException;
import dev.tiltrikt.tetravex.exception.UnauthorizedException;
import dev.tiltrikt.tetravex.handler.InputHandler;
import dev.tiltrikt.tetravex.service.console.ConsoleService;
import dev.tiltrikt.commons.exception.GameException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

@Component
@RequiredArgsConstructor
@SuppressWarnings("InfiniteLoopStatement")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleUi implements UserInterface {

  @NotNull ConsoleService consoleService;

  @NotNull InputHandler inputHandler;

  @SneakyThrows
  @Override
  public void bootstrap() {

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
      String response = inputHandler.handleAction(userInput);
      consoleService.printResponse(response);
    } catch (GameException exception) {
      consoleService.printException(exception);
    } catch (ResourceAccessException exception) {
      consoleService.printException(new ConnectionException(exception.getMessage()));
    }
  }
}
