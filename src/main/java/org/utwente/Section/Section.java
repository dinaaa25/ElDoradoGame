package org.utwente.Section;

import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

import java.util.List;

public class Section {
    private final List<Tile> tiles;
    private final SectionType sectionType;

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
}
