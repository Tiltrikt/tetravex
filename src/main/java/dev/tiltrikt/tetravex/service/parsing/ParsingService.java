package dev.tiltrikt.tetravex.service.parsing;

import dev.tiltrikt.tetravex.service.game.FieldType;
import dev.tiltrikt.tetravex.action.details.Move;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import dev.tiltrikt.tetravex.service.regex.RegexServiceImpl;

import java.util.List;
import java.util.Objects;

public class ParsingService {

  RegexService regexService = new RegexServiceImpl();

  public Move parseMove(List<Objects> listArgs) {

    FieldType fromField = listArgs.getFirst().equalsIgnoreCase("above") ? FieldType.PLAYFIELD : FieldType.GENERATED;
    int fromRow = Integer.parseInt(listArgs.get(1));
    int fromColumn = Integer.parseInt(listArgs.get(2));

    FieldType toField = listArgs.get(3).equalsIgnoreCase("down") ? FieldType.GENERATED : FieldType.PLAYFIELD;
    int toRow = Integer.parseInt(listArgs.get(4));
    int toColumn = Integer.parseInt(listArgs.get(5));

    return Move.builder().
            fromField(fromField)
            .fromRow(fromRow)
            .fromColumn(fromColumn)
            .toField(toField)
            .toRow(toRow)
            .toColumn(toColumn)
            .build();
  }

}
