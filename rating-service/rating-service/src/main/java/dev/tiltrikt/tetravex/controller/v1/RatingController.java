package dev.tiltrikt.tetravex.controller.v1;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.tetravex.dto.RatingAddRequest;
import dev.tiltrikt.tetravex.model.Rating;
import dev.tiltrikt.tetravex.service.rating.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rating")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingController {

  RatingService ratingService;

  Mapper mapper;

  @Operation(summary = "Update rating")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Rating was updated"),
      @ApiResponse(responseCode = "400", description = "Wrong request")}
  )
  @PutMapping
  public void setRating(@NotBlank @CookieValue("Player") String player, @Valid @RequestBody RatingAddRequest ratingAddRequest) {

    log.info("Set rating request");
    Rating rating = mapper.map(ratingAddRequest, Rating.class);
    rating.setPlayer(player);
    ratingService.setRating(rating);
  }

  @Operation(summary = "Get average rating for specific game")
  @ApiResponse(responseCode = "200", description = "Average rating for this game",
      content = {@Content(mediaType = "application/json")})
  @GetMapping("average/{game}")
  public int getAverageRating(@RequestHeader(HttpHeaders.AUTHORIZATION) String authenticate, @PathVariable String game) {

    log.info(authenticate);
    return ratingService.getAverageRating(game);
  }

  @Operation(summary = "Get rating for specific game and player")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Rating for this game and player",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "400", description = "Wrong request")}
  )
  @GetMapping("{game}")
  public int getRating(@PathVariable String game, @CookieValue("Player") String player) {

    return ratingService.getRating(game, player);
  }

  @Operation(summary = "Delete all ratings")
  @ApiResponse(responseCode = "200", description = "All ratings were deleted")
  @DeleteMapping
  public void reset() {

    ratingService.reset();
  }
}