package dev.tiltrikt.tetravex.controller.v1;

import dev.tiltrikt.mapper.core.Mapper;
import dev.tiltrikt.tetravex.dto.RatingAddRequest;
import dev.tiltrikt.tetravex.model.Rating;
import dev.tiltrikt.tetravex.service.rating.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
  public void setRating(Principal principal, @Valid @RequestBody RatingAddRequest ratingAddRequest) {

    Rating rating = mapper.map(ratingAddRequest, Rating.class);
    rating.setPlayer(principal.getName());
    ratingService.setRating(rating);
  }

  @Operation(summary = "Get average rating for specific game")
  @ApiResponse(responseCode = "200", description = "Average rating for this game",
      content = {@Content(mediaType = "application/json")})
  @GetMapping("average/{game}")
  public int getAverageRating(@PathVariable String game) {

    return ratingService.getAverageRating(game);
  }

  @Operation(summary = "Get rating for specific game and player")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Rating for this game and player",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "400", description = "Wrong request")}
  )
  @GetMapping("{game}")
  public int getRating(Principal principal, @PathVariable String game) {

    return ratingService.getRating(game, principal.getName());
  }

  @Operation(summary = "Delete all ratings")
  @ApiResponse(responseCode = "200", description = "All ratings were deleted")
  @DeleteMapping
  public void reset() {

    ratingService.reset();
  }
}