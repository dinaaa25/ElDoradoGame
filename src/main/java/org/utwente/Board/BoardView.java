package org.utwente.Board;

import org.utwente.Section.Section;
import org.utwente.Section.SectionController;
import org.utwente.Section.SectionView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class BoardView {
    public void drawBoard(Graphics2D g2d, Board board, int offsetX, int offsetY, int hexSize, boolean flatTop, BufferedImage image) {
        List<Section> sections = board.getSections();
        for (Section section : sections) {
            SectionController sectionController = new SectionController(section, new SectionView());
            sectionController.updateView(g2d, offsetX, offsetY, hexSize, flatTop, image);
        }
    }
}