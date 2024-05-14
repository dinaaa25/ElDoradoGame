package org.utwente.Section;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SectionTest2 {
    List<Section> sections;

    private static final Map<SectionType, Map<TileType, Map<Integer, Integer>>> expectedTileCounts = Map.ofEntries(
            entry(SectionType.A, Map.of(
                    TileType.Machete, Map.of(1, 22),
                    TileType.Paddle, Map.of(1, 3),
                    TileType.Coin, Map.of(1, 5),
                    TileType.Basecamp, Map.of(1, 1),
                    TileType.Discard, Map.of(1, 0),
                    TileType.Mountain, Map.of(0, 1),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(1, 0),
                    TileType.Start, Map.of(0, 4)
            )),
            entry(SectionType.B, Map.of(
                    TileType.Machete, Map.of(1, 23),
                    TileType.Paddle, Map.of(1, 4),
                    TileType.Coin, Map.of(1, 4),
                    TileType.Basecamp, Map.of(1, 1),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of(0, 4)
            )),
            entry(SectionType.C, Map.of(
                    TileType.Machete, Map.of(1, 6),
                    TileType.Paddle, Map.of(1, 12),
                    TileType.Coin, Map.of(1, 9),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(1, 9),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.D, Map.of(
                    TileType.Machete, Map.of(1, 15, 2, 4),
                    TileType.Paddle, Map.of(1, 8, 2, 2, 3, 1),
                    TileType.Coin, Map.of(1, 1, 3, 2),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(0, 3),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.E, Map.of(
                    TileType.Machete, Map.of(1, 10, 2, 4, 3, 1),
                    TileType.Paddle, Map.of(1, 3, 2, 1),
                    TileType.Coin, Map.of(1, 4),
                    TileType.Basecamp, Map.of(1, 1),
                    TileType.Discard, Map.of(1, 7, 3, 1),
                    TileType.Mountain, Map.of(0, 4),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.F, Map.of(
                    TileType.Machete, Map.of(1, 8, 2, 3, 3, 1),
                    TileType.Paddle, Map.of(1, 4, 2, 3, 3, 1),
                    TileType.Coin, Map.of(1, 2, 2, 1),
                    TileType.Basecamp, Map.of(1, 1, 2, 1),
                    TileType.Discard, Map.of(1, 7, 2, 1),
                    TileType.Mountain, Map.of(0, 3),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.G, Map.of(
                    TileType.Machete, Map.of(1, 14, 2, 3),
                    TileType.Paddle, Map.of(),
                    TileType.Coin, Map.of(1, 6, 2, 5, 3, 1, 4, 1),
                    TileType.Basecamp, Map.of(1, 1),
                    TileType.Discard, Map.of(1, 2),
                    TileType.Mountain, Map.of(0, 3),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.H, Map.of(
                    TileType.Machete, Map.of(1, 7, 2, 5),
                    TileType.Paddle, Map.of(1, 5, 2, 5),
                    TileType.Coin, Map.of(1, 7, 2, 6, 3, 1),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.I, Map.of(
                    TileType.Machete, Map.of(1, 14, 2, 3),
                    TileType.Paddle, Map.of(1, 3, 2, 3),
                    TileType.Coin, Map.of(1, 3, 2, 3),
                    TileType.Basecamp, Map.of(3, 1),
                    TileType.Discard, Map.of(3, 1),
                    TileType.Mountain, Map.of(0, 5),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.J, Map.of(
                    TileType.Machete, Map.of(1, 4, 2, 2),
                    TileType.Paddle, Map.of(1, 7, 2, 2),
                    TileType.Coin, Map.of(1, 7, 2, 3),
                    TileType.Basecamp, Map.of(1, 1),
                    TileType.Discard, Map.of(1, 4, 2, 5),
                    TileType.Mountain, Map.of(0, 1),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.K, Map.of(
                    TileType.Machete, Map.of(1, 17, 2, 12, 3, 4),
                    TileType.Paddle, Map.of(3, 1),
                    TileType.Coin, Map.of(4, 1),
                    TileType.Basecamp, Map.of(1, 2),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.L, Map.of(
                    TileType.Machete, Map.of(1, 14, 2, 9, 3, 3),
                    TileType.Paddle, Map.of(1, 3),
                    TileType.Coin, Map.of(2, 2),
                    TileType.Basecamp, Map.of(1, 2, 2, 1),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(0, 2),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.M, Map.of(
                    TileType.Machete, Map.of(1, 18),
                    TileType.Paddle, Map.of(1, 3, 4, 1),
                    TileType.Coin, Map.of(2, 1, 4, 1),
                    TileType.Basecamp, Map.of(1, 1),
                    TileType.Discard, Map.of(2, 3),
                    TileType.Mountain, Map.of(0, 8),
                    TileType.Cave, Map.of(0, 1),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.N, Map.of(
                    TileType.Machete, Map.of(1, 16, 2, 2),
                    TileType.Paddle, Map.of(1, 9),
                    TileType.Coin, Map.of(1, 4, 2, 3, 3, 2, 4, 1),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.O, Map.of(
                    TileType.Machete, Map.of(1, 1, 2, 2),
                    TileType.Paddle, Map.of(1, 1, 4, 1),
                    TileType.Coin, Map.of(1, 3, 2, 1),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(1, 3, 2, 1),
                    TileType.Mountain, Map.of(0, 3),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.P, Map.of(
                    TileType.Machete, Map.of(1, 1, 2, 1),
                    TileType.Paddle, Map.of(1, 7, 2, 2, 3, 3),
                    TileType.Coin, Map.of(),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(1, 1, 2, 1),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.Q, Map.of(
                    TileType.Machete, Map.of(1, 3, 2, 3, 3, 1),
                    TileType.Paddle, Map.of(1, 2, 2, 1),
                    TileType.Coin, Map.of(1, 2, 3, 1),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(1, 2, 3, 1),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.R, Map.of(
                    TileType.Machete, Map.of(1, 5, 3, 1),
                    TileType.Paddle, Map.of(),
                    TileType.Coin, Map.of(1, 6),
                    TileType.Basecamp, Map.of(1, 1),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(0, 3),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.ElDorado, Map.of(
                    TileType.Machete, Map.of(),
                    TileType.Paddle, Map.of(1, 3),
                    TileType.Coin, Map.of(),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(1, 1),
                    TileType.Start, Map.of()
            )),
            entry(SectionType.ElDorado2, Map.of(
                    TileType.Machete, Map.of(1, 3),
                    TileType.Paddle, Map.of(),
                    TileType.Coin, Map.of(),
                    TileType.Basecamp, Map.of(),
                    TileType.Discard, Map.of(),
                    TileType.Mountain, Map.of(),
                    TileType.Cave, Map.of(),
                    TileType.ElDorado, Map.of(1, 1),
                    TileType.Start, Map.of()
            ))
    );

    @BeforeEach
    void init() {
        sections = SectionLoader.loadSections();
    }

    @Test
    void testTotalTileCount() {
        for (Section section : sections) {
            SectionType sectionType = section.getSectionType();

            if (!expectedTileCounts.containsKey(sectionType)) {
                continue;
            }

            List<Tile> sectionTiles = section.getTiles();
            Map<TileType, Map<Integer, Integer>> expectedCounts = expectedTileCounts.get(sectionType);

            int expectedTotalTiles = expectedCounts.values().stream()
                    .flatMap(map -> map.values().stream())
                    .mapToInt(Integer::intValue)
                    .sum();

            assertEquals(expectedTotalTiles, sectionTiles.size(), "Section " + sectionType + " should have " + expectedTotalTiles + " tiles");
        }
    }

    @Test
    void testTileDistributionOfSections() {
        for (Section section : sections) {
            SectionType sectionType = section.getSectionType();

            if (!expectedTileCounts.containsKey(sectionType)) {
                continue;
            }

            List<Tile> sectionTiles = section.getTiles();
            Map<TileType, Map<Integer, Integer>> expectedCounts = expectedTileCounts.get(sectionType);

            for (TileType tileType : TileType.values()) {
                Map<Integer, Integer> powerCounts = expectedCounts.getOrDefault(tileType, Map.of());

                // Specified power levels
                for (Map.Entry<Integer, Integer> entry : powerCounts.entrySet()) {
                    int power = entry.getKey();
                    int expectedCount = entry.getValue();
                    long actualCount = sectionTiles.stream()
                            .filter(tile -> tile.getTileType() == tileType && tile.getPower() == power)
                            .count();
                    assertEquals(expectedCount, actualCount, "Section " + sectionType + " should have " + expectedCount + " " + tileType + " tiles with power " + power);
                }

                // Unspecified power levels (0 to 3)
                for (int power = 0; power <= 4; power++) {
                    if (!powerCounts.containsKey(power)) {
                        int finalPower = power;
                        long actualCount = sectionTiles.stream()
                                .filter(tile -> tile.getTileType() == tileType && tile.getPower() == finalPower)
                                .count();
                        assertEquals(0, actualCount, "Section " + sectionType + " should have 0 " + tileType + " tiles with power " + power);
                    }
                }
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