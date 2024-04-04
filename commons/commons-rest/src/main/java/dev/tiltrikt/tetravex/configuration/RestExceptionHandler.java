package dev.tiltrikt.tetravex.configuration;

import dev.tiltrikt.commons.exception.GameException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class RestExceptionHandler {

  @NotNull
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> notValidField(MethodArgumentNotValidException ex) {

    Map<String, String> error = new TreeMap<>();
    for (FieldError fieldError : ex.getFieldErrors()) {
      error.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return error;
  }

  @NotNull
  @ExceptionHandler(GameException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> gameException(@NotNull GameException ex) {

    Map<String, String> error = new TreeMap<>();
    error.put(String.valueOf(ex.getClass()), ex.getMessage());
    return error;
  }
}
