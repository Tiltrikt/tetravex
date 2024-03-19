package dev.tiltrikt.tetravex.core.service.game;

import dev.tiltrikt.tetravex.core.exception.BusyFieldException;
import dev.tiltrikt.tetravex.core.exception.GameNotStartedException;
import dev.tiltrikt.tetravex.core.exception.GameNotWonException;
import dev.tiltrikt.tetravex.core.service.game.dto.Move;
import dev.tiltrikt.tetravex.core.service.game.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static dev.tiltrikt.tetravex.core.service.game.model.FieldType.GENERATED;
import static dev.tiltrikt.tetravex.core.service.game.model.FieldType.PLAYFIELD;
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
    gameService.replaceTile(new Move(GENERATED, 1, 1, PLAYFIELD, 1, 1));

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
  void onReplacingToBusyFieldExceptionMustBeThrown() {
    gameService = new GameServiceImpl(2);
    assertThrows(BusyFieldException.class, () -> gameService
            .replaceTile(new Move(GENERATED, 1, 1, GENERATED, 2, 2)));
  }

  @Test
  void ifGameIsWonExceptionMustBeThrown() {
    gameService.replaceTile(new Move(GENERATED, 1, 1, PLAYFIELD, 1, 1));
    assertThrows(GameNotStartedException.class, () -> gameService
            .replaceTile(new Move(GENERATED, 1, 1, PLAYFIELD, 1, 1)));
  }

  @Test
  void ifGameIsWonGetPointsMustReturnPoints() {
    gameService.replaceTile(new Move(GENERATED, 1, 1, PLAYFIELD, 1, 1));
    assertDoesNotThrow(() -> gameService.getPoints());
  }

  @Test
  void ifGameIsNotWonGetPointsMustThrowException() {
    assertThrows(GameNotWonException.class, () -> gameService.getPoints());
  }
}