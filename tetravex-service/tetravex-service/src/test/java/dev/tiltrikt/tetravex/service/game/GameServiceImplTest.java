package dev.tiltrikt.tetravex.service.game;

import dev.tiltrikt.tetravex.exception.BusyFieldException;
import dev.tiltrikt.tetravex.exception.GameNotStartedException;
import dev.tiltrikt.tetravex.exception.GameNotWonException;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static dev.tiltrikt.tetravex.field.FieldType.GENERATED;
import static dev.tiltrikt.tetravex.field.FieldType.PLAYFIELD;
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
    gameService.replaceTile(new MoveMakeRequest(GENERATED, 1, 1, PLAYFIELD, 1, 1));

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
            .replaceTile(new MoveMakeRequest(GENERATED, 1, 1, GENERATED, 2, 2)));
  }

  @Test
  void ifGameIsWonExceptionMustBeThrown() {
    gameService.replaceTile(new MoveMakeRequest(GENERATED, 1, 1, PLAYFIELD, 1, 1));
    assertThrows(GameNotStartedException.class, () -> gameService
            .replaceTile(new MoveMakeRequest(GENERATED, 1, 1, PLAYFIELD, 1, 1)));
  }

  @Test
  void ifGameIsWonGetPointsMustReturnPoints() {
    gameService.replaceTile(new MoveMakeRequest(GENERATED, 1, 1, PLAYFIELD, 1, 1));
    assertDoesNotThrow(() -> gameService.getPoints());
  }

  @Test
  void ifGameIsNotWonGetPointsMustThrowException() {
    assertThrows(GameNotWonException.class, () -> gameService.getPoints());
  }
}