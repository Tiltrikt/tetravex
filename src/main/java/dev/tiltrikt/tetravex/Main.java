package dev.tiltrikt.tetravex;

import dev.tiltrikt.tetravex.ui.UserInterface;
import dev.tiltrikt.tetravex.ui.ConsoleUi;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Main {

  static UserInterface userInterface = new ConsoleUi();
//  static RatingService ratingService = new RatingServiceJdbc();

  public static void main(String[] args) {
//    ratingService.setRating(new Rating("jebalo", "off", 7));
//    ratingService.setRating(new Rating("jebalo", "on", 10));
//    ratingService.setRating(new Rating("jebalo", "set", 15));
//    System.out.println(ratingService.getAverageRating("jebalo"));
    userInterface.bootstrap();
  }
}
