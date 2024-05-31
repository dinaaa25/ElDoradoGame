package org.utwente.Tile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.utwente.Board.AxialTranslationCalculator;
import org.utwente.Board.DirectionType;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.player.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.utwente.game.GameConfig.TILE_COLORS;

public class Tile {
    private int q;
    private int r;
    private final TileType tileType;
    private final int power;
    private final List<CaveCoin> caveCoins;
    private Set<Player> players;
    private boolean isLastWaitingTile;

    public Tile(int q, int r, TileType tileType, int power, ArrayList<CaveCoin> caveCoins, boolean isLastWaitingTile) {
        this.q = q;
        this.r = r;
        this.tileType = tileType;
        this.power = power;
        this.caveCoins = (caveCoins == null) ? Collections.emptyList() : caveCoins; // Use provided list or initialize a new one
        this.players = new HashSet<>();
        this.isLastWaitingTile = isLastWaitingTile;
    }

    @JsonCreator
    public Tile(@JsonProperty("q") int q, @JsonProperty("r") int r, @JsonProperty("tileType") TileType tileType, @JsonProperty("power") int power,
                @JsonProperty("isLastWaitingTile") boolean isLastWaitingTile) {

        this(q, r, tileType, power, new ArrayList<>(), isLastWaitingTile);
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public TileType getTileType() {
        return tileType;
    }

    public int getPower() {
        return power;
    }

    public void rotate(int turns) {
        turns = ((turns % 6) + 6) % 6;

        for (int i = 0; i < turns; i++) {
            int tempQ = this.q;
            int tempR = this.r;
            this.q = -tempR;
            this.r = tempQ + tempR;
        }
    }

    public Color getTileColor() {
        return TILE_COLORS.getOrDefault(tileType, Color.LIGHT_GRAY);
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
                "x=" + q +
                ", y=" + r +
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
            int neighborQ = this.q + direction.getDq();
            int neighborR = this.r + direction.getDr();
            if (neighborQ == tile.getQ() && neighborR == tile.getR()) {
                return true;
            }
        }
        return false;
    }

    public void translate(AxialTranslationCalculator.AxialTranslation translationParameters) {
        q += translationParameters.getQ();
        r += translationParameters.getR();
    }

}
