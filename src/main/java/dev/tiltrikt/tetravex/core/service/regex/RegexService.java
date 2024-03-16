package dev.tiltrikt.tetravex.core.service.regex;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class RegexService {

  @NotNull Pattern movePattern = Pattern.compile("(?<field>[a-z]*)\\s?(?<position>(?:\\d ?){2})", Pattern.CASE_INSENSITIVE);
  @NotNull Pattern actionPattern = Pattern.compile("^(?<action>[a-z]+)", Pattern.CASE_INSENSITIVE);
  @NotNull Pattern commentPattern = Pattern.compile("^[a-z]+\\s+(?<parameter>(?:\\w+\\s*)+)$", Pattern.CASE_INSENSITIVE);

  public abstract @NotNull List<String> getMoveParameters(@NotNull String input);
  public abstract @NotNull String getAction(@NotNull String input);
  public abstract @NotNull List<String> getParameters(@NotNull String input);
}
