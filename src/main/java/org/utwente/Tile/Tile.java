package org.utwente.Tile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.utwente.Board.DirectionType;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.player.Player;

import java.sql.Array;
import java.util.*;

public class Tile {
    private final int x;
    private final int y;
    private final TileType tileType;
    private final int power;
    private final List<CaveCoin> caveCoins;
    private Set<Player> players;
    private boolean isLastWaitingTile;

    public Tile(int x, int y, TileType tileType, int power, ArrayList<CaveCoin> caveCoins, boolean isLastWaitingTile) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        this.power = power;
        this.caveCoins = (caveCoins == null) ? Collections.emptyList() : caveCoins; // Use provided list or initialize a new one
        this.players = new HashSet<>();
        this.isLastWaitingTile = isLastWaitingTile;
    }

    @JsonCreator
    public Tile(@JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("tileType") TileType tileType, @JsonProperty("power") int power,
                @JsonProperty("isLastWaitingTile") boolean isLastWaitingTile) {

        this(x, y, tileType, power, new ArrayList<>(), isLastWaitingTile);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public boolean isLastWaitingTile() {
        return this.isLastWaitingTile;
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

    public void placePlayer(Player player) {
        this.players.add(player);
    }

    public boolean isEmpty() {
        return this.players.isEmpty();
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }


    public boolean isNeighbor(Tile tile) {
        if (tile == null) {
            return false;
        }

        for (DirectionType.Direction direction : DirectionType.POINTY_TOP.getDirections()) {
            int neighborQ = this.x + direction.getDq();
            int neighborR = this.y + direction.getDr();
            if (neighborQ == tile.getX() && neighborR == tile.getY()) {
                return true;
            }
        }
        return false;
    }
}
