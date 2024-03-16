package dev.tiltrikt.tetravex;

import dev.tiltrikt.tetravex.client.ui.UserInterface;
import dev.tiltrikt.tetravex.client.ui.ConsoleUi;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Main {

  static UserInterface userInterface = new ConsoleUi();

  public static void main(String[] args) {
    userInterface.bootstrap();
  }
}
