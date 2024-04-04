package dev.tiltrikt.tetravex.factory;

import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.exception.InputException;
import dev.tiltrikt.tetravex.field.FieldType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MoveRequestFactory {

    public @NotNull MoveMakeRequest convertListToMoveRequest(@NotNull List<String> actionList) {

    FieldType fromField = actionList.getFirst().equalsIgnoreCase("above") ? FieldType.PLAYFIELD : FieldType.GENERATED;
    int fromRow;
    int fromColumn;
    try {
      fromRow = Integer.parseInt(actionList.get(1));
      fromColumn = Integer.parseInt(actionList.get(2));
    } catch (NumberFormatException exception) {
      throw new InputException("Wrong number input");
    }

    FieldType toField = actionList.get(3).equalsIgnoreCase("down") ? FieldType.GENERATED : FieldType.PLAYFIELD;
    int toRow;
    int toColumn;
    try {
      toRow = Integer.parseInt(actionList.get(4));
      toColumn = Integer.parseInt(actionList.get(5));
    } catch (NumberFormatException exception) {
      throw new InputException("Wrong number input");
    }

    return MoveMakeRequest.builder().
            fromField(fromField)
            .fromRow(fromRow)
            .fromColumn(fromColumn)
            .toField(toField)
            .toRow(toRow)
            .toColumn(toColumn)
            .build();
  }
}
