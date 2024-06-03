package org.utwente.Board.Blockade;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Section.Section;
import org.utwente.Tile.TileType;

@Getter
@Setter
public class Blockade {
    private TileType tileType;
    private int power;
    private int points;
    private Section section1;
    private Section section2;
    private boolean isRemoved;

    public Blockade(TileType tileType, int power, int points) {
        this.tileType = tileType;
        this.power = power;
        this.points = points;
        this.isRemoved = false;
    }

    public void initialize(Section s1, Section s2) {
        this.section1 = s1;
        this.section2 = s2;
    }

    public void remove() {
        isRemoved = true;
    }




}


