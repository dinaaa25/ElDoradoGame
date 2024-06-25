package org.utwente.game.model;

import lombok.Getter;

@Getter
public enum PhaseType {
  BUYING_AND_PLAYING_PHASE(0),
  DISCARD_PHASE(1),
  DRAW_PHASE(2);

  private final int index;

  PhaseType(int index) {
    this.index = index;
  }

  public PhaseType next() {
    int nextOrdinal = (this.ordinal() + 1) % PhaseType.values().length;
    return PhaseType.values()[nextOrdinal];
  }
}
