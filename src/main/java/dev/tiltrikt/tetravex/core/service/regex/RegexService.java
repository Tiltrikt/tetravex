package dev.tiltrikt.tetravex.core.service.regex;

import dev.tiltrikt.tetravex.core.exception.RegexNotFoundException;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface RegexService {

  @NotNull List<String> getMoveParameters(@NotNull String input) throws RegexNotFoundException;

  @NotNull String getAction(@NotNull String input) throws RegexNotFoundException;

  @NotNull List<String> getParameters(@NotNull String input) throws RegexNotFoundException;
}

