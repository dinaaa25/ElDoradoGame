package org.utwente.player.model;

import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

import lombok.Getter;

@Getter
public class PileBuilder {
  private CardPile pile;
  private CoinPile coinPile;
  private Player player;

  public PileBuilder() {
    this.reset();
  }

  public PileBuilder addCard(CardType cardType, int amount) {
    for (int i = 0; i < amount; i++) {
      this.pile.add(new Card(cardType));
    }
    return this;
  }

  public PileBuilder addCard(CardType cardType) {
    this.pile.add(new Card(cardType));
    return this;
  }

  public PileBuilder addStartingCards() {
    this.addCard(CardType.Matrose);
    this.addCard(CardType.Forscher, 3);
    this.addCard(CardType.Reisende, 4);
    return this;
  }

  public PileBuilder setPlayer(Player player) {
    this.player = player;
    this.pile.setPlayer(this.player);
    return this;
  }

  public void reset() {
    this.pile = new CardPile();
  }

  public PileBuilder setType(PileType type) {
    this.pile.setPileType(type);
    return this;
  }

  public CardPile buildDiscardPile() {
    this.reset();
    this.setType(PileType.Discard);
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public CardPile buildOutOfGamePile() {
    this.reset();
    this.setType(PileType.OutOfGame);
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public CoinPile buildOutOfGameCoinPile() {
    this.reset();
    this.coinPile = new CoinPile();
    this.setType(PileType.OutOfGame);
    this.coinPile.setPlayer(this.player);
    return this.coinPile;
  }

  public CardPile buildDrawPile() {
    this.reset();
    this.setType(PileType.Draw);
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public CardPile buildStartPile() {
    this.reset();
    this.addStartingCards();
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public CoinPile buildCaveCoinPile() {
    this.reset();
    this.coinPile = new CoinPile();
    this.setType(PileType.CaveCoin);
    this.coinPile.setPlayer(this.player);
    this.coinPile.add(new CaveCoin(1, CaveCoinType.Draw));
    this.coinPile.add(new CaveCoin(1, CaveCoinType.Symbol));
    return this.coinPile;
  }

  public CardPile build() {
    CardPile temporaryPile = this.pile;
    this.reset();
    return temporaryPile;
  }
}
