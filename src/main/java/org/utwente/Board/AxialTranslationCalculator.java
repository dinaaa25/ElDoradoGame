package org.utwente.Board;

import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;

import static org.utwente.Board.SectionDirectionType.FlatTopSectionDirection.*;
import static org.utwente.Board.SectionDirectionType.PointyTopSectionDirection.*;

public class AxialTranslationCalculator {

    public record AxialTranslation(int q, int r) {}

    private AxialTranslation getTranslationSmallOPQR(SectionWithRotationPositionSectionDirection sectionWithData, CoordinateBounds coordinateBounds) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int rotation = sectionWithData.getRotation();
        int placement = sectionWithData.getPlacement();

        int translationQ = 0;
        int translationR = 0;

        if (sectionDirection.equals(PT_NORTH) || sectionDirection.equals(FT_NORTHEAST)) {
            if (rotation == 0 || rotation == 3) {
                translationQ = coordinateBounds.maxQ() - placement;
                translationR = coordinateBounds.minR() - 2;
            }
        }
        else if (sectionDirection.equals(PT_NORTHEAST) || sectionDirection.equals(FT_EAST)) {
            if (rotation == 1 || rotation == 4) {
                translationQ = coordinateBounds.maxQ() + 2;
                translationR = coordinateBounds.minR() + 1 - placement;
            }
        } else if (sectionDirection.equals(PT_SOUTHEAST) || sectionDirection.equals(FT_SOUTHEAST)) {
            if (rotation == 2 || rotation == 5) {
                translationQ = coordinateBounds.maxQ() - 1 + placement;
                translationR = coordinateBounds.maxR() - placement;
            }
        } else if (sectionDirection.equals(PT_SOUTH) || sectionDirection.equals(FT_SOUTHWEST)) {
            if (rotation == 0 || rotation == 3) {
                translationQ = coordinateBounds.minQ() + 1 - placement;
                translationR = coordinateBounds.maxR() + 2;
            }
        } else if (sectionDirection.equals(PT_SOUTHWEST) || sectionDirection.equals(FT_WEST)) {
            if (rotation == 4 || rotation == 1) {
                translationQ = coordinateBounds.minQ() - 2;
                translationR = coordinateBounds.maxR() - 1 + placement;
            }
        } else if (sectionDirection.equals(PT_NORTHWEST) || sectionDirection.equals(FT_NORTHWEST)) {
            if (rotation == 5 || rotation == 2) {
                translationQ = coordinateBounds.minQ() + 1 - placement;
                translationR = coordinateBounds.minR() + placement;
            }
        }
        return new AxialTranslation(translationQ, translationR);
    }

    private AxialTranslation getTranslationElDorado(SectionWithRotationPositionSectionDirection sectionWithData, CoordinateBounds coordinateBounds) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int rotation = sectionWithData.getRotation();

        int translationQ = 0;
        int translationR = 0;

        if (sectionDirection instanceof SectionDirectionType.FlatTopSectionDirection flatTopSectionDirection) {
            switch (flatTopSectionDirection) {
                case FT_NORTHEAST: {
                    if (rotation == 0) {
                        translationQ = coordinateBounds.maxQ() + 1;
                        translationR = coordinateBounds.minR() - 1;
                    }
                    break;
                }
                case FT_EAST: {
                    if (rotation == 1) {
                        translationQ = coordinateBounds.maxQ()+ 1;
                        translationR = (coordinateBounds.minR() + coordinateBounds.maxR()) / 2;
                    }
                    break;
                }
                case FT_SOUTHEAST:
                    if (rotation == 2) {
                        translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        translationR = coordinateBounds.maxR() + 1;
                    }
                    break;
                case FT_SOUTHWEST: {
                    if (rotation == 3) {
                        translationQ = coordinateBounds.minQ() - 1;
                        translationR = coordinateBounds.maxR() + 1;
                    }
                    break;
                }
                case FT_WEST: {
                    if (rotation == 4) {
                        translationQ = coordinateBounds.minQ() - 1;
                        translationR = (coordinateBounds.minR() + coordinateBounds.maxR()) / 2;
                    }
                    break;
                }
                case FT_NORTHWEST: {
                    if (rotation == 5) {
                        translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        translationR = coordinateBounds.minR() - 1;
                    }
                }
            }
        } else if (sectionDirection instanceof SectionDirectionType.PointyTopSectionDirection pointyTopSectionDirection) {
            switch (pointyTopSectionDirection) {
                case PT_NORTHEAST: {
                    if (rotation == 0) {
                        translationQ = coordinateBounds.maxQ()+ 1;
                        translationR = coordinateBounds.minR() - 1;
                    }
                    break;
                }
                case PT_SOUTHEAST: {
                    if (rotation == 1) {
                        translationQ = coordinateBounds.maxQ()+ 1;
                        translationR = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                    }
                    break;
                } case PT_SOUTH: {
                    if (rotation == 2) {
                        translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        translationR = coordinateBounds.maxQ()+ 1;
                    }
                    break;
                } case PT_SOUTHWEST: {
                    if (rotation == 3) {
                        translationQ = coordinateBounds.minQ() - 1;
                        translationR = coordinateBounds.maxR() + 1;
                    }
                    break;
                } case PT_NORTHWEST: {
                    if (rotation == 4) {
                        translationQ = coordinateBounds.minQ() - 1;
                        translationR = (coordinateBounds.minR() + coordinateBounds.maxR()) / 2;
                    }
                    break;
                } case PT_NORTH:
                    if (rotation == 5) {
                        translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        translationR = coordinateBounds.minR() - 1;
                    }
                    break;
            }
        }
        return new AxialTranslation(translationQ, translationR);
    }

    private AxialTranslation getTranslationNormalSection(SectionWithRotationPositionSectionDirection sectionWithData, CoordinateBounds coordinateBounds) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int placement = sectionWithData.getPlacement();

        int translationQ = 0;
        int translationR = 0;

        if (sectionDirection.equals(PT_NORTHEAST) || sectionDirection.equals(FT_EAST)) {
            translationQ = coordinateBounds.maxQ() + 1 + 3;
            translationR = coordinateBounds.minR() - 1 + placement;
        } else if (sectionDirection.equals(PT_SOUTHEAST) || sectionDirection.equals(FT_SOUTHEAST)) {
            translationQ = coordinateBounds.maxQ()+ 1 - placement;
            translationR = coordinateBounds.maxR() + placement;
            if (placement == -1) {
                translationQ -= 2;
                translationR += 1;
            }
        } else if (sectionDirection.equals(PT_SOUTH) || sectionDirection.equals(FT_SOUTHWEST)) {
            translationQ = coordinateBounds.minQ() - placement;
            translationR = coordinateBounds.maxR() + 3 + 1;
        } else if (sectionDirection.equals(PT_SOUTHWEST) || sectionDirection.equals(FT_WEST)) {
            translationQ = coordinateBounds.minQ() - 3 - 1;
            translationR = coordinateBounds.maxR() + 1 - placement;
        } else if (sectionDirection.equals(PT_NORTHWEST) || sectionDirection.equals(FT_NORTHWEST)) {
            translationQ = coordinateBounds.minQ() - placement;
            translationR = coordinateBounds.minR() - 1 + placement;
            if (placement == -1) {
                translationQ -= 1;
                translationR += 2;
            }
        } else if (sectionDirection.equals(PT_NORTH) || sectionDirection.equals(FT_NORTHEAST)) {
            translationQ = coordinateBounds.maxQ() + placement;
            translationR = coordinateBounds.minR() - 3 - 1;
        }

        return new AxialTranslation(translationQ, translationR);
    }


    public AxialTranslation getTranslation(SectionWithRotationPositionSectionDirection sectionWithData, CoordinateBounds coordinateBounds) {
        SectionType sectionType = sectionWithData.getSectionType();
        AxialTranslation axialTranslation;

        if (sectionType == SectionType.O || sectionType == SectionType.P || sectionType == SectionType.Q || sectionType == SectionType.R) {
            axialTranslation = getTranslationSmallOPQR(sectionWithData, coordinateBounds);
        } else if (sectionType == SectionType.ElDorado || sectionType == SectionType.ElDoradoTwo) {
            axialTranslation = getTranslationElDorado(sectionWithData, coordinateBounds);
        } else {
            axialTranslation = getTranslationNormalSection(sectionWithData, coordinateBounds);
        }
        return axialTranslation;
    }
}
