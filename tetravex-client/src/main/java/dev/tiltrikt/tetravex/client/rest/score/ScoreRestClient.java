package dev.tiltrikt.tetravex.client.rest.score;

import dev.tiltrikt.tetravex.dto.ScoreDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ScoreRestClient {

  @NotNull List<ScoreDto> getTopScores(@NotNull String game);

  void reset();

}
