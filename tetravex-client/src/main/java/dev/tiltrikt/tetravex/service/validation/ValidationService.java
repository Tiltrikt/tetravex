package dev.tiltrikt.tetravex.service.validation;

//import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ValidationService {

  void validateFieldSize(int min, int max, int input);

  void validateStartInput(@NotNull List<String> list);

  void validatePlayerInput(@NotNull List<String> list);

  void validateCommentInput(@NotNull List<String> list);

  void validateRatingInput(@NotNull List<String> list);

  void validateScoreInput(@NotNull List<String> list);

  void validateAuthenticationRequest(@NotNull List<String> list);
}
