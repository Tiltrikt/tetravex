package dev.tiltrikt;

import dev.tiltrikt.ui.ConsoleUi;
import dev.tiltrikt.ui.UserInterface;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Main {

  static UserInterface userInterface = new ConsoleUi();

  public static void main(String[] args) {
    userInterface.bootstrap();
  }
}
