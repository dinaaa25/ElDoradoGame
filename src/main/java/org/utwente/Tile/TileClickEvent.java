package org.utwente.Tile;

import org.utwente.util.event.Event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TileClickEvent extends Event {
  Tile tile;

  public TileClickEvent(Tile tile) {
    this.tile = tile;
  }

  @Override
  public String toString() {
    return "TileClickEvent{" +
            "tile=" + tile +
            '}';
  }
}
