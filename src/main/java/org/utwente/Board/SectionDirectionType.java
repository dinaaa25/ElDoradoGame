package org.utwente.Board;

public enum SectionDirectionType {
    FLAT_TOP {
        @Override
        public SectionDirection[] getSectionDirections() {
            return FlatTopSectionDirection.values();
        }
    },
    POINTY_TOP {
        @Override
        public SectionDirection[] getSectionDirections() {
            return PointyTopSectionDirection.values();
        }
    };

    public abstract SectionDirection[] getSectionDirections();

    public interface SectionDirection {}

    public enum FlatTopSectionDirection implements SectionDirection {
        FT_NORTHEAST,
        FT_EAST,
        FT_SOUTHEAST,
        FT_SOUTHWEST,
        FT_WEST,
        FT_NORTHWEST;

        public static PointyTopSectionDirection fromFlatTopDirection(FlatTopSectionDirection flatTopSectionDirection) {
            return switch (flatTopSectionDirection) {
                case FT_NORTHEAST -> PointyTopSectionDirection.PT_NORTHEAST;
                case FT_EAST -> PointyTopSectionDirection.PT_SOUTHEAST;
                case FT_SOUTHEAST -> PointyTopSectionDirection.PT_SOUTH;
                case FT_SOUTHWEST -> PointyTopSectionDirection.PT_SOUTHWEST;
                case FT_WEST -> PointyTopSectionDirection.PT_NORTHWEST;
                case FT_NORTHWEST -> PointyTopSectionDirection.PT_NORTH;
            };
        }
    }

    public enum PointyTopSectionDirection implements SectionDirection {
        PT_NORTH,
        PT_NORTHEAST,
        PT_SOUTHEAST,
        PT_SOUTH,
        PT_SOUTHWEST,
        PT_NORTHWEST;
    }

    public static PointyTopSectionDirection toPointyTopSectionDirection(SectionDirection sectionDirection) {
        if (sectionDirection instanceof PointyTopSectionDirection) {
            return (PointyTopSectionDirection) sectionDirection;
        } else if (sectionDirection instanceof FlatTopSectionDirection) {
            return FlatTopSectionDirection.fromFlatTopDirection((FlatTopSectionDirection) sectionDirection);
        } else {
            throw new IllegalArgumentException("Unknown SectionDirection type: " + sectionDirection.getClass().getName());
        }
    }
}