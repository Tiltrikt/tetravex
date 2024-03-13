package dev.tiltrikt.tetravex.service.regex;

import dev.tiltrikt.tetravex.action.ActionType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class RegexService {

  @NotNull Pattern sizePattern = Pattern.compile("^(?<size>\\d{1})$");
  @NotNull Pattern movePattern = Pattern.compile("(?<field>[a-zA-Z]*)\\s?(?<position>(?:\\d ?){2})");
//  @NotNull Pattern scorePattern = Pattern.compile("(?<>[a-zA-Z]*))");
  @NotNull Pattern actionPattern = Pattern.compile("^(?<action>[a-zA-Z]+)");

  public abstract int getSize(@NotNull String input);
  public abstract @NotNull List<String> getMove(@NotNull String input);
  public abstract @NotNull ActionType getAction(@NotNull String input);
}
