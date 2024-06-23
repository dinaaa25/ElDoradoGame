package org.utwente.player.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.utwente.market.model.Card;
import lombok.Getter;
import lombok.Setter;
import org.utwente.market.model.Resource;

@Getter
@Setter
public class Pile {
  protected List<Resource> resources;
  protected Player player;
  protected PileType pileType;

  public Pile() {
    this.resources = new ArrayList<>();
  }

  public Pile(List<Resource> resources, Player player, PileType pileType) {
    this.resources = resources;
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
    List<Resource> newList = new ArrayList<>(List.copyOf(resources));
    newList.addAll(other.resources);
    return new Pile(newList, player, this.pileType);
  }

  public void addAll(Pile other) {
    this.resources.addAll(other.getResources());
  }

  public boolean remove(Card card) {
    return resources.remove(card);
  }

  public boolean add(Resource card) {
    return resources.add(card);
  }

  public void shuffle() {
    Collections.shuffle(resources);
  }

  public Pile draw(int cardAmount) {
    if (cardAmount > this.resources.size() && pileType == PileType.Draw) {
      Pile discardPile = player.getDiscardPile();
      this.resources.addAll(discardPile.getResources());
    }

    this.shuffle();
    List<Resource> drawnCards = new ArrayList<>();
    int drawAmount = Math.min(cardAmount, this.resources.size());
    for (int i = 0; i < drawAmount; i++) {
      drawnCards.add(this.resources.get(i));
      this.resources.remove(i);
    }
    return new Pile(drawnCards, this.player, this.pileType);
  }

}