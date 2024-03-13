//package dev.tiltrikt.tetravex.action;
//
//import dev.tiltrikt.tetravex.configuration.GameConfiguration;
//import dev.tiltrikt.tetravex.exception.CommentException;
//import dev.tiltrikt.tetravex.service.rating.RatingService;
//import dev.tiltrikt.tetravex.service.rating.RatingServiceJdbc;
//import dev.tiltrikt.tetravex.service.game.GameService;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class RatingAction extends Action {
//
//  @NotNull RatingService ratingService = new RatingServiceJdbc();
//
//  public RatingAction(@Nullable Action action) {
//    super(action);
//  }
//
//  @Override
//  public void doAction(@NotNull ActionType actionType, @NotNull List<String> actionArgs, @NotNull GameService gameService) {
//    if (actionType == ActionType.RATING) {
//
//      String rating = ratingService.getAverageRating(GameConfiguration.GAME);
//      consoleService.printTopScore(scoreAction);
//
//    } else if (action != null) {
//      action.doAction(actionType, actionArgs, gameService);
//    } else {
//      throw new CommentException("End of chain, wrong action");
//    }
//  }
//}
