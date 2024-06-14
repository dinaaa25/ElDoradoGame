package org.utwente.player.model;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.player.PlayerColor;

import lombok.Getter;
import lombok.Setter;
import org.utwente.player.PlayerColor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class Player {

    private String name;
    private List<Blockade> blockades;
    @Getter
    @Setter
    private PlayerColor color;
    private Pile discardPile;
    private Pile playPile;
    private Pile outOfGamePile;
    private Pile drawPile;

    public Player(String name) {
        this.name = name;
        this.blockades = new ArrayList<Blockade>();
        PileBuilder builder = new PileBuilder();
        this.discardPile = builder.setPlayer(this).build();
        Pile startPile = builder.setPlayer(this).addStartingCards().build();
        this.outOfGamePile = builder.setPlayer(this).build();
        this.playPile = startPile.draw(4);
        this.drawPile = startPile;
    }

    public String getName() {
        return this.name;
    }

    public void addBlockade(Blockade b) {
        this.blockades.add(b);
    }

    public int getBlockadeCount() {
        return blockades.size();
    }

    public Blockade getMaxBlockade() {
        return blockades.stream()
                .max(Comparator.comparingInt(Blockade::getPower))
                .orElse(null);
    }

    @Override
    public boolean equals(Object p) {
        if (p instanceof Player) {
            Player elPlayer = (Player) p;
            return this.getName().equals(elPlayer.getName());
        }
        return false;
    }

}
