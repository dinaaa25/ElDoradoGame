package org.utwente.game.model;

import java.util.*;

import org.utwente.Tile.Tile;
import org.utwente.market.model.Card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase {
  private PhaseType currentPhase;
  private Stack<Card> playedCards;
  private boolean moveThoughPlayers;
  private Tile selectedTile;

  public Phase() {
    this.currentPhase = PhaseType.BUYING_AND_PLAYING_PHASE;
    this.playedCards = new Stack<>();
    moveThoughPlayers = false;
  }

  public void next() {
    this.currentPhase = currentPhase.next();
  }

  @Override
  public String toString() {
    return "Phase{" +
            "currentPhase=" + currentPhase +
            ", playedCards=" + playedCards +
            ", moveThoughPlayers=" + moveThoughPlayers +
            ", selectedTile=" + selectedTile +
            '}';
  }
}
