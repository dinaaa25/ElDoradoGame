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
    }

    public enum PointyTopSectionDirection implements SectionDirection {
        PT_NORTH,
        PT_NORTHEAST,
        PT_SOUTHEAST,
        PT_SOUTH,
        PT_SOUTHWEST,
        PT_NORTHWEST;
    }
}
