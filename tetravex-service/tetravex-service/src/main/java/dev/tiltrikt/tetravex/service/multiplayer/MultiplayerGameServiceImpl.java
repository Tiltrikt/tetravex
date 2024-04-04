package dev.tiltrikt.tetravex.service.multiplayer;

import dev.tiltrikt.tetravex.dto.GameStartRequest;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.exception.GameAlreadyStartedException;
import dev.tiltrikt.tetravex.exception.GameIsWon;
import dev.tiltrikt.tetravex.exception.PlayerNotFoundException;
import dev.tiltrikt.tetravex.model.Field;
import dev.tiltrikt.tetravex.service.game.GameService;
import dev.tiltrikt.tetravex.service.game.GameServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MultiplayerGameServiceImpl implements MultiplayerGameService {

  @NotNull Map<String, GameService> activeGames = new HashMap<>();


  @Override
  public void startGame(@NotNull String player, @NotNull GameStartRequest gameStartRequest) {

    if (activeGames.get(player) != null) {
      throw new GameAlreadyStartedException("This player already playing");
    }
    activeGames.put(player, new GameServiceImpl(gameStartRequest.getSize()));
  }

  @Override
  public void replaceTile(@NotNull String player, @NotNull MoveMakeRequest moveMakeRequest) {

    playerPresent(player);
    try {
      activeGames.get(player).replaceTile(moveMakeRequest);
    } catch (GameIsWon ex) {
      activeGames.remove(player);
      ex.setPlayer(player);
      throw ex;
    }

  }

  @Override
  public @NotNull Field getGeneratedField(@NotNull String player) {

    playerPresent(player);
    return activeGames.get(player).getGeneratedField();
  }

  @Override
  public @NotNull Field getPlayField(@NotNull String player) {

    playerPresent(player);
    return activeGames.get(player).getPlayField();
  }

  private void playerPresent(@NotNull String player) {
    if (activeGames.get(player) == null) {
      throw new PlayerNotFoundException("No active game with this player");
    }
  }
}
