package dev.tiltrikt.tetravex.controller.v1;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.tetravex.dto.ScoreAddRequest;
import dev.tiltrikt.tetravex.dto.ScoreDto;
import dev.tiltrikt.tetravex.model.Score;
import dev.tiltrikt.tetravex.service.score.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/score")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreController {

  ScoreService scoreService;

  Mapper mapper;

  @Operation(summary = "Add new score")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Score was added"),
      @ApiResponse(responseCode = "400", description = "Wrong request")}
  )
  @PostMapping
  public void addScore(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody ScoreAddRequest scoreAddRequest) {

    Score score = mapper.map(scoreAddRequest, Score.class);
    score.setPlayer(jwt.getClaimAsString("name"));
    scoreService.addScore(score);
  }

  @Operation(summary = "Get top scores in the specific game")
  @ApiResponse(responseCode = "200", description = "Scores for this game",
      content = {@Content(mediaType = "application/json",
          array = @ArraySchema(schema = @Schema(implementation = ScoreDto.class)))})
  @GetMapping("{game}")
  public List<ScoreDto> getTopScores(@PathVariable String game) {

    return scoreService.getTopScores(game).stream()
        .map((score) -> mapper.map(score, ScoreDto.class))
        .toList();
  }

  @Operation(summary = "Delete all scores")
  @ApiResponse(responseCode = "200", description = "All scores were deleted")
  @DeleteMapping
  public void reset() {

    scoreService.reset();
  }
}
