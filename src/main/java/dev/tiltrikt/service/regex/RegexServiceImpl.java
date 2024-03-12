package dev.tiltrikt.service.regex;

import dev.tiltrikt.exception.RegexNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegexServiceImpl extends RegexService {

  public @NotNull String getSize(@NotNull String input) throws RegexNotFoundException {

    Matcher matcher = sizePattern.matcher(input);

    if (matcher.find()) {
      return matcher.group("size");
    } else {
      throw new RegexNotFoundException("Size wrong format");
    }
  }

  public @NotNull List<String> getMove(@NotNull String input) throws RegexNotFoundException {

    Matcher matcher = movePattern.matcher(input);

    if (matcher.find()) {
      List<String> moveList = new ArrayList<>();
      moveList.add(matcher.group("field"));
      moveList.addAll(List.of(matcher.group("position").split(" ")));
      return moveList;
    } else {
      throw new RegexNotFoundException("No move action found");
    }
  }
}
