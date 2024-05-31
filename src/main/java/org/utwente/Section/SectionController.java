package org.utwente.Section;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    public void updateView(Graphics2D g2d, int offsetX, int offsetY, boolean flatTop, BufferedImage image) {
        sectionView.drawSection(g2d, section, offsetX, offsetY, flatTop, image);
    }
}