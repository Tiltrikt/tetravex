package dev.tiltrikt.tetravex.service.console;

import dev.tiltrikt.tetravex.entity.Score;
import dev.tiltrikt.tetravex.model.Field;
import dev.tiltrikt.tetravex.model.Tile;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleServiceImpl {

  @NotNull Scanner scanner = new Scanner(System.in);

  public @NotNull String getUserInput(@NotNull String format, Object @NotNull ... args) {

    System.out.printf(format, args);
    System.out.println();
    System.out.print("t-> ");
    return scanner.nextLine();
  }

  public void printTopScore(List<Score> scoreActionList) {
    scoreActionList.forEach(System.out::println);
  }

  public void printField(Field field) {
    int size = field.getSize();
    printHeader(size);
    List<Optional<Tile>> solvedTileList = field.getTileList();
    for (int y = 0; y < size; y++) {
      printTileLine(solvedTileList.subList(y * size, (y + 1) * size), y);
    }
  }

  public void printBorderLine(int size) {
    System.out.print("===");
    for (int i = 0; i < size; i++) {
      System.out.print("=========");
    }
    System.out.println("===");
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
}
