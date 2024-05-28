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

    public enum FlatTopDirection implements Direction {
        NORTH(0, -1),
        NORTHEAST(1, -1),
        SOUTHEAST(1, 0),
        SOUTH(0, 1),
        SOUTHWEST(-1, +1),
        NORTHWEST(-1, 0);

        private final int dq;
        private final int dr;

        FlatTopDirection(int dq, int dr) {
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

    public enum PointyTopDirection implements Direction {
        NORTHEAST(1, -1),
        EAST(1, 0),
        SOUTHEAST(0, 1),
        SOUTHWEST(-1, 1),
        WEST(-1, 0),
        NORTHWEST(0, -1);

        private final int dq;
        private final int dr;

        PointyTopDirection(int dq, int dr) {
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