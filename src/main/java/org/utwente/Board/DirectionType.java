package org.utwente.Board;

public enum DirectionType {

    FLAT_TOP {
        @Override
        public Direction[] getDirections() {
            return FlatTopDirection.values();
        }
    },
    POINTY_TOP {
        @Override
        public Direction[] getDirections() {
            return PointyTopDirection.values();
        }
    };

    public abstract Direction[] getDirections();

    public interface Direction {
        int getDq();
        int getDr();
    }

    public static abstract class AbstractDirection implements Direction {
        private final int dq;
        private final int dr;

        protected AbstractDirection(int dq, int dr) {
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

    public static class FlatTopDirection extends AbstractDirection {
        public static final FlatTopDirection NORTH = new FlatTopDirection(0, -1);
        public static final FlatTopDirection NORTHEAST = new FlatTopDirection(1, -1);
        public static final FlatTopDirection SOUTHEAST = new FlatTopDirection(1, 0);
        public static final FlatTopDirection SOUTH = new FlatTopDirection(0, 1);
        public static final FlatTopDirection SOUTHWEST = new FlatTopDirection(-1, 1);
        public static final FlatTopDirection NORTHWEST = new FlatTopDirection(-1, 0);

        private FlatTopDirection(int dq, int dr) {
            super(dq, dr);
        }

        public static FlatTopDirection[] values() {
            return new FlatTopDirection[] { NORTH, NORTHEAST, SOUTHEAST, SOUTH, SOUTHWEST, NORTHWEST };
        }
    }

    public static class PointyTopDirection extends AbstractDirection {
        public static final PointyTopDirection NORTHEAST = new PointyTopDirection(1, -1);
        public static final PointyTopDirection EAST = new PointyTopDirection(1, 0);
        public static final PointyTopDirection SOUTHEAST = new PointyTopDirection(0, 1);
        public static final PointyTopDirection SOUTHWEST = new PointyTopDirection(-1, 1);
        public static final PointyTopDirection WEST = new PointyTopDirection(-1, 0);
        public static final PointyTopDirection NORTHWEST = new PointyTopDirection(0, -1);

        private PointyTopDirection(int dq, int dr) {
            super(dq, dr);
        }

        public static PointyTopDirection[] values() {
            return new PointyTopDirection[] { NORTHEAST, EAST, SOUTHEAST, SOUTHWEST, WEST, NORTHWEST };
        }
    }
}