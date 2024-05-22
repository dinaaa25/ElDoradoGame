package org.utwente.Board;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Tile.TileType;

@Getter
@Setter
public class Blockade {
    TileType tileType;
    int power;

    public Blockade() {

    }

    public Blockade(TileType tileType, int power) {
        this.tileType = tileType;
        this.power = power;
    }


}


