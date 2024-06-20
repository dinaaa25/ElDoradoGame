package org.utwente.Board;

import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;

import java.util.*;

public class AxialTranslationCalculator {

    public record AxialTranslation(int q, int r) {
    }

    private AxialTranslation getTranslationSmallRectangle(SectionWithRotationPositionSectionDirection sectionWithData,
                                                          CoordinateBounds coordinateBounds) {

        SectionDirectionType.PointyTopSectionDirection sectionDirection = SectionDirectionType.toPointyTopSectionDirection(sectionWithData.getSectionDirection());
        int rotation = sectionWithData.getRotation();
        int placement = sectionWithData.getPlacement();

        return switch (sectionDirection) {
            case PT_NORTH:
                if (rotation == 0 || rotation == 3) {
                    int translationQ = coordinateBounds.maxQ() - placement;
                    int translationR = coordinateBounds.minR() - 2;
                    yield new AxialTranslation(translationQ, translationR);
                }
            case PT_NORTHEAST:
                if (rotation == 1 || rotation == 4) {
                    int translationQ = coordinateBounds.maxQ() + 2;
                    int translationR = coordinateBounds.minR() + 1 - placement;
                    yield new AxialTranslation(translationQ, translationR);
                }
            case PT_SOUTHEAST:
                if (rotation == 2 || rotation == 5) {
                    int translationQ = coordinateBounds.maxQ() - 1 + placement;
                    int translationR = coordinateBounds.maxR() - placement;
                    yield new AxialTranslation(translationQ, translationR);
                }
            case PT_SOUTH:
                if (rotation == 0 || rotation == 3) {
                    int translationQ = coordinateBounds.minQ() + 1 - placement;
                    int translationR = coordinateBounds.maxR() + 2;
                    yield new AxialTranslation(translationQ, translationR);
                }
            case PT_SOUTHWEST:
                if (rotation == 4 || rotation == 1) {
                    int translationQ = coordinateBounds.minQ() - 2;
                    int translationR = coordinateBounds.maxR() - 1 + placement;
                    yield new AxialTranslation(translationQ, translationR);
                }
            case PT_NORTHWEST:
                if (rotation == 5 || rotation == 2) {
                    int translationQ = coordinateBounds.minQ() + 1 - placement;
                    int translationR = coordinateBounds.minR() + placement;
                    yield new AxialTranslation(translationQ, translationR);
                }
            default:
                yield new AxialTranslation(0, 0);
        };
    }

    private AxialTranslation getTranslationElDorado(SectionWithRotationPositionSectionDirection sectionWithData,
                                                    CoordinateBounds coordinateBounds) {
        SectionDirectionType.SectionDirection sectionDirection = sectionWithData.getSectionDirection();
        int rotation = sectionWithData.getRotation();
        if (sectionDirection instanceof SectionDirectionType.PointyTopSectionDirection pointyTopSectionDirection) {
            return switch (pointyTopSectionDirection) {
                case PT_NORTHEAST: {
                    if (rotation == 0) {
                        int translationQ = coordinateBounds.maxQ() + 1;
                        int translationR = coordinateBounds.minR() - 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case PT_SOUTHEAST: {
                    if (rotation == 1) {
                        int translationQ = coordinateBounds.maxQ() + 1;
                        int translationR = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case PT_SOUTH: {
                    if (rotation == 2) {
                        int translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        int translationR = coordinateBounds.maxQ() + 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case PT_SOUTHWEST: {
                    if (rotation == 3) {
                        int translationQ = coordinateBounds.minQ() - 1;
                        int translationR = coordinateBounds.maxR() + 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case PT_NORTHWEST: {
                    if (rotation == 4) {
                        int translationQ = coordinateBounds.minQ() - 1;
                        int translationR = (coordinateBounds.minR() + coordinateBounds.maxR()) / 2;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case PT_NORTH: {
                    if (rotation == 5) {
                        int translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        int translationR = coordinateBounds.minR() - 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                default: {
                    yield new AxialTranslation(0, 0);
                }
            };
        } else {
            SectionDirectionType.FlatTopSectionDirection flatTopSectionDirection = (SectionDirectionType.FlatTopSectionDirection) sectionDirection;
            return switch (flatTopSectionDirection) {
                case FT_NORTHEAST: {
                    if (rotation == 0) {
                        int translationQ = coordinateBounds.maxQ() + 1;
                        int translationR = coordinateBounds.minR() - 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case FT_EAST: {
                    if (rotation == 1) {
                        int translationQ = coordinateBounds.maxQ() + 1;
                        int translationR = (coordinateBounds.minR() + coordinateBounds.maxR()) / 2;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case FT_SOUTHEAST:
                    if (rotation == 2) {
                        int translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        int translationR = coordinateBounds.maxR() + 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                case FT_SOUTHWEST: {
                    if (rotation == 3) {
                        int translationQ = coordinateBounds.minQ() - 1;
                        int translationR = coordinateBounds.maxR() + 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case FT_WEST: {
                    if (rotation == 4) {
                        int translationQ = coordinateBounds.minQ() - 1;
                        int translationR = (coordinateBounds.minR() + coordinateBounds.maxR()) / 2;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                case FT_NORTHWEST: {
                    if (rotation == 5) {
                        int translationQ = (coordinateBounds.minQ() + coordinateBounds.maxQ()) / 2;
                        int translationR = coordinateBounds.minR() - 1;
                        yield new AxialTranslation(translationQ, translationR);
                    }
                }
                default: {
                    yield new AxialTranslation(0, 0);
                }
            };
        }
    }

    private AxialTranslation getTranslationNormalSection(SectionWithRotationPositionSectionDirection sectionWithData,
                                                         CoordinateBounds coordinateBounds) {
        SectionDirectionType.PointyTopSectionDirection sectionDirection = SectionDirectionType.toPointyTopSectionDirection(sectionWithData.getSectionDirection());
        int placement = sectionWithData.getPlacement();

        return switch (sectionDirection) {
            case PT_NORTHEAST: {
                int translationQ = coordinateBounds.maxQ() + 1 + 3;
                int translationR = coordinateBounds.minR() - 1 + placement;
                yield new AxialTranslation(translationQ, translationR);
            }
            case PT_SOUTHEAST: {
                int translationQ = coordinateBounds.maxQ() + 1 - placement;
                int translationR = coordinateBounds.maxR() + placement;
                if (placement == -1) {
                    translationQ -= 2;
                    translationR += 1;
                }
                yield new AxialTranslation(translationQ, translationR);
            }
            case PT_SOUTH: {
                int translationQ = coordinateBounds.minQ() - placement;
                int translationR = coordinateBounds.maxR() + 3 + 1;
                yield new AxialTranslation(translationQ, translationR);
            }
            case PT_SOUTHWEST: {
                int translationQ = coordinateBounds.minQ() - 3 - 1;
                int translationR = coordinateBounds.maxR() + 1 - placement;
                yield new AxialTranslation(translationQ, translationR);
            }
            case PT_NORTHWEST: {
                int translationQ = coordinateBounds.minQ() - placement;
                int translationR = coordinateBounds.minR() - 1 + placement;
                if (placement == -1) {
                    translationQ -= 1;
                    translationR += 2;
                }
                yield new AxialTranslation(translationQ, translationR);
            }
            case PT_NORTH: {
                int translationQ = coordinateBounds.maxQ() + placement;
                int translationR = coordinateBounds.minR() - 3 - 1;
                yield new AxialTranslation(translationQ, translationR);
            }
        };
    }

    private boolean isRectangleSection(SectionType sectionType) {
        return List.of(SectionType.O, SectionType.P, SectionType.Q, SectionType.R).contains(sectionType);
    }

    private boolean isElDoradoSection(SectionType sectionType) {
        return List.of(SectionType.ElDorado, SectionType.ElDoradoTwo).contains(sectionType);
    }

    public AxialTranslation getTranslation(SectionWithRotationPositionSectionDirection sectionWithData,
                                           CoordinateBounds coordinateBounds) {
        SectionType sectionType = sectionWithData.getSectionType();

        if (isRectangleSection(sectionType)) {
            return getTranslationSmallRectangle(sectionWithData, coordinateBounds);
        }

        if (isElDoradoSection(sectionType)) {
            return getTranslationElDorado(sectionWithData, coordinateBounds);
        }

        return getTranslationNormalSection(sectionWithData, coordinateBounds);
    }
}
