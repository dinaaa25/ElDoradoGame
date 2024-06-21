package org.utwente.util.event;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPlayersEvent extends Event {
  List<String> playerNames;

  public AddPlayersEvent(List<String> playerNames) {
    this.playerNames = playerNames;
  }

  @Override
  public String toString() {
    return "AddPlayersEvent{" +
            "playerNames=" + playerNames +
            '}';
  }

}
