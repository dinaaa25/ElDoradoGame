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
    this.shuffle();
    List<Card> drawnCards = new ArrayList<>();
    int drawAmount = Math.min(cardAmount, this.resources.size());
    for (int i = 0; i < drawAmount; i++) {
      Card resource = this.resources.remove(0);
      drawnCards.add(resource);
    }
    return new CardPile(drawnCards, this.player, this.pileType);
  }

  public List<Card> getCards() {
    return this.resources;
  }
}
