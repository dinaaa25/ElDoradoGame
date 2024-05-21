package org.utwente.game;


import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Resource;
import org.utwente.player.Player;

public class MoveAction extends Action {

    private Tile tileFrom;
    private Tile tileTo;

    public Tile getTileFrom() {
        return this.tileFrom;
    }

    public Tile getTileTo() {
        return this.tileTo;
    }

    public MoveAction(Player player, Resource resource, Tile from, Tile to) {
        super(player, resource);
        this.tileFrom = from;
        this.tileTo = to;
    }

    @Override
    void execute() {

    }

    @Override
    void validate() {

    }

    private boolean isTileToNeighbour(Tile tile) {
        return true;
    }

    // check TileType of TileTo is of type Mountain
    private TileType getTileType(Tile tile) {
        return TileType.Mountain;
    }

    // check if another player is on TileTo
    private boolean isPlayerOnTile(Tile tile) {
        return true;
    }

    private boolean resourceHasEnoughPower() {
        // compare if card has power 3 and tile has power 3
        return true;
    }


    private boolean isCardMatchingTile(){
        // compare not just power but also now whether this card type can be applied to the tile you want to move to
        return true;
    }


}

