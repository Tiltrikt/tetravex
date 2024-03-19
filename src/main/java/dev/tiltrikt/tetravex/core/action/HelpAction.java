package dev.tiltrikt.tetravex.core.action;

import dev.tiltrikt.tetravex.core.action.annotation.ActionClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ActionClass
@SuppressWarnings("StringBufferReplaceableByString")
public class HelpAction extends Action {

  public HelpAction(@Nullable Action action) {
    super(action, ActionType.HELP);
  }

  @Override
  protected @NotNull String doAction(@NotNull String input) {
    StringBuilder usageInfo = new StringBuilder();

    usageInfo.append("**Usage Info:**\n");
    usageInfo.append("* `help` - Shows this information.\n");
    usageInfo.append("* `player <name>` - Set player with the specified name.\n");
    usageInfo.append("* `start <size>` - Starts a new game with the specified size.\n");
    usageInfo.append("* `move (FROM)<field[above/down] row column> (TO)<field row column>` - Move a tile from one field to another.\n");
    usageInfo.append("* `comment [add + comment / get / reset]` - Add, get, or reset a comment.\n");
    usageInfo.append("* `rating [add + rating / get / average / reset]` - Add, get, average, or reset the rating.\n");
    usageInfo.append("* `score [add + score / get / reset]` - Add, get, or reset the score.\n");
    usageInfo.append("* `exit` - Exit the game.\n");

    return usageInfo.toString();
  }
}
