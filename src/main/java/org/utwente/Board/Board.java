package org.utwente.Board;

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

    public Board(List<Section> sections, Path path) {
        this.sections = sections;
        this.path = path;
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
                        new SectionWithRotationPositionSectionDirection(SectionType.N, -2, 0, PT_NORTHEAST)
                )),
//                entry(Path.HomeStretch, List.of(
//                        SectionType.B,
//                        SectionType.J,
//                        SectionType.Q,
//                        SectionType.K,
//                        SectionType.M,
//                        SectionType.C,
//                        SectionType.ElDoradoTwo
//                )),
                entry(Path.WindingPaths, List.of(
                        new SectionWithRotationPositionSectionDirection(SectionType.B, 1, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.I, 3, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.F, -3, 0, FT_EAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.G, 1, 1, FT_SOUTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.C, 0, 1, FT_NORTHEAST),
                        new SectionWithRotationPositionSectionDirection(SectionType.N, -2, 0, FT_EAST)
                ))
//                entry(Path.Serpentine, List.of(
//                        SectionType.A,
//                        SectionType.C,
//                        SectionType.E,
//                        SectionType.G,
//                        SectionType.J,
//                        SectionType.M,
//                        SectionType.ElDorado
//                )),
//                entry(Path.Swamplands, List.of(
//                        SectionType.A,
//                        SectionType.R,
//                        SectionType.D,
//                        SectionType.H,
//                        SectionType.E,
//                        SectionType.O,
//                        SectionType.ElDorado
//                )),
//                entry(Path.WitchCauldron, List.of(
//                        SectionType.A,
//                        SectionType.L,
//                        SectionType.G,
//                        SectionType.D,
//                        SectionType.M,
//                        SectionType.I,
//                        SectionType.ElDorado
//                ))
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
            List<SectionType> sectionTypes = paths.get(path).stream()
                    .map(SectionWithRotationPositionSectionDirection::getSectionType)
                    .toList();
            sectionTypes.stream()
                    .map(BoardBuilder::getSectionBySectionType)
                    .forEach(sections::add);
            return this;
        }

        public List<Section> getSections() {
            return sections;
        }

        public Board build() {
            assert path != null : "Path is null";
            assert !sections.isEmpty() : "No sections found";
            return new Board(sections, path);
        }
    }
}
