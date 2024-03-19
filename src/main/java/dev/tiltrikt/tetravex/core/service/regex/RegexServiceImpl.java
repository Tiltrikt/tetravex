package dev.tiltrikt.tetravex.core.service.regex;

import dev.tiltrikt.tetravex.core.exception.RegexNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexServiceImpl implements RegexService {

  @NotNull Pattern movePattern = Pattern.compile("(?<field>[a-z]*)\\s?(?<position>(?:\\d ?){2})", Pattern.CASE_INSENSITIVE);
  @NotNull Pattern actionPattern = Pattern.compile("^(?<action>[a-z]+)", Pattern.CASE_INSENSITIVE);
  @NotNull Pattern commentPattern = Pattern.compile("^[a-z]+\\s+(?<parameter>(?:.+\\s*)+)$", Pattern.CASE_INSENSITIVE);

  @Override
  public @NotNull List<String> getMoveParameters(@NotNull String input) throws RegexNotFoundException {

    Matcher matcher = movePattern.matcher(input);

    List<String> actionList = new ArrayList<>();
    for (int i = 0; i < 2; i++) {

      if (matcher.find()) {
        actionList.add(matcher.group("field"));
        actionList.addAll(List.of(matcher.group("position").split(" ")));
      } else {
        throw new RegexNotFoundException("No move action found");
      }
    }

    return actionList;
  }

  @Override
  public @NotNull String getAction(@NotNull String input) {

    Matcher matcher = actionPattern.matcher(input);

    if (matcher.find()) {
      return matcher.group("action");
    } else {
      throw new RegexNotFoundException("No action present");
    }
  }

  @Override
  public @NotNull List<String> getParameters(@NotNull String input) {

    Matcher matcher = commentPattern.matcher(input);

    if (matcher.find()) {
      return List.of(matcher.group("parameter").split(" "));
    } else {
      throw new RegexNotFoundException("No parameters found");
    }
  }
}
