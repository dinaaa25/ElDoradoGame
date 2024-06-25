package org.utwente.player.model;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.player.PlayerColor;
import org.utwente.market.model.Card;
import  org.utwente.util.ShuffleUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private CardPile faceUpDiscardPile;

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
        this.faceUpDiscardPile = new CardPile(new ArrayList<>(), this, PileType.FaceUpDiscard);

    }
    public CardPile getFaceUpDiscardPile() {
        return faceUpDiscardPile;
    }

    public void clearFaceUpDiscardPile() {
        faceUpDiscardPile = new CardPile(new ArrayList<>(), this, PileType.FaceUpDiscard);
    }

    public String getName() {
        return this.name;
    }

    public void discardCard(Card card) {
        faceUpDiscardPile.add(card);
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


    public void xRayEyes() {
        System.out.println("[xRayEyes]");
        System.out.println("drawPile: " + getCardsAsString(drawPile));
        System.out.println("discardPile: " + getCardsAsString(discardPile));
        System.out.println("playPile: " + getCardsAsString(playPile));
        System.out.println("faceUpDiscardPile: " + getCardsAsString(faceUpDiscardPile));
        System.out.println("outOfGamePile: " + getCardsAsString(outOfGamePile));
        System.out.println("caveCoinPile: " + getCoinsAsString(caveCoinPile));
        System.out.println("outOfGameCoinsPile: " + getCoinsAsString(outOfGameCoinsPile));
    }

    private String getCardsAsString(CardPile cardPile) {
        if (cardPile == null || cardPile.getCards() == null) {
            return "No cards";
        }
        return cardPile.getCards().stream()
                .map(card -> card.getCardType().name())
                .collect(Collectors.joining(", "));
    }

    private String getCoinsAsString(CoinPile coinPile) {
        if (coinPile == null || coinPile.getResources() == null) {
            return "No coins";
        }
        return coinPile.getResources().stream()
                .map(coin -> coin.getCaveCoinType().name())
                .collect(Collectors.joining(", "));
    }
}
