package org.utwente.Board;

import lombok.Getter;
import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;
import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;
import static org.utwente.Board.SectionDirectionType.FlatTopSectionDirection.*;
import static org.utwente.Board.SectionDirectionType.PointyTopSectionDirection.*;

public class Board {
    private final List<Section> sections;
    private final Path path;
    @Getter
    private final boolean flatTop;

    public Board(List<Section> sections, Path path, boolean flatTop) {
        this.sections = sections;
        this.path = path;
        this.flatTop = flatTop;
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
        private final List<Section> sections;
        private Path path;
        private boolean flatTop;

        public BoardBuilder() {
            this.sections = new ArrayList<>();
            this.availableSections = SectionLoader.loadSections();
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
                        new SectionWithRotationPositionSectionDirection(SectionType.K, 1, 1, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.J, 1, 0, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.N, -2, 0, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDoradoTwo, -2, 0, PT_NORTHEAST)
                )),
                entry(Path.HomeStretch, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.B, 1, 0, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.J, -1, 1, FT_NORTHEAST),
//                        new SectionWithRotationPositionSectionDirection(SectionType.Q, -2, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.K, 0, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.M, -2, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDoradoTwo, 1, 1, PT_NORTHEAST)
                )),
                entry(Path.WindingPaths, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.B, 1, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.I, 3, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.F, -3, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, 1, 1, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.N, -2, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, -2, 0, FT_NORTHEAST)
                )),
                entry(Path.Serpentine, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 1, 1, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, -2, 1, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.E, 1, 0, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, 3, 0, PT_NORTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.J, 3, 0, PT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.M, -1, 1, PT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDoradoTwo, 0, 0, PT_SOUTHEAST)
                )),
                entry(Path.Swamplands, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.D, 2, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.H, 2, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.E, 1, 0, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.K, 0, 0, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, 0, 0, FT_SOUTHWEST)
                )),
                entry(Path.WitchCauldron, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.A, 0, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.L, -1, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, -2, 1, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.D, -2, 1, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.M, 0, 1, FT_SOUTHWEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.I, -1, 0, FT_WEST),
                        new SectionWithRotationPositionSectionDirection(SectionType.ElDorado, 0, 0, FT_NORTHWEST)
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
            return this.buildPath();
        }

        public BoardBuilder buildPath() {
            assert path != null : "Path is null";
            List<SectionWithRotationPositionSectionDirection> sectionWithRotationPositionSectionDirectionList = paths.get(path);
            flatTop = sectionWithRotationPositionSectionDirectionList.stream()
                    .findFirst()
                    .map(section -> section.getSectionDirection() instanceof SectionDirectionType.FlatTopSectionDirection)
                    .orElse(false);
            for (SectionWithRotationPositionSectionDirection sectionWithRotationPositionSectionDirection : sectionWithRotationPositionSectionDirectionList) {
                attachBoardSection(sectionWithRotationPositionSectionDirection.getSectionType(), sectionWithRotationPositionSectionDirection.getSectionDirection(), sectionWithRotationPositionSectionDirection.getPlacement(), sectionWithRotationPositionSectionDirection.getRotation());
            }
            return this;
        }

        private void attachBoardSection(SectionType sectionType, SectionDirectionType.SectionDirection sectionDirection, int placement, int rotation) {
            List<Section> availableSections = SectionLoader.loadSections();
            Optional<Section> optionalSection = availableSections.stream()
                    .filter(s -> s.getSectionType() == sectionType)
                    .findFirst();
            if (optionalSection.isEmpty()) {
                throw new IllegalArgumentException("This section Type does not exist");
            }
            Section section = optionalSection.get();
            for (Tile tile : section.getTiles()) {
                tile.rotate(rotation);
            }

            if (sections.isEmpty()) {
                sections.add(section);
            } else {
                Section lastSection = sections.get(sections.size() - 1);
                int minQ = lastSection.getTiles().stream().mapToInt(Tile::getQ).min().orElse(0);
                int maxQ = lastSection.getTiles().stream().mapToInt(Tile::getQ).max().orElse(0);
                int minR = lastSection.getTiles().stream().mapToInt(Tile::getR).min().orElse(0);
                int maxR = lastSection.getTiles().stream().mapToInt(Tile::getR).max().orElse(0);

                int translationQ;
                int translationR;

                if (sectionType == SectionType.O) {
                    if (sectionDirection.equals(PT_NORTHEAST)) {
                        if (rotation == 0) {
                            translationQ = maxQ;
                            translationR = minR - 2;
                        } else if (rotation == 1) {
                            translationQ = maxQ + 2;
                            translationR = minR + 1;
                        } else {
                            translationQ = maxQ;
                            translationR = minR;
                        }
                    } else if (sectionDirection.equals(PT_NORTH)) {
                        translationQ = maxQ;
                        translationR = minR - 2;
                    } else {
                        translationQ = maxQ - minQ;
                        translationR = maxR - minR;
                    }
                } else {
                    if (sectionDirection.equals(PT_NORTHEAST) || sectionDirection.equals(FT_EAST)) {
                        translationQ = maxQ + 1 + 3;
                        translationR = minR - 1 + placement;
                    } else if (sectionDirection.equals(PT_SOUTHEAST) || sectionDirection.equals(FT_SOUTHEAST)) {
                        translationQ = maxQ + placement;
                        translationR = maxR + 1 - placement;
                    } else if (sectionDirection.equals(PT_SOUTH) || sectionDirection.equals(FT_SOUTHWEST)) {
                        translationQ = minQ - placement;
                        translationR = maxR + 3 + 1;
                    } else if (sectionDirection.equals(PT_SOUTHWEST) || sectionDirection.equals(FT_WEST)) {
                        translationQ = minQ - 3 - 1;
                        translationR = maxR + 1 - placement;
                    } else if (sectionDirection.equals(PT_NORTHWEST) || sectionDirection.equals(FT_NORTHWEST)) {
                        translationQ = minQ - placement;
                        translationR = minR - 1 + placement;
                    } else if (sectionDirection.equals(PT_NORTH) || sectionDirection.equals(FT_NORTHEAST)) {
                        translationQ = maxQ + placement;
                        translationR = minR - 3 - 1;
                    } else {
                        translationQ = maxQ;
                        translationR = minR;
                    }
                }
                for (Tile tile : section.getTiles()) {
                    tile.setQ(tile.getQ() + translationQ);
                    tile.setR(tile.getR() + translationR);
                }
                sections.add(section);
            }
        }

        public List<Section> getSections() {
            return sections;
        }

        public Board build() {
            assert path != null : "Path is null";
            assert !sections.isEmpty() : "No sections found";
            return new Board(sections, path, flatTop);
        }
    }
}
