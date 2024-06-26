package dev.tiltrikt.tetravex.action;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("StringBufferReplaceableByString")
public class HelpAction implements Action {

  @Override
  public @NotNull String doAction(@NotNull String input) {
    StringBuilder usageInfo = new StringBuilder();

    usageInfo.append("**Usage Info:**\n");
    usageInfo.append("* `help` - Shows this information.\n");
    usageInfo.append("* `register <name> <password>` - Register new player.\n");
    usageInfo.append("* `authenticate <name> <password>` - Authenticate player.\n");
    usageInfo.append("* `start <size>` - Starts a new game with the specified size.\n");
    usageInfo.append("* `move (FROM)<field[above/down] row column> (TO)<field row column>` - MoveMakeRequest a tile from one field to another.\n");
    usageInfo.append("* `comment [add + comment / get / reset]` - Add, get, or reset a comment.\n");
    usageInfo.append("* `rating [add + rating / get / average / reset]` - Add, get, average, or reset the rating.\n");
    usageInfo.append("* `score [get / reset]` - Add, get, or reset the score.\n");
    usageInfo.append("* `exit` - Exit the game.\n");

    return usageInfo.toString();
  }
}
