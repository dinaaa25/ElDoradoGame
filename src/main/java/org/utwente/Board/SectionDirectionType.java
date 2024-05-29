package org.utwente.Board;

public enum SectionDirectionType {
    FLAT_TOP {
        @Override
        public SectionDirectionType.SectionDirection[] getSectionDirections() {
            return SectionDirectionType.FlatTopSectionDirection.values();
        }
    },
    POINTY_TOP {
        @Override
        public SectionDirectionType.SectionDirection[] getSectionDirections() {
            return SectionDirectionType.PointyTopSectionDirection.values();
        }
    };

    public abstract SectionDirectionType.SectionDirection[] getSectionDirections();

    public interface SectionDirection {
        int getDq();
        int getDr();
    }

    public enum FlatTopSectionDirection implements SectionDirectionType.SectionDirection {
        FT_NORTHEAST(1, -1),
        FT_EAST(-1, 0),
        FT_SOUTHEAST(1, 0),
        FT_SOUTHWEST(-1, +1),
        FT_WEST(-1, -1),
        FT_NORTHWEST(-1, 0);

        private final int dq;
        private final int dr;

        FlatTopSectionDirection(int dq, int dr) {
            this.dq = dq;
            this.dr = dr;
        }

        @Override
        public int getDq() {
            return dq;
        }

        @Override
        public int getDr() {
            return dr;
        }
    }

    public enum PointyTopSectionDirection implements SectionDirectionType.SectionDirection {
        PT_NORTH(0, -1),
        PT_NORTHEAST(1, -1),
        PT_SOUTHEAST(1, 0),
        PT_SOUTH(0, 1),
        PT_SOUTHWEST(-1, +1),
        PT_NORTHWEST(-1, 0);

        private final int dq;
        private final int dr;

        PointyTopSectionDirection(int dq, int dr) {
            this.dq = dq;
            this.dr = dr;
        }

        @Override
        public int getDq() {
            return dq;
        }

        @Override
        public int getDr() {
            return dr;
        }
    }
}
