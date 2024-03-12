package dev.tiltrikt.service.regex;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class RegexService {

  @NotNull Pattern sizePattern = Pattern.compile("(?<size>\\d{1})");
  @NotNull Pattern movePattern = Pattern.compile("(?<field>[a-zA-Z]*)\\s?(?<position>(?:\\d ?){2})");

  public abstract @NotNull String getSize(@NotNull String input);
  public abstract @NotNull List<String> getMove(@NotNull String input);
}
