package org.utwente.Tile;

import lombok.Getter;

import java.awt.*;

@Getter
public class TileController {
    private Tile tile;
    private TileView tileView;

    public TileController(Tile tile, TileView tileView) {
        this.tile = tile;
        this.tileView = tileView;
    }

    public void updateView() {
        tileView.repaint();
    }
}
