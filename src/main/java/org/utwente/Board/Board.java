package org.utwente.Board;

import lombok.Getter;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;
import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.player.Player;

import java.util.*;

import static java.util.Map.entry;
import static org.utwente.Board.SectionDirectionType.FlatTopSectionDirection.*;
import static org.utwente.Board.SectionDirectionType.PointyTopSectionDirection.*;

public class Board {
    private final List<Section> sections;
    private final Path path;
    @Getter
    private List<Blockade> blockades;
    @Getter
    private final boolean flatTop;

    public Board(List<Section> sections, Path path, boolean flatTop, List<Blockade> blockades) {
        this.sections = sections;
        this.path = path;
        this.flatTop = flatTop;
        this.blockades = blockades;
    }

    public List<Tile> getStartingTiles() {
        List<Tile> startingTiles = new ArrayList<>();
        List<Tile> allBoardTiles = sections.stream().flatMap(section -> section.getTiles().stream().filter(tile -> tile.isStartingTile())).toList();
        return allBoardTiles;
    }

    public List<Tile> getLastWaitingTiles() {
        List<Tile> lastWaitingTiles = new ArrayList<>();
        List<Tile> allBoardTiles = sections.stream().flatMap(section -> section.getTiles().stream().filter(tile -> tile.isLastWaitingTile())).toList();
        return allBoardTiles;
    }

    public List<Section> getSections() {
        return sections;
    }

    public Path getPath() {
        return path;
    }

    public Tile getElDoradoTile() {
        return sections.stream()
                .flatMap(section -> section.getTiles().stream())
                .filter(tile -> tile.getTileType() == TileType.ElDorado)
                .findFirst()
                .orElse(null);
    }

    public void placePlayer(Tile lastTile, Player player) {
        lastTile.placePlayer(player);
    }

    public static class BoardBuilder {
        @Getter
        private final List<Section> sections;
        private Path path;
        private boolean flatTop;
        private final List<Blockade> blockades;

        public BoardBuilder() {
            this.sections = new ArrayList<>();
            this.availableSections = SectionLoader.loadSections();
            this.blockades = new ArrayList<>();
        }

        private List<Section> availableSections;

        private static Section getSectionBySectionType(SectionType sectionType) {
            List<Section> sectionList = SectionLoader.loadSections();
            Optional<Section> sectionToAdd = sectionList.stream()
                    .filter(section -> section.getSectionType() == sectionType)
                    .findFirst();
            return sectionToAdd.orElseThrow(IllegalArgumentException::new);
        }

        public static final Map<Path, List<SectionWithRotationPositionSectionDirection>> paths = Map.ofEntries(
                entry(Path.HillsOfGold, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.B, 1, 0, PT_NORTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 0, PT_NORTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, -2, 1, PT_NORTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.K, 1, 0, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.J, 1, 1, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.N, -2, 1, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDoradoTwo, 1, 0, FT_EAST)
                )),
                entry(Path.HomeStretch, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.B, 1, 0, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.J, -1, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.Q, 4, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.K, 0, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.M, -2, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDoradoTwo, 0, 0, PT_NORTHEAST)
                )),
                entry(Path.WindingPaths, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.B, 1, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.I, 3, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.F, -3, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, 1, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.N, -2, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, 0, 0, PT_NORTHEAST)
                )),
                entry(Path.Serpentine, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 1, 1, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, -2, 0, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.E, 1, 0, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, 3, 0, PT_NORTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.J, 3, 0, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.M, -1, 0, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDoradoTwo, 2, 0, FT_SOUTHEAST)
                )),
                entry(Path.Swamplands, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.R, 1, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.D, 2, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.H, 2, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.E, 1, 1, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 4, 1, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.K, 0, 1, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, 3, 0, PT_SOUTHWEST)
                )),
                entry(Path.WitchCauldron, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.L, -1, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, -2, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.D, -2, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.M, 0, 1, FT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.I, -1, 0, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, 4, 0, PT_NORTHWEST)
                )),
                entry(Path.TestGame, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, PT_NORTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 4, 1, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 2, 1, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 2, 0, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 2, 0, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 3, 1, PT_SOUTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 3, 0, PT_SOUTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 1, 1, PT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 1, 1, PT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 1, 1, PT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 5, 0, PT_NORTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 2, 1, PT_NORTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 3, 1, PT_NORTH)
                )),
                entry(Path.TestGameFT, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 1, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 2, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 3, 1, FT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 4, 0, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 1, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 1, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 5, 0, FT_NORTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 1, FT_NORTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 1, FT_NORTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.O, 0, 0, FT_NORTHEAST)
                )),
                entry(Path.TestGameElDorado, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, PT_NORTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, 5, 0, FT_NORTHWEST)
                )),
                entry(Path.TestGameElDoradoFT, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, 5, 0, PT_NORTH)
                )),
                entry(Path.BlockadeTest, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, PT_NORTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 0, PT_NORTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.D, 0, 0, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.E, 0, 0, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.F, 0, 0, PT_SOUTH),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, 0, 0, PT_SOUTHWEST)
                )),
                entry(Path.BlockadeTestFT, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 0, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.D, 0, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.E, 0, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.F, 0, 0, FT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, 0, 0, FT_WEST)
                ))
        );

        public BoardBuilder addInitialSection(Section section) {
            if (!sections.isEmpty()) {
                throw new IllegalStateException("Starting section already exists.");
            }
            if (!section.isStartingSection()) {
                throw new IllegalArgumentException("Section is not a starting section");
            }
            sections.add(section);
            return this;
        }

        private void updateBlockadeTiles(Blockade blockade) {
            List<Tile> blockadeTiles = getBlockadeTiles(blockade);
            for (Tile tile : blockadeTiles) {
                tile.setBlockade(blockade);
            }
        }

        private List<Tile> getBlockadeTiles(Blockade blockade) {
            Set<Tile> blockadeTiles = new HashSet<>();
            for (Tile tileSection1 : blockade.getSection1().getTiles()) {
                for (Tile tileSection2 : blockade.getSection2().getTiles()) {
                    if (tileSection1.isNeighbor(tileSection2)) {
                        blockadeTiles.add(tileSection1);
                    }
                }
            }
            return new ArrayList<>(blockadeTiles);
        }

        public void updateAllBlockadeTiles() {
            if (blockades != null) {
                for (Blockade blockade : blockades) {
                    updateBlockadeTiles(blockade);
                }
            }
        }

        public BoardBuilder addSection(Section section) {
            if (section.isStartingSection()) {
                throw new IllegalArgumentException("Section is starting section");
            }
            sections.add(section);
            return this;
        }

        public BoardBuilder addFinalSection(Section section) {
            if (!section.isEndingSection()) {
                throw new IllegalArgumentException("Section is not a ending section");
            }
            sections.add(section);
            return this;
        }

        public BoardBuilder selectPath(Path path) {
            if (path == null) {
                throw new IllegalArgumentException("Path is null");
            }
            this.path = path;
            return this;
        }

        public BoardBuilder addBlockades() {
            if (sections.isEmpty()) {
                throw new IllegalArgumentException("Sections are empty");
            }
            if (sections.size() < 2) {
                throw new IllegalStateException("Not at least 2 sections in BoardBuilder");
            }
            List<Blockade> blockades = getBlockadesList();
            Collections.shuffle(blockades);
            List<Section> nonElDoradoSections = sections.stream()
                    .filter(section -> section.getSectionType() != SectionType.ElDorado && section.getSectionType() != SectionType.ElDoradoTwo)
                    .toList();
            int numberOfBlockades = nonElDoradoSections.size() - 1;


            List<Blockade> selectedBlockades = blockades.subList(0, Math.min(blockades.size() - 1, numberOfBlockades));
            int count = 0;
            for (Blockade blockade : selectedBlockades) {
                if (count == numberOfBlockades) {
                    break;
                }
                blockade.initialize(nonElDoradoSections.get(count), nonElDoradoSections.get(count + 1));
                count++;
            }
            this.blockades.addAll(selectedBlockades);
            updateAllBlockadeTiles();
            return this;
        }

        private static List<Blockade> getBlockadesList() {
            Blockade blockade1 = new Blockade(TileType.Machete, 1, 1);
            Blockade blockade2 = new Blockade(TileType.Coin, 1, 2);
            Blockade blockade3 = new Blockade(TileType.Discard, 1, 3);
            Blockade blockade4 = new Blockade(TileType.Paddle, 1, 4);
            Blockade blockade5 = new Blockade(TileType.Machete, 2, 5);
            Blockade blockade6 = new Blockade(TileType.Discard, 2, 6);

            List<Blockade> blockades = new ArrayList<>();
            blockades.add(blockade1);
            blockades.add(blockade2);
            blockades.add(blockade3);
            blockades.add(blockade4);
            blockades.add(blockade5);
            blockades.add(blockade6);
            return blockades;
        }

        public BoardBuilder buildPath() {
            assert path != null : "Path is null";
            List<SectionWithRotationPositionSectionDirection> sectionWithRotationPositionSectionDirectionList = paths.get(path);
            flatTop = sectionWithRotationPositionSectionDirectionList.stream()
                    .findFirst()
                    .map(section -> section.getSectionDirection() instanceof SectionDirectionType.FlatTopSectionDirection)
                    .orElse(false);
            SectionWithRotationPositionSectionDirection lastSection = null;
            for (SectionWithRotationPositionSectionDirection sectionWithRotationPositionSectionDirection : sectionWithRotationPositionSectionDirectionList) {
                if (lastSection != null && lastSection.getSectionType() == SectionType.O && (sectionWithRotationPositionSectionDirection.getSectionDirection() == PT_SOUTHEAST || sectionWithRotationPositionSectionDirection.getSectionDirection() == PT_NORTHWEST || sectionWithRotationPositionSectionDirection.getSectionDirection() == FT_SOUTHEAST || sectionWithRotationPositionSectionDirection.getSectionDirection() == FT_NORTHWEST)) {
                    sectionWithRotationPositionSectionDirection.setPlacement(-1);
                }
                attachBoardSection(sectionWithRotationPositionSectionDirection);
                lastSection = sectionWithRotationPositionSectionDirection;

            }
            return this;
        }

        private void attachBoardSection(SectionWithRotationPositionSectionDirection sectionWithData) {
            List<Section> availableSections = SectionLoader.loadSections();
            Optional<Section> optionalSection = availableSections.stream()
                    .filter(s -> s.getSectionType() == sectionWithData.getSectionType())
                    .findFirst();
            if (optionalSection.isEmpty()) {
                throw new IllegalArgumentException("This section Type does not exist");
            }
            Section section = optionalSection.get();
            section.setDirectionType(sectionWithData.getSectionDirection());
            rotateSection(section, sectionWithData);

            if (sections.isEmpty()) {
                sections.add(section);
            } else {
                Section lastSection = sections.get(sections.size() - 1);
                int minQ = lastSection.getTiles().stream().mapToInt(Tile::getQ).min().orElse(0);
                int maxQ = lastSection.getTiles().stream().mapToInt(Tile::getQ).max().orElse(0);
                int minR = lastSection.getTiles().stream().mapToInt(Tile::getR).min().orElse(0);
                int maxR = lastSection.getTiles().stream().mapToInt(Tile::getR).max().orElse(0);

                AxialTranslationCalculator.AxialTranslation axialTranslation = new AxialTranslationCalculator().getTranslation(sectionWithData, maxQ, minR, minQ, maxR);

                for (Tile tile : section.getTiles()) {
                    tile.translate(axialTranslation);
                }
                sections.add(section);
            }
        }

        private void rotateSection(Section section, SectionWithRotationPositionSectionDirection sectionWithData) {
            for (Tile tile : section.getTiles()) {
                tile.rotate(sectionWithData.getRotation());
            }
        }

        public Board build() {
            assert path != null : "Path is null";
            assert !sections.isEmpty() : "No sections found";
            assert !blockades.isEmpty() : "No blockades found";
            return new Board(sections, path, flatTop, blockades);
        }
    }
}
