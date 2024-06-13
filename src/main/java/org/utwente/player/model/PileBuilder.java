package org.utwente.player.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

import lombok.Getter;

@Getter
public class PileBuilder {
  private Pile pile;

  public PileBuilder() {
    this.pile = new Pile();
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
    this.addCard(CardType.Abenteurerin, 4);
    return this;
  }

  public PileBuilder setPlayer(Player player) {
    this.pile.setPlayer(player);
    return this;
  }

  private void clearPile() {
    this.pile = new Pile();
  }

  public Pile build() {
    Pile temporaryPile = this.pile;
    clearPile();
    return temporaryPile;
  }
}
