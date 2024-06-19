package org.utwente.Section;

import org.utwente.Tile.TileImageLoader;

import java.awt.*;

public class SectionController {
    private final Section section;
    private final SectionView sectionView;

    public SectionController(Section section, SectionView sectionView) {
        this.section = section;
        this.sectionView = sectionView;
    }

    public Section getSection() {
        return section;
    }

    public SectionView getSectionView() {
        return sectionView;
    }

    public void updateView(boolean flatTop, TileImageLoader tileImageLoader) {
        sectionView.drawSection(section, flatTop, tileImageLoader);
    }
}