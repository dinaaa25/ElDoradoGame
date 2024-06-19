package org.utwente.Section;

import org.utwente.Tile.Tile;
import org.utwente.Tile.TileController;
import org.utwente.Tile.TileImageLoader;
import org.utwente.Tile.TileView;

import java.util.List;

public class SectionView {
    public void drawSection(Section section, boolean flatTop, TileImageLoader tileImageLoader) {
        List<Tile> tiles = section.getTiles();
        for (Tile tile : tiles) {
            TileController tileController = new TileController(tile, new TileView(tile, flatTop, tileImageLoader));
            tileController.updateView();
        }
    }


}