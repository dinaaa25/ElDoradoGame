package org.utwente.player.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.utwente.market.model.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pile {
  protected List<Card> cards;
  protected Player player;
  protected PileType pileType;

  public Pile() {
    this.cards = new ArrayList<>();
  }

  public Pile(List<Card> cards, Player player, PileType pileType) {
    this.cards = cards;
    this.player = player;
    this.pileType = pileType;
  }

  /**
   * Join two Piles together and return result.
   * 
   * @param other
   * @return
   */
  public Pile union(Pile other) {
    List<Card> newList = new ArrayList<>(List.copyOf(cards));
    newList.addAll(other.cards);
    return new Pile(newList, player, this.pileType);
  }

  public boolean remove(Card card) {
    return cards.remove(card);
  }

  public boolean add(Card card) {
    return cards.add(card);
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public Pile draw(int cardAmount) {
    this.shuffle();
    int drawAmount = Math.min(cardAmount, this.cards.size());
    List<Card> drawnCards = this.cards.subList(0, drawAmount);
    this.cards.subList(0, drawAmount).clear();
    return new Pile(drawnCards, this.player, this.pileType);
  }

}