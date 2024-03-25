package dev.tiltrikt.tetravex.core.service.score;

import dev.tiltrikt.tetravex.core.exception.ScoreException;
import dev.tiltrikt.tetravex.core.model.Score;
import dev.tiltrikt.tetravex.core.repository.ScoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreServiceJpa implements ScoreService {

  ScoreRepository scoreRepository;

  @Override
  public void addScore(Score score) throws ScoreException {
    scoreRepository.save(score);
  }

  @Override
  public List<Score> getTopScores(String game) throws ScoreException {
    return scoreRepository.findFirstByOrderByPointsDesc();
  }

  @Override
  public void reset() throws ScoreException {
    scoreRepository.deleteAll();
  }
}
