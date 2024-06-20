package org.utwente.game.model;

import java.util.*;
import org.utwente.market.model.Card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase {
  private PhaseType currentPhase;
  private Stack<Card> playedCards;
  private boolean moveThoughPlayers;

  public Phase() {

  }
}
