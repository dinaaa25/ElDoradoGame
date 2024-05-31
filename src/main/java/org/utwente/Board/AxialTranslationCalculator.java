package org.utwente.Board;

import lombok.Getter;
import org.utwente.Section.SectionType;

import static org.utwente.Board.SectionDirectionType.FlatTopSectionDirection.*;
import static org.utwente.Board.SectionDirectionType.PointyTopSectionDirection.*;

public class AxialTranslationCalculator {

        public record AxialTranslation(int q, int r) {}

    public AxialTranslation getTranslation(SectionType sectionType, SectionDirectionType.SectionDirection sectionDirection, int rotation, int maxQ, int minR, int minQ, int maxR, int placement) {
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

        return new AxialTranslation(translationQ, translationR);
    }
}
