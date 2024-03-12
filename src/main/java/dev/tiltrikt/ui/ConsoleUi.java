package dev.tiltrikt.ui;

import dev.tiltrikt.exception.GameException;
import dev.tiltrikt.exception.RegexNotFoundException;
import dev.tiltrikt.model.Field;
import dev.tiltrikt.model.Tile;
import dev.tiltrikt.service.game.GameService;
import dev.tiltrikt.service.game.GameServiceImpl;
import dev.tiltrikt.service.regex.RegexService;
import dev.tiltrikt.service.regex.RegexServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static dev.tiltrikt.service.game.GameServiceImpl.FieldPlayground.GENERATED;
import static dev.tiltrikt.service.game.GameServiceImpl.FieldPlayground.PLAYFIELD;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleUi implements UserInterface {

  @NotNull Scanner scanner = new Scanner(System.in);
  @NotNull RegexService regexService = new RegexServiceImpl();
  @NonFinal GameService gameService;

  @Override
  public void bootstrap() {
    int min = 1;
    int max = 5;

    int size = 0;
    boolean validInput;
    do {
      System.out.printf("Choose field size (between %d and %d): \n", min, max);
      System.out.print("t-> ");
      String input = scanner.nextLine();
      try {
        size = Integer.parseInt(regexService.getSize(input));
        validInput = size >= min && size <= max;
      } catch (RegexNotFoundException ignored){
        validInput = false;
      }
    } while (!validInput);

    gameService = new GameServiceImpl(size);
    play();
  }

  @Override
  public void play() {
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
    try {

      String userInput = getUserInput(1, gameService.getPlayField().getSize());
      List<String> inputList = List.of(userInput.split("[/.|,\\\\]"));

      List<String> moveList = regexService.getMove(inputList.getFirst());

      GameServiceImpl.FieldPlayground fromField = moveList.getFirst()
              .equalsIgnoreCase("above") ? PLAYFIELD : GENERATED;
      int fromRow = Integer.parseInt(moveList.get(1));
      int fromColumn = Integer.parseInt(moveList.get(2));


      moveList = regexService.getMove(inputList.getLast());

      GameServiceImpl.FieldPlayground toField = moveList.get(1)
              .equalsIgnoreCase("down") ? GENERATED : PLAYFIELD;
      int toRow = Integer.parseInt(moveList.get(1));
      int toColumn = Integer.parseInt(moveList.get(2));


      try {
        gameService.replaceTile(fromField, fromRow, fromColumn, toField, toRow, toColumn);
      } catch (GameException exception) {
        System.out.println(exception.getMessage());
      }

    } catch (RegexNotFoundException exception) {
      System.out.println("Wrong input, try again");
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

  private String getUserInput(int min, int max) {
    System.out.printf("Choose field (default DOWN), row (between %d and %d), column," +
            " Field (default ABOVE), row, column)\n", min, max);
    return scanner.nextLine();
  }
}
