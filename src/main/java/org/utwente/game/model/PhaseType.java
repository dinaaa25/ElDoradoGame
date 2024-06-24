package org.utwente.game.model;

public enum PhaseType {
  BUYING_AND_PLAYING_PHASE(0),
  DISCARD_PHASE(1);

  private final int i;

  PhaseType(int i) {
    this.i = i;
  }

  public PhaseType next() {
    int nextOrdinal = (this.ordinal() + 1) % PhaseType.values().length;
    return PhaseType.values()[nextOrdinal];
  }
}
