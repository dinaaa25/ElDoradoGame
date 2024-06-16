package org.utwente.game.model;


import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

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
    public void execute() {
        tileTo.placePlayer(this.player);
        tileFrom.removePlayer(this.player);
    }

    @Override
    public boolean validate() {
        return isTileToNeighbour() && resourceHasEnoughPower() && isCardMatchingTile() && isNoPlayerOnToTile();
    }

    @Override
    public void discard() {
        Resource resource = this.getResource();
        if(resource instanceof Card) {
            if(resource.getPower() <= 0) {
                player.discardCard((Card) resource);
            }
        }
        // TODO: coins
    }

    public boolean isTileToNeighbour() {
        return tileTo.isNeighbor(tileFrom);
    }

    /**
     *  check TileType of TileTo is of type Mountain
     * @param tile
     * @return
     */
    public TileType getTileType(Tile tile) {
        return TileType.Mountain;
    }

    /**
     * check if another player is on TileTo
     * @return true if destination tile isEmpty() and false if destination tile !isEmpty()
     */
    public boolean isNoPlayerOnToTile() {
        return tileTo.isEmpty();
    }

    public boolean resourceHasEnoughPower() {
        return tileTo.getPower() <= this.getResource().getPower();
    }


    /**
     * compare not just power but also now whether this card type can be applied to the tile you want to move to
     * @return if resource matches to tile
     */
    public boolean isCardMatchingTile(){
        return tileTo.getTileType().getPowerTypeList().contains(this.getResource().getType());
    }


}

