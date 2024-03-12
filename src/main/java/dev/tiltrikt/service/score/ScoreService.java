package dev.tiltrikt.service.score;

import dev.tiltrikt.entity.Score;
import dev.tiltrikt.exception.ScoreException;

import java.util.List;

public interface ScoreService {

  void addScore(Score score) throws ScoreException;

  List<Score> getTopScores(String game) throws ScoreException;

  void reset() throws ScoreException;
}
