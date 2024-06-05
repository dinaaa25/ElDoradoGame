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
    List<Card> drawnCards = new ArrayList<>();
    this.shuffle();

    for (int i = 0; i < cardAmount; i++) {
      drawnCards.add(this.cards.get(i));
      this.cards.remove(i);
    }

    return new Pile(drawnCards, this.player, this.pileType);
  }

}