package dev.tiltrikt.tetravex.service.rendering;


import dev.tiltrikt.tetravex.dto.FieldDto;
import dev.tiltrikt.tetravex.dto.RatingDto;
import dev.tiltrikt.tetravex.dto.ScoreDto;
//import dev.tiltrikt.tetravex.service.game.GameService;
//import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
//import dev.tiltrikt.tetravex.service.game.model.Field;
//import dev.tiltrikt.tetravex.service.game.model.FieldType;
//import dev.tiltrikt.tetravex.service.game.model.Tile;
import dev.tiltrikt.tetravex.dto.TileDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@SuppressWarnings("StringBufferReplaceableByString")
public class StringRenderingServiceImpl implements StringRenderingService {

  public @NotNull String renderFields(@NotNull FieldDto generatedField, @NotNull FieldDto playField) {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(renderField(playField));
    stringBuilder.append(renderBorderLine(generatedField.getSize()));
    stringBuilder.append(renderField(generatedField));
    return stringBuilder.toString();
  }

  public @NotNull String renderComments(@NotNull List<RatingDto> commentList) {

    StringBuilder stringBuilder = new StringBuilder();

    for (RatingDto comment : commentList) {
      stringBuilder.append(String.format("%s %s: %s\n", comment.getGame(), comment.getPlayer(), comment.getComment()));
    }

    return stringBuilder.toString();
  }

  public @NotNull String renderScores(@NotNull List<ScoreDto> scoreList) {
    StringBuilder stringBuilder = new StringBuilder();

    for (ScoreDto score : scoreList) {
      stringBuilder.append(String.format("%s %s %s: %s\n", score.getGame(), score.getPlayer(),
              score.getPlayedOn(), score.getPoints()));
    }

    return stringBuilder.toString();
  }

  private @NotNull String renderField(@NotNull FieldDto field) {

    StringBuilder stringBuilder = new StringBuilder();
    int size = field.getSize();
    stringBuilder.append(renderHeader(size));
    List<Optional<TileDto>> tileList = field.getTileList();
    for (int y = 0; y < size; y++) {
      stringBuilder.append(renderTileLine(tileList.subList(y * size, (y + 1) * size), y));
    }
    return stringBuilder.toString();
  }

  private @NotNull String renderTileLine(@NotNull List<Optional<TileDto>> tileSubList, int row) {

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

  private @NotNull String renderHeader(int size) {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("   ");
    for (int i = 0; i < size; i++) {
      stringBuilder.append(String.format("    %d    ", i + 1));
    }
    stringBuilder.append("\n");
    return stringBuilder.toString();
  }

  private @NotNull String renderBorderLine(int size) {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("===");
    stringBuilder.append("=========".repeat( size));
    stringBuilder.append("===\n");
    return stringBuilder.toString();
  }
}
