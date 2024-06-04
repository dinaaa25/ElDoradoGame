package org.utwente.market.view.gui;

public class GridCoordinate {
  public final int columns = 4;
  public int y;
  public int x;

  public GridCoordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void nextRow() {
    this.y += 1;
    this.x = 0;
  }

  public void nextColumn() {
    if (x == columns - 1) {
      this.nextRow();
    } else {
      this.x += 1;
    }
  }

  @Override
  public String toString() {
    return String.format("Coordinate[x=%d,y=%d]", x, y);
  }
}
