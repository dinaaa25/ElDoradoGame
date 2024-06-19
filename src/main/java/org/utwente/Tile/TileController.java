package org.utwente.Tile;

import lombok.Getter;

@Getter
public class TileController {
    private final Tile tile;
    private final TileView tileView;

    public TileController(Tile tile, TileView tileView) {
        this.tile = tile;
        this.tileView = tileView;
    }

    public void updateView() {
        tileView.repaint();
    }
}
