package dev.tiltrikt.tetravex.client.rest.score;

import dev.tiltrikt.tetravex.dto.ScoreAddRequest;
import org.jetbrains.annotations.NotNull;

public interface ScoreRestClient {

  void addScore(@NotNull ScoreAddRequest scoreAddRequest, @NotNull String player);
}
