package dev.tiltrikt.tetravex.service.score;

import dev.tiltrikt.tetravex.entity.Score;
import dev.tiltrikt.tetravex.exception.ScoreException;

import java.util.List;

public interface ScoreService {

  void addScore(Score score) throws ScoreException;

  List<Score> getTopScores(String game) throws ScoreException;

  void reset() throws ScoreException;
}
