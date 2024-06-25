package org.utwente.player.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

import lombok.Getter;

@Getter
public class PileBuilder {
  private CardPile pile;
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
    this.addCard(CardType.Wissenschaftlerin);
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

  public CardPile build() {
    CardPile temporaryPile = this.pile;
    this.reset();
    return temporaryPile;
  }
}
