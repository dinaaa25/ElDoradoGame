package org.utwente.Board.Blockade;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Tile.TileType;

import java.awt.*;

@Getter
@Setter
public class Blockade {
    private TileType tileType;
    @Getter
    private int power;
    @Getter
    private Point start;
    @Getter
    private Point end;
    @Getter
    private boolean isRemoved;

    public Blockade(TileType tileType, Point start, Point end, int power) {
        this.tileType = tileType;
        this.start = start;
        this.end = end;
        this.power = power;
    }

    public void remove() {
        isRemoved = true;
    }




}


