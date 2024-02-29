package dev.tiltrikt.ui;

import dev.tiltrikt.model.Field;
import dev.tiltrikt.model.Tile;
import dev.tiltrikt.service.FieldObject;
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
  @NonFinal GameService gameService;

  @Override
  public void bootstrap() {
    System.out.println("Choose filed size: ");
    System.out.print("t-> ");
    gameService = new GameServiceImpl(scanner.nextInt());
    show();
    while (!gameService.getSolvedField().isSolved()) {
      move();
      show();
    }
  }

  @Override
  public void show() {
    printField(gameService.getSolvedField());
    printBorderLine(gameService.getSolvedField().getSize());
    printField(gameService.getGeneratedField());
  }

  @Override
  public void move() {
    System.out.println("Choose filed from: ");
    System.out.print("t-> ");
    scanner.nextLine();
    String fieldFrom = scanner.nextLine();
    FieldObject fieldObjectFrom = fieldFrom.equalsIgnoreCase("solved") ? FieldObject.SOLVED : FieldObject.GENERATED;

    System.out.println("Choose row: ");
    System.out.print("t-> ");
    int fromRow = scanner.nextInt();
    System.out.println("Choose column: ");
    System.out.print("t-> ");
    int fromColumn = scanner.nextInt();

    System.out.println("Choose filed to: ");
    System.out.print("t-> ");
    scanner.nextLine();
    String fieldTo = scanner.nextLine();
    FieldObject fieldObjectTo = fieldTo.equalsIgnoreCase("generated") ? FieldObject.GENERATED : FieldObject.SOLVED;

    System.out.println("Choose row: ");
    System.out.print("t-> ");
    int toRow = scanner.nextInt();
    System.out.println("Choose column: ");
    System.out.print("t-> ");
    int toColumn = scanner.nextInt();
    gameService.replaceTile(fieldObjectFrom, fromRow, fromColumn, fieldObjectTo, toRow, toColumn);
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
      if (tileSubList.get(i).isPresent())
      {
        System.out.printf(" |\\ %d /| ", tileSubList.get(i).get().getUpNumber());
      }
      else
      {
        System.out.print(" |\\   /| ");
      }
    }
    System.out.println();

    System.out.printf(" %d ", row + 1);
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent())
      {
        System.out.printf(" |%d # %d| ", tileSubList.get(i).get().getLeftNumber(), tileSubList.get(i).get().getRightNumber());
      }
      else
      {
        System.out.print(" |  #  | ");
      }
    }
    System.out.println();

    System.out.print("   ");
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent())
      {
        System.out.printf(" |/ %d \\| ", tileSubList.get(i).get().getDownNumber());
      }
      else
      {
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
}
