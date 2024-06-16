package org.utwente.game.model;


import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.PowerType;
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
        // especially check native
        if(checkIfAdjacentCardOrCoin()) {
            return isTileToNeighbour() && !isTileToMountain() && isNoPlayerOnToTile();
        }
        return isTileToNeighbour() && resourceHasEnoughPower() && isCardMatchingTile() && isNoPlayerOnToTile();
    }

    protected boolean isTileToMountain(){
        return this.tileTo.getTileType() == TileType.Mountain;
    }

    private boolean checkIfAdjacentCardOrCoin() {
        return this.getResource() instanceof Card &&
                ((Card) this.getResource()).getCardType() == CardType.Ureinwohner
                || ((CaveCoin) this.getResource()).caveCoinType() == CaveCoinType.Adjacent;
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

