package org.utwente.player.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

import lombok.Getter;

@Getter
public class PileBuilder {
  private Pile pile;
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
    this.addCard(CardType.Kapitan);
    this.addCard(CardType.Entdecker, 3);
    this.addCard(CardType.Reisende, 4);
    return this;
  }

  public PileBuilder setPlayer(Player player) {
    this.player = player;
    this.pile.setPlayer(this.player);
    return this;
  }

  public void reset() {
    this.pile = new Pile();
  }

  public PileBuilder setType(PileType type) {
    this.pile.setPileType(type);
    return this;
  }

  public Pile buildDiscardPile() {
    this.reset();
    this.setType(PileType.Discard);
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public Pile buildOutOfGamePile() {
    this.reset();
    this.setType(PileType.OutOfGame);
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public Pile buildDrawPile() {
    this.reset();
    this.setType(PileType.Draw);
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public Pile buildStartPile() {
    this.reset();
    this.addStartingCards();
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public Pile buildCaveCoinPile() {
    this.reset();
    this.setType(PileType.CaveCoin);
    this.pile.setPlayer(this.player);
    return this.pile;
  }

  public Pile build() {
    Pile temporaryPile = this.pile;
    this.reset();
    return temporaryPile;
  }
}
