package org.utwente.util.event;

import org.utwente.Board.Path;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickBoardEvent extends Event {
  Path path;
  boolean otherBoard;

  public PickBoardEvent(Path path) {
    this(path, false);
  }

  public PickBoardEvent(Path path, boolean otherBoard) {
    this.path = path;
    this.otherBoard = otherBoard;
  }

  @Override
  public String toString() {
    return "PickBoardEvent{" +
            "path=" + path +
            '}';
  }
}
