package org.utwente.game.model;

import java.util.*;

import org.utwente.Tile.Tile;

import lombok.Getter;
import lombok.Setter;
import org.utwente.market.model.Resource;

@Getter
@Setter
public class Phase {
  private PhaseType currentPhase;
  private Stack<Resource> playedResources;
  private boolean moveThoughPlayers;
  private Tile selectedTile;

  public Phase() {
    this.currentPhase = PhaseType.BUYING_AND_PLAYING_PHASE;
    this.playedResources = new Stack<>();
    moveThoughPlayers = false;
  }

  public void next() {
    this.currentPhase = currentPhase.next();
  }

  public void addPlayedResource(Resource resource) {
    this.playedResources.push(resource);
  }

  @Override
  public String toString() {
    return "Phase{" +
            "currentPhase=" + currentPhase +
            ", playedResources=" + playedResources +
            ", moveThoughPlayers=" + moveThoughPlayers +
            ", selectedTile=" + selectedTile +
            '}';
  }
}
