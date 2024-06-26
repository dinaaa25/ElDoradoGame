package org.utwente.Board;

public record CoordinateBounds(int minQ, int maxQ, int minR, int maxR) {

    @Override
    public String toString() {
        return "CoordinateBounds{" +
                "minQ=" + minQ +
                ", maxQ=" + maxQ +
                ", minR=" + minR +
                ", maxR=" + maxR +
                '}';
    }
}
