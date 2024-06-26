package org.utwente.player.model;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.player.PlayerColor;
import org.utwente.market.model.Card;
import org.utwente.util.ShuffleUtils;

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
    private CardPile outOfGameCoinsPile;

    public Player(String name) {
        this.name = name;
        this.blockades = new ArrayList<Blockade>();
        PileBuilder builder = new PileBuilder();
        builder.setPlayer(this);
        this.discardPile = builder.buildDiscardPile();
        this.outOfGamePile = builder.buildOutOfGamePile();
        CardPile startPile = builder.buildStartPile();
        this.playPile = startPile.draw(DECK_CARDS);
        this.playPile.setPileType(PileType.Play);
        this.drawPile = startPile;
        this.caveCoinPile = builder.buildCaveCoinPile();
        this.outOfGameCoinsPile = builder.buildOutOfGamePile();
    }

    public String getName() {
        return this.name;
    }

    public void discardCard(Card card) {
        discardPile.add(card);
        playPile.remove(card);
    }

    public void drawPlayCards() {
        int currentCards = this.playPile.getCards().size();
        int cardsToDraw = DECK_CARDS - currentCards;
        if (cardsToDraw > 0) {
            CardPile drawnCards = this.drawPile.draw(cardsToDraw);
            if (drawnCards.getCards().size() < cardsToDraw && !this.discardPile.isEmpty()) {
                // Shuffle discard pile into draw pile if draw pile is exhausted
                ShuffleUtils.shuffle(this.discardPile.getCards());
                this.drawPile.addAll(this.discardPile.draw(this.discardPile.getCards().size()));
                drawnCards.addAll(this.drawPile.draw(cardsToDraw - drawnCards.getCards().size()));
            }
            this.playPile.addAll(drawnCards);
        }
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

    public void discardCoin(CaveCoin coin) {
        this.caveCoinPile.remove(coin);
    }

}
