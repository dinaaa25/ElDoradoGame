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

    public void updateView(Graphics2D g2d, int offsetX, int offsetY, boolean flatTop, TileImageLoader tileImageLoader) {
        sectionView.drawSection(g2d, section, offsetX, offsetY, flatTop, tileImageLoader);
    }
}