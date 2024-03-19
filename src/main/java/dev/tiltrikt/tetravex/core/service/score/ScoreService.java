package dev.tiltrikt.tetravex.core.service.score;

import dev.tiltrikt.tetravex.core.exception.ScoreException;
import dev.tiltrikt.tetravex.core.model.Score;

import java.util.List;

public interface ScoreService {

  void addScore(Score score) throws ScoreException;

  List<Score> getTopScores(String game) throws ScoreException;

  void reset() throws ScoreException;
}
