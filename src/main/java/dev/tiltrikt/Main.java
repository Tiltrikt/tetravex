package dev.tiltrikt;

import dev.tiltrikt.ui.ConsoleUi;
import dev.tiltrikt.ui.UserInterface;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Main {

  static UserInterface userInterface = new ConsoleUi();

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {

      System.out.println(new Random().nextInt(9) + 1);
    }
//    userInterface.bootstrap();
  }
}
