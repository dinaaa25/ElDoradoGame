package org.utwente.Section;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Board.SectionDirectionType;
import org.utwente.Tile.Tile;

import java.util.List;

public class Section {
    private final List<Tile> tiles;
    private final SectionType sectionType;
    @Getter
    @Setter
    private SectionDirectionType.SectionDirection directionType;

    public Section(List<Tile> tiles, SectionType sectionType) {
        this.tiles = tiles;
        this.sectionType = sectionType;
    }

    @Override
    public String toString() {
        return "Section{" +
                "tiles=" + tiles +
                ", sectionType=" + sectionType +
                '}';
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public boolean isStartingSection() {
        return tiles.stream().anyMatch(Tile::isStartingTile);
    }

    public boolean isEndingSection() {
        return tiles.stream().anyMatch(Tile::isEndTile);
    }

    public int getMaxQ() {
        return tiles.stream()
                .mapToInt(Tile::getQ)
                .max()
                .orElseThrow(() -> new IllegalStateException("Section has no tiles"));
    }

    public int getMinQ() {
        return tiles.stream()
                .mapToInt(Tile::getQ)
                .min()
                .orElseThrow(() -> new IllegalStateException("Section has no tiles"));
    }

    public int getMinR() {
        return tiles.stream()
                .mapToInt(Tile::getR)
                .min()
                .orElseThrow(() -> new IllegalStateException("Section has no tiles"));
    }

    public int getMaxR() {
        return tiles.stream()
                .mapToInt(Tile::getR)
                .max()
                .orElseThrow(() -> new IllegalStateException("Section has no tiles"));
    }


}
