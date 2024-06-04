package org.utwente.Board;

import jdk.jshell.spi.ExecutionControl;
import lombok.Getter;
import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;

import static org.utwente.Board.SectionDirectionType.FlatTopSectionDirection.*;
import static org.utwente.Board.SectionDirectionType.PointyTopSectionDirection.*;

public class AxialTranslationCalculator {

    public record AxialTranslation(int q, int r) {}

    public AxialTranslation getTranslation(SectionWithRotationPositionSectionDirection sectionWithData, int maxQ, int minR, int minQ, int maxR) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int rotation = sectionWithData.getRotation();
        int placement = sectionWithData.getPlacement();
        SectionType sectionType = sectionWithData.getSectionType();

        Integer translationQ = null;
        Integer translationR = null;

        if (sectionType == SectionType.O) {
            if (sectionDirection.equals(PT_NORTH)) {
                if (rotation == 0 || rotation == 3) {
                    translationQ = maxQ - placement;
                    translationR = minR - 2;
                }
            }
            else if (sectionDirection.equals(PT_NORTHEAST)) {
                if (rotation == 1 || rotation == 4) {
                    translationQ = maxQ + 2;
                    translationR = minR + 1 - placement;
                }
            } else if (sectionDirection.equals(PT_SOUTHEAST)) {
                if (rotation == 2 || rotation == 5) {
                    translationQ = maxQ - 1 + placement;
                    translationR = maxR - placement;
                }
            } else if (sectionDirection.equals(PT_SOUTH)) {
                if (rotation == 0 || rotation == 3) {
                    translationQ = minQ + 1 - placement;
                    translationR = maxR + 2;
                }
            } else if (sectionDirection.equals(PT_SOUTHWEST)) {
                if (rotation == 4 || rotation == 1) {
                    translationQ = minQ - 2;
                    translationR = maxR - 1 + placement;
                }
            } else if (sectionDirection.equals(PT_NORTHWEST)) {
                if (rotation == 5 || rotation == 2) {
                    translationQ = minQ + 1 - placement;
                    translationR = minR + placement;
                }
            }
        } else {
            if (sectionDirection.equals(PT_NORTHEAST) || sectionDirection.equals(FT_EAST)) {
                translationQ = maxQ + 1 + 3;
                translationR = minR - 1 + placement;
            } else if (sectionDirection.equals(PT_SOUTHEAST) || sectionDirection.equals(FT_SOUTHEAST)) {
                translationQ = maxQ + 1 - placement;
                translationR = maxR + placement;
                if (placement == -1) {
                    translationQ -= 2;
                    translationR += 1;
                }
            } else if (sectionDirection.equals(PT_SOUTH) || sectionDirection.equals(FT_SOUTHWEST)) {
                translationQ = minQ - placement;
                translationR = maxR + 3 + 1;
            } else if (sectionDirection.equals(PT_SOUTHWEST) || sectionDirection.equals(FT_WEST)) {
                translationQ = minQ - 3 - 1;
                translationR = maxR + 1 - placement;
            } else if (sectionDirection.equals(PT_NORTHWEST) || sectionDirection.equals(FT_NORTHWEST)) {
                translationQ = minQ - placement;
                translationR = minR - 1 + placement;
                if (placement == -1) {
                    translationQ -= 1;
                    translationR += 2;
                }
            } else if (sectionDirection.equals(PT_NORTH) || sectionDirection.equals(FT_NORTHEAST)) {
                translationQ = maxQ + placement;
                translationR = minR - 3 - 1;
            }
        }
        if (translationQ == null || translationR == null) {
            try {
                throw new ExecutionControl.NotImplementedException(sectionWithData.toString());
            } catch (ExecutionControl.NotImplementedException e) {
                throw new RuntimeException(e);
            }

        }

        return new AxialTranslation(translationQ, translationR);
    }
}
