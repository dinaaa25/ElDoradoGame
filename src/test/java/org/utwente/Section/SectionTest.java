package org.utwente.Section;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectionTest {
    List<Section> sections;

    @BeforeEach
    void init() {
        sections = SectionLoader.loadSections();
    }


    @Test
    void testTileDistributionOfSections() {
        for(Section section : sections) {
            switch(section.getSectionType()) {
                case A:
                    List<Tile> sectionTiles = section.getTiles();
                    assertEquals(37, sectionTiles.size(), "Section A should have 36 tiles");
                    List<Tile> macheteTiles = sectionTiles.stream()
                            .filter(tile -> tile.getTileType() == TileType.Machete)
                            .toList();
                    assertEquals(22, macheteTiles.size(), "Section A should have 22 Machete tiles");
                    List<Tile> macheteCoinsPower1 = macheteTiles.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(22, macheteCoinsPower1.size(), "Section A should have 22 Machete Power 1 tiles");
                    List<Tile> paddleTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.Paddle).toList();
                    assertEquals(3, paddleTiles.size(), "Section A should have 3 Paddle tiles");
                    List<Tile> paddleTilesPower1 = paddleTiles.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(3, paddleTilesPower1.size(), "Section A should have 3 Paddle Power 1 tiles");
                    List<Tile> coinTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.Coin).toList();
                    assertEquals(5, coinTiles.size(), "Section A should have 5 Coins tiles");
                    List<Tile> basecampTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.Basecamp).toList();
                    assertEquals(1, basecampTiles.size(), "Section A should have 1 Basecamp tiles");
                    List<Tile> discardTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.Discard).toList();
                    assertEquals(0, discardTiles.size(), "Section A should have 0 Discard tiles");
                    List<Tile> mountainTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.Mountain).toList();
                    assertEquals(1, mountainTiles.size(), "Section A should have 1 Mountain tiles");
                    List<Tile> caveTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.Cave).toList();
                    assertEquals(1, caveTiles.size(), "Section A should have 1 Cave tiles");
                    List<Tile> elDoradoTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.ElDorado).toList();
                    assertEquals(0, elDoradoTiles.size(), "Section A should have 0 ElDorado tiles");
                    List<Tile> startTiles = sectionTiles.stream().filter(tile -> tile.getTileType() == TileType.Start).toList();
                    assertEquals(4, startTiles.size(), "Section A should have 4 Start tiles");
                    break;
                case B:

                    break;
            }
        }
    }
}
