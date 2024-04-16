package dev.tiltrikt.tetravex.controller.v1;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.tetravex.dto.*;
import dev.tiltrikt.tetravex.model.Field;
import dev.tiltrikt.tetravex.service.multiplayer.MultiplayerGameService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
  public @NotNull List<FieldDto> startGame(Principal principal, @Valid @RequestBody GameStartRequest gameStartRequest) {

    multiplayerGameService.startGame(principal.getName(), gameStartRequest);
    return List.of(mapFieldToDto(multiplayerGameService.getGeneratedField(principal.getName())),
        mapFieldToDto(multiplayerGameService.getPlayField(principal.getName())));
  }


  @PutMapping("/replace")
  public @NotNull TetravexResponse replaceTile(Principal principal, @Valid @RequestBody MoveMakeRequest moveMakeRequest) {
    multiplayerGameService.replaceTile(principal.getName(), moveMakeRequest);
    return TetravexResponse.builder()
        .generatedField(mapFieldToDto(multiplayerGameService.getGeneratedField(principal.getName())))
        .playField(mapFieldToDto(multiplayerGameService.getPlayField(principal.getName())))
        .build();
  }

  @GetMapping("/generatedfield")
  public @NotNull FieldDto getGeneratedField(Principal principal) {
    Field field = multiplayerGameService.getGeneratedField(principal.getName());
    return mapFieldToDto(field);
  }

  @GetMapping("/playfield")
  public @NotNull FieldDto getPlayField(Principal principal) {

    Field field = multiplayerGameService.getPlayField(principal.getName());
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
