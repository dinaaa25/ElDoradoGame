package org.utwente.player.model;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.player.PlayerColor;
import org.utwente.market.model.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class Player {
    public static final int DECK_CARDS = 4;
    private String name;
    private List<Blockade> blockades;
    private PlayerColor color;
    private CardPile discardPile;
    private CardPile playPile;
    private CardPile outOfGamePile;
    private CardPile drawPile;
    private CoinPile caveCoinPile;
    private CoinPile outOfGameCoinsPile;

    public Player(String name) {
        this.name = name;
        this.blockades = new ArrayList<Blockade>();
        PileBuilder builder = new PileBuilder();
        builder.setPlayer(this);
        this.discardPile = builder.buildDiscardPile();
        CardPile startPile = builder.buildStartPile();
        this.playPile = startPile.draw(DECK_CARDS);
        this.playPile.setPileType(PileType.Play);
        this.drawPile = startPile;
        this.caveCoinPile = new CoinPile();
    }

    public String getName() {
        return this.name;
    }

    public void discardCard(Card card) {
        card.setSelected(false);
        discardPile.add(card);
        playPile.remove(card);
    }

    public void removeCardFromGame(Card card) {
        outOfGamePile.add(card);
        playPile.remove(card);
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
