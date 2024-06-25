package org.utwente.player.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.utwente.util.ShuffleUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pile<T> {
  protected List<T> resources;
  protected Player player;
  protected PileType pileType;

  public Pile() {
    this.resources = new ArrayList<>();
  }

  public Pile(List<T> resources, Player player, PileType pileType) {
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
  public Pile<T> union(Pile<T> other) {
    List<T> newList = new ArrayList<>(List.copyOf(resources));
    newList.addAll(other.resources);
    return new Pile<T>(newList, player, this.pileType);
  }

  public void addAll(Pile<T> other) {
    this.resources.addAll(other.getResources());
  }

  public boolean remove(T card) {
    return resources.remove(card);
  }

  public boolean add(T card) {
    return resources.add(card);
  }

  public void shuffle() {

    ShuffleUtils.shuffle(resources);
  }

  public boolean isEmpty() {
    return this.resources.isEmpty();
  }
}