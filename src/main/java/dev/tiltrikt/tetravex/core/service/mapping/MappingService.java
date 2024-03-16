package dev.tiltrikt.tetravex.core.service.mapping;

import dev.tiltrikt.tetravex.core.action.dto.Move;
import dev.tiltrikt.tetravex.core.model.Comment;
import dev.tiltrikt.tetravex.core.model.Score;
import dev.tiltrikt.tetravex.core.service.game.GameService;
import dev.tiltrikt.tetravex.core.service.game.model.Field;
import dev.tiltrikt.tetravex.core.service.game.model.FieldType;
import dev.tiltrikt.tetravex.core.service.game.model.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("StringBufferReplaceableByString")
public class MappingService {

  public @NotNull String mapFieldsToString(@NotNull GameService gameService) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(parseField(gameService.getPlayField()));
    stringBuilder.append(printBorderLine(gameService.getGeneratedField().getSize()));
    stringBuilder.append(parseField(gameService.getGeneratedField()));
    return stringBuilder.toString();
  }

  public @NotNull String mapCommentsToString(@NotNull List<Comment> commentList) {
    StringBuilder stringBuilder = new StringBuilder();

    for (Comment comment : commentList) {
      stringBuilder.append(String.format("%s %s: %s\n", comment.getGame(), comment.getPlayer(), comment.getComment()));
    }

    return stringBuilder.toString();
  }

  public @NotNull String mapScoresToString(@NotNull List<Score> scoreList) {
    StringBuilder stringBuilder = new StringBuilder();

    for (Score score : scoreList) {
      stringBuilder.append(String.format("%s %s %s: %s\n", score.getGame(), score.getPlayer(), score.getPlayedOn().toString(), score.getPoints()));
    }

    return stringBuilder.toString();
  }

  public @NotNull Move mapListToMoveRequest(@NotNull List<String> actionList) {

    FieldType fromField = actionList.getFirst().equalsIgnoreCase("above") ? FieldType.PLAYFIELD : FieldType.GENERATED;
    int fromRow = Integer.parseInt(actionList.get(1));
    int fromColumn = Integer.parseInt(actionList.get(2));

    FieldType toField = actionList.get(3).equalsIgnoreCase("down") ? FieldType.GENERATED : FieldType.PLAYFIELD;
    int toRow = Integer.parseInt(actionList.get(4));
    int toColumn = Integer.parseInt(actionList.get(5));

    return Move.builder().
            fromField(fromField)
            .fromRow(fromRow)
            .fromColumn(fromColumn)
            .toField(toField)
            .toRow(toRow)
            .toColumn(toColumn)
            .build();
  }

  private @NotNull String parseField(@NotNull Field field) {
    StringBuilder stringBuilder = new StringBuilder();
    int size = field.getSize();
    stringBuilder.append(printHeader(size));
    List<Optional<Tile>> tileList = field.getTileList();
    for (int y = 0; y < size; y++) {
      stringBuilder.append(printTileLine(tileList.subList(y * size, (y + 1) * size), y));
    }
    return stringBuilder.toString();
  }

  private @NotNull String printBorderLine(int size) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("===");
    stringBuilder.append("=========".repeat( size));
    stringBuilder.append("===\n");
    return stringBuilder.toString();
  }

  private @NotNull String printTileLine(@NotNull List<Optional<Tile>> tileSubList, int row) {
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

  private @NotNull String printHeader(int size) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("   ");
    for (int i = 0; i < size; i++) {
      stringBuilder.append(String.format("    %d    ", i + 1));
    }
    stringBuilder.append("\n");
    return stringBuilder.toString();
  }
}
