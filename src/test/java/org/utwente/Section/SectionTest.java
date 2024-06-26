package org.utwente.Section;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.utwente.Board.CoordinateBounds;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

class SectionTest {
    List<Section> sections;
    /**
     * Method under test: {@link Section#getCoordinateBounds()}
     */
    @Test
    void testGetCoordinateBounds() {
        // Arrange and Act
        CoordinateBounds actualCoordinateBounds = (new Section(new ArrayList<>(), SectionType.A)).getCoordinateBounds();

        // Assert
        assertEquals(0, actualCoordinateBounds.maxQ());
        assertEquals(0, actualCoordinateBounds.maxR());
        assertEquals(0, actualCoordinateBounds.minQ());
        assertEquals(0, actualCoordinateBounds.minR());
    }

    /**
     * Method under test: {@link Section#isStartingSection()}
     */
    @Test
    void testIsStartingSection() {
        // Arrange, Act and Assert
        assertFalse((new Section(new ArrayList<>(), SectionType.A)).isStartingSection());
    }

    /**
     * Method under test: {@link Section#isStartingSection()}
     */
    @Test
    void testIsStartingSection2() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(6, 6, TileType.Start, 6, true));

        // Act and Assert
        assertTrue((new Section(tiles, SectionType.A)).isStartingSection());
    }

    /**
     * Method under test: {@link Section#isEndingSection()}
     */
    @Test
    void testIsEndingSection() {
        // Arrange, Act and Assert
        assertFalse((new Section(new ArrayList<>(), SectionType.A)).isEndingSection());
    }

    /**
     * Method under test: {@link Section#isEndingSection()}
     */
    @Test
    void testIsEndingSection2() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(6, 6, TileType.ElDorado, 6, true));

        // Act and Assert
        assertTrue((new Section(tiles, SectionType.A)).isEndingSection());
    }

    /**
     * Method under test: {@link Section#getMaxQ()}
     */
    @Test
    void testGetMaxQ() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> (new Section(new ArrayList<>(), SectionType.A)).getMaxQ());
    }

    /**
     * Method under test: {@link Section#getMaxQ()}
     */
    @Test
    void testGetMaxQ2() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));

        // Act and Assert
        assertEquals(1, (new Section(tiles, SectionType.A)).getMaxQ());
    }

    /**
     * Method under test: {@link Section#getMinQ()}
     */
    @Test
    void testGetMinQ() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> (new Section(new ArrayList<>(), SectionType.A)).getMinQ());
    }

    /**
     * Method under test: {@link Section#getMinQ()}
     */
    @Test
    void testGetMinQ2() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));

        // Act and Assert
        assertEquals(1, (new Section(tiles, SectionType.A)).getMinQ());
    }

    /**
     * Method under test: {@link Section#getMinR()}
     */
    @Test
    void testGetMinR() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> (new Section(new ArrayList<>(), SectionType.A)).getMinR());
    }

    /**
     * Method under test: {@link Section#getMinR()}
     */
    @Test
    void testGetMinR2() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));

        // Act and Assert
        assertEquals(1, (new Section(tiles, SectionType.A)).getMinR());
    }

    /**
     * Method under test: {@link Section#getMaxR()}
     */
    @Test
    void testGetMaxR() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> (new Section(new ArrayList<>(), SectionType.A)).getMaxR());
    }

    /**
     * Method under test: {@link Section#getMaxR()}
     */
    @Test
    void testGetMaxR2() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));

        // Act and Assert
        assertEquals(1, (new Section(tiles, SectionType.A)).getMaxR());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Section#Section(List, SectionType)}
     *   <li>{@link Section#toString()}
     *   <li>{@link Section#getSectionType()}
     *   <li>{@link Section#getTiles()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();

        // Act
        Section actualSection = new Section(tiles, SectionType.A);
        String actualToStringResult = actualSection.toString();
        SectionType actualSectionType = actualSection.getSectionType();

        // Assert
        assertEquals("Section{tiles=[], sectionType=A}", actualToStringResult);
        assertEquals(SectionType.A, actualSectionType);
        assertSame(tiles, actualSection.getTiles());
    }


        private static final Map<SectionType, Map<TileType, Map<Integer, Integer>>> expectedTileCounts = Map.ofEntries(
                entry(SectionType.A, Map.of(
                        TileType.Machete, Map.of(1, 22),
                        TileType.Paddle, Map.of(1, 3),
                        TileType.Coin, Map.of(1, 5),
                        TileType.Basecamp, Map.of(1, 1),
                        TileType.Discard, Map.of(),
                        TileType.Mountain, Map.of(0, 1),
                        TileType.Cave, Map.of(0, 1),
                        TileType.ElDorado, Map.of(),
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
                        TileType.ElDorado, Map.of(0, 3),
                        TileType.Start, Map.of()
                )),
                entry(SectionType.ElDoradoTwo, Map.of(
                        TileType.Machete, Map.of(1, 3),
                        TileType.Paddle, Map.of(),
                        TileType.Coin, Map.of(),
                        TileType.Basecamp, Map.of(),
                        TileType.Discard, Map.of(),
                        TileType.Mountain, Map.of(),
                        TileType.Cave, Map.of(),
                        TileType.ElDorado, Map.of(0, 3),
                        TileType.Start, Map.of()
                ))
        );

        @BeforeEach
        void init() {
            sections = SectionLoader.loadSections();
        }

        private static Stream<SectionType> sectionTypes() {
            return expectedTileCounts.keySet().stream();
        }

        @ParameterizedTest
        @EnumSource(SectionType.class)
        void testTotalTileCount(SectionType sectionType) {
            Section section = sections.stream()
                    .filter(s -> s.getSectionType() == sectionType)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Section " + sectionType + " not found"));

            List<Tile> sectionTiles = section.getTiles();
            Map<TileType, Map<Integer, Integer>> expectedCounts = expectedTileCounts.get(sectionType);

            int expectedTotalTiles = expectedCounts.values().stream()
                    .flatMap(map -> map.values().stream())
                    .mapToInt(Integer::intValue)
                    .sum();
            assertEquals(expectedTotalTiles, sectionTiles.size(), "Section " + sectionType + " should have " + expectedTotalTiles + " tiles");
        }

        @ParameterizedTest
        @EnumSource(SectionType.class)
        void testTileDistributionOfSections(SectionType sectionType) {
            Section section = sections.stream()
                    .filter(s -> s.getSectionType() == sectionType)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Section " + sectionType + " not found"));

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

                // Unspecified power levels (0 to 4), if not specified the tiles should not exist at all.
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

        @ParameterizedTest
        @EnumSource(SectionType.class)
        void testAllSectionsSpecified(SectionType sectionType) {
            boolean containsType = sections.stream()
                    .anyMatch(section -> section.getSectionType() == sectionType);
            assertTrue(containsType, "List of all sections must contain SectionType: " + sectionType);
        }
    }

