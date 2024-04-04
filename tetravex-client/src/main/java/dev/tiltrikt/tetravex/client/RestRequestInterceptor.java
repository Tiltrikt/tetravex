package dev.tiltrikt.tetravex.client;

import dev.tiltrikt.tetravex.exception.NoPlayerException;
import dev.tiltrikt.tetravex.player.Player;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestRequestInterceptor implements ClientHttpRequestInterceptor {

  @NotNull Player player;

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

    if (player.getName() == null) {
      throw new NoPlayerException("Player was not set");
    }
    request.getHeaders().add(HttpHeaders.COOKIE, "Player=" + player.getName());

    return execution.execute(request, body);
  }
}
