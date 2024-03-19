package dev.tiltrikt.tetravex.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuppressWarnings("RedundantIfStatement")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score {

  String game;

  String player;

  int points;

  Date playedOn;

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Score)) return false;
    final Score other = (Score) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$game = this.getGame();
    final Object other$game = other.getGame();
    if (this$game == null ? other$game != null : !this$game.equals(other$game)) return false;
    final Object this$player = this.getPlayer();
    final Object other$player = other.getPlayer();
    if (this$player == null ? other$player != null : !this$player.equals(other$player)) return false;
    if (this.getPoints() != other.getPoints()) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Score;
  }
}
