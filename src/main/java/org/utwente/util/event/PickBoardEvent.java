package org.utwente.util.event;

import org.utwente.Board.Path;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickBoardEvent extends Event {
  Path path;

  public PickBoardEvent(Path path) {
    this.path = path;
  }

  @Override
  public String toString() {
    return "PickBoardEvent{" +
            "path=" + path +
            '}';
  }
}
