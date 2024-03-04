package dev.tiltrikt.ui;

import dev.tiltrikt.exception.GameException;
import dev.tiltrikt.model.Field;
import dev.tiltrikt.model.Tile;
import dev.tiltrikt.service.GameService;
import dev.tiltrikt.service.GameServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static dev.tiltrikt.service.GameServiceImpl.FieldPlayground.GENERATED;
import static dev.tiltrikt.service.GameServiceImpl.FieldPlayground.PLAYFIELD;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleUi implements UserInterface {

  @NotNull Scanner scanner = new Scanner(System.in);
  @NonFinal
  GameService gameService;

  @Override
  public void bootstrap() {
    gameService = new GameServiceImpl(getIntInput(2, 5, "field size"));
    show();
    while (!gameService.isWin()) {
      move();
      show();
    }
  }

  @Override
  public void show() {
    printField(gameService.getPlayField());
    printBorderLine(gameService.getPlayField().getSize());
    printField(gameService.getGeneratedField());
  }

  @Override
  public void move() {
    String fieldFrom = getStringInput("Choose filed to (default DOWN): ");
    int fromRow = getIntInput(1, gameService.getPlayField().getSize(), "row");
    int fromColumn = getIntInput(1, gameService.getPlayField().getSize(), "column");
    GameServiceImpl.FieldPlayground fieldPlaygroundFrom =
            fieldFrom.equalsIgnoreCase("above") ? PLAYFIELD : GENERATED;

    String fieldTo = getStringInput("Choose filed to (default ABOVE): ");
    GameServiceImpl.FieldPlayground fieldPlaygroundTo =
            fieldTo.equalsIgnoreCase("down") ? GENERATED : PLAYFIELD;
    int toRow = getIntInput(1, gameService.getPlayField().getSize(), "row");
    int toColumn = getIntInput(1, gameService.getPlayField().getSize(), "column");

    try {
      gameService.replaceTile(fieldPlaygroundFrom, fromRow, fromColumn, fieldPlaygroundTo, toRow, toColumn);
    } catch (GameException exception) {
      System.out.println(exception.getMessage());
    }
  }

  private void printTileLine(List<Optional<Tile>> tileSubList, int row) {
    int size = tileSubList.size();
    System.out.print("   ");
    for (int i = 0; i < size; i++) {
      System.out.print("  _____  ");
    }
    System.out.println();

    System.out.print("   ");
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent()) {
        System.out.printf(" |\\ %d /| ", tileSubList.get(i).get().getUpNumber());
      } else {
        System.out.print(" |\\   /| ");
      }
    }
    System.out.println();

    System.out.printf(" %d ", row + 1);
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent()) {
        System.out.printf(" |%d # %d| ", tileSubList.get(i).get().getLeftNumber(), tileSubList.get(i).get().getRightNumber());
      } else {
        System.out.print(" |  #  | ");
      }
    }
    System.out.println();

    System.out.print("   ");
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent()) {
        System.out.printf(" |/ %d \\| ", tileSubList.get(i).get().getDownNumber());
      } else {
        System.out.print(" |/   \\| ");
      }
    }
    System.out.println();

    System.out.print("   ");
    for (int i = 0; i < size; i++) {
      System.out.print("  `````  ");
    }
    System.out.println();
  }

  private void printHeader(int size) {
    System.out.print("   ");
    for (int i = 0; i < size; i++) {
      System.out.printf("    %d    ", i + 1);
    }
    System.out.println();
  }

  private void printBorderLine(int size) {
    System.out.print("===");
    for (int i = 0; i < size; i++) {
      System.out.print("=========");
    }
    System.out.println("===");
  }

  private void printField(Field field) {
    int size = field.getSize();
    printHeader(size);
    List<Optional<Tile>> solvedTileList = field.getTileList();
    for (int y = 0; y < size; y++) {
      printTileLine(solvedTileList.subList(y * size, (y + 1) * size), y);
    }
  }

  private int getIntInput(int min, int max, String text) {
    while (true) {
      System.out.printf("Choose %s (between %d and %d): \n", text, min, max);
      System.out.print("t-> ");
      try {
        int input = Integer.parseInt(scanner.nextLine());
        if (input >= min && input <= max) return input;
      } catch (NumberFormatException ignore) {
      }
    }
  }

  private String getStringInput(String text) {
    System.out.println(text);
    System.out.print("t-> ");
    return scanner.nextLine();
  }
}
