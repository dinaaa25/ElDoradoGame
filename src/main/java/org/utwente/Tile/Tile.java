package org.utwente.Tile;

import org.utwente.CaveCoin.CaveCoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Tile {
    private int x;
    private int y;
    private final TileType tileType;
    private final int power;
    private List<CaveCoin> caveCoins;

    public Tile(int x, int y, TileType tileType, int power, ArrayList<CaveCoin> caveCoins) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        this.power = power;
        this.caveCoins = (caveCoins == null) ? Collections.emptyList() : caveCoins; // Use provided list or initialize a new one
    }

    public TileType getTileType() {
        return tileType;
    }

    public int getPower() {
        return power;
    }

    public boolean hasCaveCoins() {
        return !caveCoins.isEmpty();
    }

    public Optional<CaveCoin> retreiveCoin() {
        if (!hasCaveCoins()) {
            return Optional.empty();
        }
        CaveCoin coin = caveCoins.get(0);
        caveCoins.remove(0);
        return Optional.of(coin);
    }

    public int getCaveCoinCount() {
        return caveCoins.size();
    }
}
