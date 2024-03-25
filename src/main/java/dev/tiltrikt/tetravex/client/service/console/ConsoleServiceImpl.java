package dev.tiltrikt.tetravex.client.service.console;

import dev.tiltrikt.tetravex.core.exception.GameException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleServiceImpl implements ConsoleService {

  @NotNull Scanner scanner = new Scanner(System.in);

  public @NotNull String getUserInput(@NotNull String format, Object @NotNull ... args) {

    System.out.printf(format, args);
    System.out.println();
    System.out.print("t-> ");
    return scanner.nextLine();
  }

  public void printResponse(@NotNull String response) {

    System.out.println(response);
  }

  public void printException(@NotNull GameException exception) {

    System.out.println(exception.getMessage());
  }
}
