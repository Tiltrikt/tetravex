package dev.tiltrikt;

import dev.tiltrikt.entity.Rating;
import dev.tiltrikt.entity.Score;
import dev.tiltrikt.service.rating.RatingService;
import dev.tiltrikt.service.rating.RatingServiceJdbc;
import dev.tiltrikt.ui.ConsoleUi;
import dev.tiltrikt.ui.UserInterface;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Main {

  static UserInterface userInterface = new ConsoleUi();
  static RatingService ratingService = new RatingServiceJdbc();

  public static void main(String[] args) {
    ratingService.setRating(new Rating("jebalo", "off", 7));
    ratingService.setRating(new Rating("jebalo", "on", 10));
    ratingService.setRating(new Rating("jebalo", "set", 15));
    System.out.println(ratingService.getAverageRating("jebalo"));
    userInterface.bootstrap();
  }
}
