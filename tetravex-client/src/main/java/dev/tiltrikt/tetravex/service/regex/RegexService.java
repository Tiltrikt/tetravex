package dev.tiltrikt.tetravex.service.regex;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface RegexService {

  @NotNull List<String> getMoveParameters(@NotNull String input);

  @NotNull String getAction(@NotNull String input);

  @NotNull List<String> getParameters(@NotNull String input);

  @NotNull String getError(@NotNull String input);
}

