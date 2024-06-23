package org.utwente.player.model;

import org.utwente.market.model.Card;
import java.util.*;

public class CardPile extends Pile<Card> {

  public CardPile() {

  }

  public CardPile(List<Card> cards, Player player, PileType pileType) {
    super(cards, player, pileType);
  }

  public CardPile draw(int cardAmount) {
    if (cardAmount > this.resources.size() && pileType == PileType.Draw) {
      Pile<Card> discardPile = player.getDiscardPile();
      this.resources.addAll(discardPile.getResources());
    }

    this.shuffle();
    List<Card> drawnCards = new ArrayList<>();
    int drawAmount = Math.min(cardAmount, this.resources.size());
    for (int i = 0; i < drawAmount; i++) {
      drawnCards.add(this.resources.get(i));
      this.resources.remove(i);
    }
    return new CardPile(drawnCards, this.player, this.pileType);
  }

  public List<Card> getCards() {
    return this.resources;
  }
}
