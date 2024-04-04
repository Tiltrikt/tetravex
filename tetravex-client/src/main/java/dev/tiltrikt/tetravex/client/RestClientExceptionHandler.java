package dev.tiltrikt.tetravex.client;

import dev.tiltrikt.tetravex.exception.RestResponseException;
import dev.tiltrikt.tetravex.service.regex.RegexService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestClientExceptionHandler implements ResponseErrorHandler {

  @NotNull RegexService regexService;

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {

    if (response.getHeaders().containsKey("Win")){
      return true;
    }
    return response.getStatusCode().isError();
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {

    InputStream inputStream = response.getBody();
    String body = new BufferedReader(new InputStreamReader(inputStream))
        .lines().collect(Collectors.joining("\n"));

    throw new RestResponseException(regexService.getError(body));
  }
}
