package dev.tiltrikt.ui;

import dev.tiltrikt.model.Field;
import dev.tiltrikt.model.Tile;
import dev.tiltrikt.service.FieldPlayground;
import dev.tiltrikt.service.GameService;
import dev.tiltrikt.service.GameServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
    printField(gameService.getFieldToSolve());
    printBorderLine(gameService.getFieldToSolve().getSize());
    printField(gameService.getGeneratedField());
  }

  @Override
  public void move() {
    System.out.println("Choose filed from (default ABOVE): ");
    System.out.print("t-> ");
    String fieldFrom = scanner.nextLine();
    FieldPlayground fieldPlaygroundFrom = fieldFrom.equalsIgnoreCase("down") ? FieldPlayground.PLAYFIELD
            : FieldPlayground.GENERATED;

    int fromRow = getIntInput(1, gameService.getFieldToSolve().getSize(), "row");
    int fromColumn = getIntInput(1, gameService.getFieldToSolve().getSize(), "column");

    System.out.println("Choose filed to (default UNDER): ");
    System.out.print("t-> ");
    String fieldTo = scanner.nextLine();
    FieldPlayground fieldPlaygroundTo = fieldTo.equalsIgnoreCase("above") ? FieldPlayground.GENERATED
            : FieldPlayground.PLAYFIELD;

    int toRow = getIntInput(1, gameService.getFieldToSolve().getSize(), "row");
    int toColumn = getIntInput(1, gameService.getFieldToSolve().getSize(), "column");

    try {
      gameService.replaceTile(fieldPlaygroundFrom, fromRow, fromColumn, fieldPlaygroundTo, toRow, toColumn);
    } catch (Exception exception) {
      System.out.println(exception.getClass());
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
      } catch (NumberFormatException e) {
        System.out.println("STRING IS NOT INTEGER!!!");
      }
    }
  }
}
