package org.utwente.Section;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                    List<Tile> sectionTilesA = section.getTiles();
                    assertEquals(37, sectionTilesA.size(), "Section B should have 37 tiles");
                    List<Tile> macheteTilesA = sectionTilesA.stream()
                            .filter(tile -> tile.getTileType() == TileType.Machete)
                            .toList();
                    assertEquals(22, macheteTilesA.size(), "Section A should have 22 Machete tiles");
                    List<Tile> macheteCoinsPower1A = macheteTilesA.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(22, macheteCoinsPower1A.size(), "Section A should have 22 Machete Power 1 tiles");
                    List<Tile> paddleTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.Paddle).toList();
                    assertEquals(3, paddleTilesA.size(), "Section A should have 3 Paddle tiles");
                    List<Tile> paddleTilesPower1A = paddleTilesA.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(3, paddleTilesPower1A.size(), "Section A should have 3 Paddle Power 1 tiles");
                    List<Tile> coinTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.Coin).toList();
                    assertEquals(5, coinTilesA.size(), "Section A should have 5 Coins tiles");
                    List<Tile> basecampTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.Basecamp).toList();
                    assertEquals(1, basecampTilesA.size(), "Section A should have 1 Basecamp tiles");
                    List<Tile> discardTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.Discard).toList();
                    assertEquals(0, discardTilesA.size(), "Section A should have 0 Discard tiles");
                    List<Tile> mountainTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.Mountain).toList();
                    assertEquals(1, mountainTilesA.size(), "Section A should have 1 Mountain tiles");
                    List<Tile> caveTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.Cave).toList();
                    assertEquals(1, caveTilesA.size(), "Section A should have 1 Cave tiles");
                    List<Tile> elDoradoTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.ElDorado).toList();
                    assertEquals(0, elDoradoTilesA.size(), "Section A should have 0 ElDorado tiles");
                    List<Tile> startTilesA = sectionTilesA.stream().filter(tile -> tile.getTileType() == TileType.Start).toList();
                    assertEquals(4, startTilesA.size(), "Section A should have 4 Start tiles");
                    break;
                case B:
                    List<Tile> sectionTilesB = section.getTiles();
                    assertEquals(37, sectionTilesB.size(), "Section B should have 37 tiles");
                    List<Tile> macheteTilesB = sectionTilesB.stream()
                            .filter(tile -> tile.getTileType() == TileType.Machete)
                            .toList();
                    assertEquals(23, macheteTilesB.size(), "Section B should have 23 Machete tiles");
                    List<Tile> macheteCoinsPower1B = macheteTilesB.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(23, macheteCoinsPower1B.size(), "Section B should have 23 Machete Power 1 tiles");
                    List<Tile> paddleTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.Paddle).toList();
                    assertEquals(4, paddleTilesB.size(), "Section B should have 4 Paddle tiles");
                    List<Tile> paddleTilesPower1B = paddleTilesB.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(4, paddleTilesPower1B.size(), "Section B should have 4 Paddle Power 1 tiles");
                    List<Tile> coinTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.Coin).toList();
                    assertEquals(4, coinTilesB.size(), "Section B should have 4 Coins tiles");
                    List<Tile> basecampTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.Basecamp).toList();
                    assertEquals(1, basecampTilesB.size(), "Section B should have 1 Basecamp tiles");
                    List<Tile> discardTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.Discard).toList();
                    assertEquals(0, discardTilesB.size(), "Section B should have 0 Discard tiles");
                    List<Tile> mountainTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.Mountain).toList();
                    assertEquals(0, mountainTilesB.size(), "Section B should have 0 Mountain tiles");
                    List<Tile> caveTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.Cave).toList();
                    assertEquals(1, caveTilesB.size(), "Section B should have 1 Cave tiles");
                    List<Tile> elDoradoTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.ElDorado).toList();
                    assertEquals(0, elDoradoTilesB.size(), "Section B should have 0 ElDorado tiles");
                    List<Tile> startTilesB = sectionTilesB.stream().filter(tile -> tile.getTileType() == TileType.Start).toList();
                    assertEquals(4, startTilesB.size(), "Section B should have 4 Start tiles");
                    break;
                case C:
                    List<Tile> sectionTilesC = section.getTiles();
                    assertEquals(37, sectionTilesC.size(), "Section C should have 37 tiles");
                    List<Tile> macheteTilesC = sectionTilesC.stream()
                            .filter(tile -> tile.getTileType() == TileType.Machete)
                            .toList();
                    assertEquals(6, macheteTilesC.size(), "Section C should have 6 Machete tiles");
                    List<Tile> macheteCoinsPower1C = macheteTilesC.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(6, macheteCoinsPower1C.size(), "Section C should have 6 Machete Power 1 tiles");
                    List<Tile> paddleTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.Paddle).toList();
                    assertEquals(12, paddleTilesC.size(), "Section C should have 12 Paddle tiles");
                    List<Tile> paddleTilesPower1C = paddleTilesC.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(12, paddleTilesPower1C.size(), "Section C should have 12 Paddle Power 1 tiles");
                    List<Tile> coinTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.Coin).toList();
                    assertEquals(9, coinTilesC.size(), "Section C should have 9 Coins tiles");
                    List<Tile> basecampTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.Basecamp).toList();
                    assertEquals(0, basecampTilesC.size(), "Section C should have 0 Basecamp tiles");
                    List<Tile> discardTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.Discard).toList();
                    assertEquals(9, discardTilesC.size(), "Section C should have 9 Discard tiles");
                    List<Tile> discardTilesPower1C = discardTilesC.stream().filter(tile -> tile.getPower() == 1).toList();
                    assertEquals(9, discardTilesPower1C.size(), "Section C should have 9 Discard Power 1 tiles");
                    List<Tile> mountainTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.Mountain).toList();
                    assertEquals(0, mountainTilesC.size(), "Section C should have 0 Mountain tiles");
                    List<Tile> caveTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.Cave).toList();
                    assertEquals(1, caveTilesC.size(), "Section C should have 1 Cave tiles");
                    List<Tile> elDoradoTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.ElDorado).toList();
                    assertEquals(0, elDoradoTilesC.size(), "Section C should have 0 ElDorado tiles");
                    List<Tile> startTilesC = sectionTilesC.stream().filter(tile -> tile.getTileType() == TileType.Start).toList();
                    assertEquals(0, startTilesC.size(), "Section C should have 0 Start tiles");
                    break;
                case ElDorado:
                    List<Tile> sectionTilesElDorado = section.getTiles();
                    assertEquals(4, sectionTilesElDorado.size(), "Section ElDorado should have 4 tiles");
                    List<Tile> paddleTilesElDorado = sectionTilesElDorado.stream()
                            .filter(tile -> tile.getTileType() == TileType.Paddle)
                            .toList();
                    assertEquals(3, paddleTilesElDorado.size(), "Section ElDorado should have 3 Paddle tiles");
                    List<Tile> elDoradoTilesElDorado = sectionTilesElDorado.stream().filter(tile -> tile.getTileType() == TileType.ElDorado).toList();
                    assertEquals(1, elDoradoTilesElDorado.size(), "Section ElDorado should have 1 ElDorado tile");
                    break;

            }
        }
    }

    @Test
    void testAllSectionsSpecified() {
        for (SectionType sectionType : SectionType.values()) {
            boolean containsType = sections.stream()
                    .anyMatch(section -> section.getSectionType() == sectionType);
            assertTrue(containsType, "List of all sections must contain SectionType: " + sectionType);
        }
    }
}
