package org.utwente.Tile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.player.Player;

import java.util.*;

public class Tile {
    private final int x;
    private final int y;
    private final TileType tileType;
    private final int power;
    private final List<CaveCoin> caveCoins;
    private List<Player> players;

    public Tile(int x, int y, TileType tileType, int power, ArrayList<CaveCoin> caveCoins) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        this.power = power;
        this.caveCoins = (caveCoins == null) ? Collections.emptyList() : caveCoins; // Use provided list or initialize a new one
    }

    @JsonCreator
    public Tile(@JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("tileType") TileType tileType, @JsonProperty("power") int power) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        this.power = power;
        this.caveCoins = Collections.emptyList();
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

    public Optional<CaveCoin> retrieveCoin() {
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

    public boolean isStartingTile() {
        return this.tileType == TileType.Start;
    }

    public boolean isEndTile() {
        return this.tileType == TileType.ElDorado;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", tileType=" + tileType +
                ", power=" + power +
                ", caveCoins=" + caveCoins +
                '}';
    }

    public boolean isStartTile() {
        return true;
    }

    public void placePlayer(Player player) {
    }

    public boolean isEmpty() {
        return true;
    }

    public List<Player> getPlayers() {
        return this.players;
    }


}
