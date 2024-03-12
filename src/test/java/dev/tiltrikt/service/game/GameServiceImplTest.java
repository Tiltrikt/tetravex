package dev.tiltrikt.service.game;

import dev.tiltrikt.exception.OutOfBoundsException;
import dev.tiltrikt.model.Tile;
import dev.tiltrikt.service.game.GameService;
import dev.tiltrikt.service.game.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static dev.tiltrikt.service.game.GameServiceImpl.FieldPlayground.GENERATED;
import static dev.tiltrikt.service.game.GameServiceImpl.FieldPlayground.PLAYFIELD;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

  GameService gameService;

  @BeforeEach
  void createGameService() {
    gameService = new GameServiceImpl(1);
  }

  @Test
  void onReplacingTileMustBeReplaced() {
    Optional<Tile> tile = gameService.getGeneratedField().getTileList().getFirst();
    gameService.replaceTile(GENERATED, 1, 1, PLAYFIELD, 1, 1);

    assertEquals(tile, gameService.getPlayField().getTileList().getFirst());
  }

  @Test
  void onCreatingGameServiceGeneratedFieldMustHaveTiles() {
    assertDoesNotThrow(() -> gameService.getGeneratedField().getTileList().getFirst().orElseThrow());
  }

  @Test
  void onCreatingGameServicePlayFieldBeEmpty() {
    assertThrows(NoSuchElementException.class, () -> gameService.getPlayField().getTileList().getFirst().orElseThrow());
  }

  @Test
  void onPuttingTooHighInputForMoveExceptionMustBeThrown() {
    assertThrows(OutOfBoundsException.class, () -> gameService
            .replaceTile(GENERATED, 2, 2, PLAYFIELD, 2, 2));
  }

  @Test
  void onPuttingTooLowInputForMoveExceptionMustBeThrown() {
    assertThrows(OutOfBoundsException.class, () -> gameService
            .replaceTile(GENERATED, 0, 0, PLAYFIELD, 0, 0));
  }

  @Test
  void onPuttingRightInputForMoveNoExceptionMustBeThrown() {
    assertDoesNotThrow(() -> gameService
            .replaceTile(GENERATED, 1, 1, PLAYFIELD, 1, 1));
  }
}