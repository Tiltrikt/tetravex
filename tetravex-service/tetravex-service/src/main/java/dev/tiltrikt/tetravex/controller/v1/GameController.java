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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
  public @NotNull List<FieldDto> startGame(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody GameStartRequest gameStartRequest) {

    multiplayerGameService.startGame(jwt.getClaimAsString("name"), gameStartRequest);
    return List.of(mapFieldToDto(multiplayerGameService.getGeneratedField(jwt.getClaimAsString("name"))),
        mapFieldToDto(multiplayerGameService.getPlayField(jwt.getClaimAsString("name"))));
  }


  @PutMapping("/replace")
  public @NotNull TetravexResponse replaceTile(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody MoveMakeRequest moveMakeRequest) {
    multiplayerGameService.replaceTile(jwt.getClaimAsString("name"), moveMakeRequest);
    return TetravexResponse.builder()
        .generatedField(mapFieldToDto(multiplayerGameService.getGeneratedField(jwt.getClaimAsString("name"))))
        .playField(mapFieldToDto(multiplayerGameService.getPlayField(jwt.getClaimAsString("name"))))
        .build();
  }

  @GetMapping("/generatedfield")
  public @NotNull FieldDto getGeneratedField(@AuthenticationPrincipal Jwt jwt) {
    Field field = multiplayerGameService.getGeneratedField(jwt.getClaimAsString("name"));
    return mapFieldToDto(field);
  }

  @GetMapping("/playfield")
  public @NotNull FieldDto getPlayField(@AuthenticationPrincipal Jwt jwt) {

    Field field = multiplayerGameService.getPlayField(jwt.getClaimAsString("name"));
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
