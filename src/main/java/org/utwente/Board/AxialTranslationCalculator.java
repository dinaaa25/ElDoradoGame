package org.utwente.Board;

import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;

import static org.utwente.Board.SectionDirectionType.FlatTopSectionDirection.*;
import static org.utwente.Board.SectionDirectionType.PointyTopSectionDirection.*;

public class AxialTranslationCalculator {

    public record AxialTranslation(int q, int r) {}

    private AxialTranslation getTranslationSmallOPQR(SectionWithRotationPositionSectionDirection sectionWithData, int maxQ, int minR, int minQ, int maxR) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int rotation = sectionWithData.getRotation();
        int placement = sectionWithData.getPlacement();

        int translationQ = 0;
        int translationR = 0;

        if (sectionDirection.equals(PT_NORTH) || sectionDirection.equals(FT_NORTHEAST)) {
            if (rotation == 0 || rotation == 3) {
                translationQ = maxQ - placement;
                translationR = minR - 2;
            }
        }
        else if (sectionDirection.equals(PT_NORTHEAST) || sectionDirection.equals(FT_EAST)) {
            if (rotation == 1 || rotation == 4) {
                translationQ = maxQ + 2;
                translationR = minR + 1 - placement;
            }
        } else if (sectionDirection.equals(PT_SOUTHEAST) || sectionDirection.equals(FT_SOUTHEAST)) {
            if (rotation == 2 || rotation == 5) {
                translationQ = maxQ - 1 + placement;
                translationR = maxR - placement;
            }
        } else if (sectionDirection.equals(PT_SOUTH) || sectionDirection.equals(FT_SOUTHWEST)) {
            if (rotation == 0 || rotation == 3) {
                translationQ = minQ + 1 - placement;
                translationR = maxR + 2;
            }
        } else if (sectionDirection.equals(PT_SOUTHWEST) || sectionDirection.equals(FT_WEST)) {
            if (rotation == 4 || rotation == 1) {
                translationQ = minQ - 2;
                translationR = maxR - 1 + placement;
            }
        } else if (sectionDirection.equals(PT_NORTHWEST) || sectionDirection.equals(FT_NORTHWEST)) {
            if (rotation == 5 || rotation == 2) {
                translationQ = minQ + 1 - placement;
                translationR = minR + placement;
            }
        }
        return new AxialTranslation(translationQ, translationR);
    }

    private AxialTranslation getTranslationElDorado(SectionWithRotationPositionSectionDirection sectionWithData, int maxQ, int minR, int minQ, int maxR) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int rotation = sectionWithData.getRotation();

        int translationQ = 0;
        int translationR = 0;

        if (sectionDirection instanceof SectionDirectionType.FlatTopSectionDirection flatTopSectionDirection) {
            switch (flatTopSectionDirection) {
                case FT_NORTHEAST: {
                    if (rotation == 0) {
                        translationQ = maxQ + 1;
                        translationR = minR - 1;
                    }
                    break;
                }
                case FT_EAST: {
                    if (rotation == 1) {
                        translationQ = maxQ + 1;
                        translationR = (minR + maxR) / 2;
                    }
                    break;
                }
                case FT_SOUTHEAST:
                    if (rotation == 2) {
                        translationQ = (minQ + maxQ) / 2;
                        translationR = maxR + 1;
                    }
                    break;
                case FT_SOUTHWEST: {
                    if (rotation == 3) {
                        translationQ = minQ - 1;
                        translationR = maxR + 1;
                    }
                    break;
                }
                case FT_WEST: {
                    if (rotation == 4) {
                        translationQ = minQ - 1;
                        translationR = (minR + maxR) / 2;
                    }
                    break;
                }
                case FT_NORTHWEST: {
                    if (rotation == 5) {
                        translationQ = (minQ + maxQ) / 2;
                        translationR = minR - 1;
                    }
                }
            }
        } else if (sectionDirection instanceof SectionDirectionType.PointyTopSectionDirection pointyTopSectionDirection) {
            switch (pointyTopSectionDirection) {
                case PT_NORTHEAST: {
                    if (rotation == 0) {
                        translationQ = maxQ + 1;
                        translationR = minR - 1;
                    }
                    break;
                }
                case PT_SOUTHEAST: {
                    if (rotation == 1) {
                        translationQ = maxQ + 1;
                        translationR = (minQ + maxQ) / 2;
                    }
                    break;
                } case PT_SOUTH: {
                    if (rotation == 2) {
                        translationQ = (minQ + maxQ) / 2;
                        translationR = maxQ + 1;
                    }
                    break;
                } case PT_SOUTHWEST: {
                    if (rotation == 3) {
                        translationQ = minQ - 1;
                        translationR = maxR + 1;
                    }
                    break;
                } case PT_NORTHWEST: {
                    if (rotation == 4) {
                        translationQ = minQ - 1;
                        translationR = (minR + maxR) / 2;
                    }
                    break;
                } case PT_NORTH:
                    if (rotation == 5) {
                        translationQ = (minQ + maxQ) / 2;
                        translationR = minR - 1;
                    }
                    break;
            }
        }
        return new AxialTranslation(translationQ, translationR);
    }

    private AxialTranslation getTranslationNormalSection(SectionWithRotationPositionSectionDirection sectionWithData, int maxQ, int minR, int minQ, int maxR) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int placement = sectionWithData.getPlacement();

        int translationQ = 0;
        int translationR = 0;

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

        return new AxialTranslation(translationQ, translationR);
    }


    public AxialTranslation getTranslation(SectionWithRotationPositionSectionDirection sectionWithData, int maxQ, int minR, int minQ, int maxR) {
        SectionType sectionType = sectionWithData.getSectionType();
        AxialTranslation axialTranslation;

        if (sectionType == SectionType.O || sectionType == SectionType.P || sectionType == SectionType.Q || sectionType == SectionType.R) {
            axialTranslation = getTranslationSmallOPQR(sectionWithData, maxQ, minR, minQ, maxR);
        } else if (sectionType == SectionType.ElDorado || sectionType == SectionType.ElDoradoTwo) {
            axialTranslation = getTranslationElDorado(sectionWithData, maxQ, minR, minQ, maxR);
        } else {
            axialTranslation = getTranslationNormalSection(sectionWithData, maxQ, minR, minQ, maxR);
        }
        return axialTranslation;
    }
}
