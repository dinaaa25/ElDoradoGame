package org.utwente.game.model;

import java.util.*;

import org.utwente.Tile.Tile;

import lombok.Getter;
import lombok.Setter;
import org.utwente.market.model.Resource;
import org.utwente.util.ValidationResult;

@Getter
@Setter
public class Phase {
    private PhaseType currentPhase;
    private Stack<Resource> playedResources;
    private Stack<Resource> selectedResources;
    private boolean moveThoughPlayers;
    private Tile selectedTile;
    private ValidationResult actionMessage;

    public Phase() {
        this.currentPhase = PhaseType.BUYING_AND_PLAYING_PHASE;
        this.playedResources = new Stack<>();
        this.selectedResources = new Stack<>();
        moveThoughPlayers = false;
    }

    public void next() {
        System.out.println("[ DEBUG A ] " + this.toString());
        this.selectedResources.removeAllElements();
        this.selectedTile = null;
        this.currentPhase = currentPhase.next();
        // print the phase string to console,for debugging
    }



    public void addPlayedResource(Resource resource) {
        this.playedResources.push(resource);
    }

    @Override
    public String toString() {
        return "Phase{" +
                "currentPhase=" + currentPhase +
                ", playedResources=" + playedResources +
                ", selectedResources=" + selectedResources +
                ", moveThoughPlayers=" + moveThoughPlayers +
                ", selectedTile=" + selectedTile +
                '}';
    }
}
