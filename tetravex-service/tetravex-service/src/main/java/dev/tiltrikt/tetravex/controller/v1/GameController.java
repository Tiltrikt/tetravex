package dev.tiltrikt.tetravex.controller.v1;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.tetravex.dto.FieldDto;
import dev.tiltrikt.tetravex.dto.GameStartRequest;
import dev.tiltrikt.tetravex.dto.MoveMakeRequest;
import dev.tiltrikt.tetravex.dto.TileDto;
import dev.tiltrikt.tetravex.model.Field;
import dev.tiltrikt.tetravex.service.multiplayer.MultiplayerGameService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tetravex")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameController {

  @NotNull MultiplayerGameService multiplayerGameService;

  @NotNull Mapper mapper;

  @PutMapping("/start")
  public @NotNull List<FieldDto> startGame(@NotBlank @CookieValue("Player") String player, @Valid @RequestBody GameStartRequest gameStartRequest) {

    multiplayerGameService.startGame(player, gameStartRequest);
    return List.of(mapFieldToDto(multiplayerGameService.getGeneratedField(player)),
        mapFieldToDto(multiplayerGameService.getPlayField(player)));
  }

  @PutMapping("/replace")
  public @NotNull List<FieldDto> replaceTile(@NotBlank @CookieValue("Player") String player, @Valid @RequestBody MoveMakeRequest moveMakeRequest) {
    multiplayerGameService.replaceTile(player, moveMakeRequest);
    return List.of(mapFieldToDto(multiplayerGameService.getGeneratedField(player)),
        mapFieldToDto(multiplayerGameService.getPlayField(player)));
  }

  @GetMapping("/generatedfield")
  public @NotNull FieldDto getGeneratedField(@NotBlank @CookieValue("Player") String player) {
    Field field = multiplayerGameService.getGeneratedField(player);
    return mapFieldToDto(field);
  }

  @GetMapping("/playfield")
  public @NotNull FieldDto getPlayField(@NotBlank @CookieValue("Player") String player) {

    Field field = multiplayerGameService.getPlayField(player);
    return mapFieldToDto(field);
  }

  @NotNull
  private FieldDto mapFieldToDto(Field source) {
    List<Optional<TileDto>> tileList = source.getTileList().stream()
        .map(optional -> optional.map(tile -> mapper.map(tile, TileDto.class)))
        .toList();

    FieldDto fieldDto = new FieldDto();
    fieldDto.setTileList(tileList);
    fieldDto.setSize(source.getSize());
    return fieldDto;
  }
}
