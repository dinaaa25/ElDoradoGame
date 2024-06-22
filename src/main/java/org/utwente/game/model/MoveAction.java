package org.utwente.game.model;

import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardPowerException;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

public class MoveAction extends Action {

    private Tile tileFrom;
    private Tile tileTo;
    private Phase phase;

    public Tile getTileFrom() {
        return this.tileFrom;
    }

    public Tile getTileTo() {
        return this.tileTo;
    }

    public MoveAction(Player player, Resource resource, Tile from, Tile to) {
        this(player, resource, from, to, new Phase());
    }

    public MoveAction(Player player, Resource resource, Tile from, Tile to, Phase phase) {
        super(player, resource);
        this.tileFrom = from;
        this.tileTo = to;
        this.phase = phase;
    }



    @Override
    public void execute() {
        phase.addPlayedResource(this.getResource());
        if (checkIfScientistCard()) {
            phase.setCurrentPhase(PhaseType.SCIENTIST_PHASE);
            // TODO set game to ScientistPhase
            // TODO instruct user through selecting the right things
            // TODO go back to Move Phase
            try {
                resources.getFirst().removePower(CardType.Wissenschaftlerin.power);
            } catch (CardPowerException e) {
                throw new RuntimeException(e);
            }
        } else {
            tileTo.placePlayer(this.player);
            tileFrom.removePlayer(this.player);
            try {
                resources.getFirst().removePower(tileTo.getPower());
            } catch (CardPowerException e) {
                // TODO implement UI error display
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public boolean validate() {
        // especially check native
        if (checkIfAdjacentCardOrCoin()) {
            return isTileToNeighbour() && !isTileToMountain() && isNoPlayerOnToTile() && isSingleResource();
        }
        if (checkIfScientistCard()) {
            return isSingleResource();
        }
        return defaultValidate();
    }

    private boolean defaultValidate() {
        return isTileToNeighbour() && !isTileToMountain() && isNoPlayerOnToTile() && isSingleResource() && resourceHasEnoughPower() && isCardMatchingTile();
    }

    protected boolean isTileToMountain() {
        return this.tileTo.getTileType() == TileType.Mountain;
    }

    private boolean checkIfAdjacentCardOrCoin() {
        return checkIfCardOrCaveCoinType(CardType.Ureinwohner, CaveCoinType.Adjacent);
    }

    private boolean checkIfScientistCard() {
        return checkIfCardOrCaveCoinType(CardType.Wissenschaftlerin, null);
    }

    private boolean checkIfCardOrCaveCoinType(CardType cardType, CaveCoinType caveCoinType) {
        if (this.getResource() instanceof Card card) {
            return cardType != null && card.getCardType() == cardType;
        } else if (this.getResource() instanceof CaveCoin caveCoin) {
            return caveCoinType != null && caveCoin.caveCoinType() == caveCoinType;
        }
        return false;
    }

    private boolean isSingleResource() {
        return this.resources.size() == 1;
    }

    @Override
    public void discard() {
        Resource resource = this.getResource();
        if (resource instanceof Card) {
            if (resource.getPower() <= 0) {
                player.discardCard((Card) resource);
            }
        }
        // TODO: coins
    }

    public boolean isTileToNeighbour() {
        return tileTo.isNeighbor(tileFrom);
    }

    /**
     * check if another player is on TileTo
     *
     * @return true if destination tile isEmpty() and false if destination tile
     * !isEmpty()
     */
    public boolean isNoPlayerOnToTile() {
        return tileTo.isEmpty();
    }

    public boolean resourceHasEnoughPower() {
        return tileTo.getPower() <= this.getResource().getPower();
    }

    /**
     * compare not just power but also now whether this card type can be applied to
     * the tile you want to move to
     *
     * @return if resource matches to tile
     */
    public boolean isCardMatchingTile() {
        return tileTo.getTileType().getPowerTypeList().contains(this.getResource().getType());
    }

}
