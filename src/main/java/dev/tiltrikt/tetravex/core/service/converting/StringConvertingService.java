package dev.tiltrikt.tetravex.core.service.converting;

import dev.tiltrikt.tetravex.core.exception.InputException;
import dev.tiltrikt.tetravex.core.model.Comment;
import dev.tiltrikt.tetravex.core.model.Score;
import dev.tiltrikt.tetravex.core.service.game.GameService;
import dev.tiltrikt.tetravex.core.service.game.dto.Move;
import dev.tiltrikt.tetravex.core.service.game.model.Field;
import dev.tiltrikt.tetravex.core.service.game.model.FieldType;
import dev.tiltrikt.tetravex.core.service.game.model.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("StringBufferReplaceableByString")
public class StringConvertingService {

  public @NotNull String convertFieldsToString(@NotNull GameService gameService) {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(convertFieldToString(gameService.getPlayField()));
    stringBuilder.append(createBorderLine(gameService.getGeneratedField().getSize()));
    stringBuilder.append(convertFieldToString(gameService.getGeneratedField()));
    return stringBuilder.toString();
  }

  public @NotNull String convertCommentsToString(@NotNull List<Comment> commentList) {

    StringBuilder stringBuilder = new StringBuilder();

    for (Comment comment : commentList) {
      stringBuilder.append(String.format("%s %s: %s\n", comment.getGame(), comment.getPlayer(), comment.getComment()));
    }

    return stringBuilder.toString();
  }

  public @NotNull String convertScoresToString(@NotNull List<Score> scoreList) {
    StringBuilder stringBuilder = new StringBuilder();

    for (Score score : scoreList) {
      stringBuilder.append(String.format("%s %s %s: %s\n", score.getGame(), score.getPlayer(),
              score.getPlayedOn().toString(), score.getPoints()));
    }

    return stringBuilder.toString();
  }

  public @NotNull Move convertListToMoveRequest(@NotNull List<String> actionList) {

    FieldType fromField = FieldType.resolveFieldType(actionList.getFirst(), "above");
    int fromRow;
    int fromColumn;
    try {
      fromRow = Integer.parseInt(actionList.get(1));
      fromColumn = Integer.parseInt(actionList.get(2));
    } catch (NumberFormatException exception) {
      throw new InputException("Wrong number input");
    }

    FieldType toField = FieldType.resolveFieldType(actionList.get(3), "down");
    int toRow;
    int toColumn;
    try {
      toRow = Integer.parseInt(actionList.get(4));
      toColumn = Integer.parseInt(actionList.get(5));
    } catch (NumberFormatException exception) {
      throw new InputException("Wrong number input");
    }

    return Move.builder().
            fromField(fromField)
            .fromRow(fromRow)
            .fromColumn(fromColumn)
            .toField(toField)
            .toRow(toRow)
            .toColumn(toColumn)
            .build();
  }

  private @NotNull String convertFieldToString(@NotNull Field field) {

    StringBuilder stringBuilder = new StringBuilder();
    int size = field.getSize();
    stringBuilder.append(createHeader(size));
    List<Optional<Tile>> tileList = field.getTileList();
    for (int y = 0; y < size; y++) {
      stringBuilder.append(convertTileLineToString(tileList.subList(y * size, (y + 1) * size), y));
    }
    return stringBuilder.toString();
  }

  private @NotNull String convertTileLineToString(@NotNull List<Optional<Tile>> tileSubList, int row) {

    StringBuilder stringBuilder = new StringBuilder();
    int size = tileSubList.size();
    stringBuilder.append("   ");
    stringBuilder.append("  _____  ".repeat(size));
    stringBuilder.append("\n");

    stringBuilder.append("   ");
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent()) {
        stringBuilder.append(String.format(" |\\ %d /| ", tileSubList.get(i).get().getUpNumber()));
      } else {
        stringBuilder.append(" |\\   /| ");
      }
    }
    stringBuilder.append("\n");

    stringBuilder.append(String.format(" %d ", row + 1));
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent()) {
        stringBuilder.append(String.format(" |%d # %d| ", tileSubList.get(i).get().getLeftNumber(), tileSubList.get(i).get().getRightNumber()));
      } else {
        stringBuilder.append(" |  #  | ");
      }
    }
    stringBuilder.append("\n");

    stringBuilder.append("   ");
    for (int i = 0; i < size; i++) {
      if (tileSubList.get(i).isPresent()) {
        stringBuilder.append(String.format(" |/ %d \\| ", tileSubList.get(i).get().getDownNumber()));
      } else {
        stringBuilder.append(" |/   \\| ");
      }
    }
    stringBuilder.append("\n");

    stringBuilder.append("   ");
    stringBuilder.append("  `````  ".repeat(size));
    stringBuilder.append("\n");

    return stringBuilder.toString();
  }

  private @NotNull String createHeader(int size) {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("   ");
    for (int i = 0; i < size; i++) {
      stringBuilder.append(String.format("    %d    ", i + 1));
    }
    stringBuilder.append("\n");
    return stringBuilder.toString();
  }

  private @NotNull String createBorderLine(int size) {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("===");
    stringBuilder.append("=========".repeat( size));
    stringBuilder.append("===\n");
    return stringBuilder.toString();
  }
}
