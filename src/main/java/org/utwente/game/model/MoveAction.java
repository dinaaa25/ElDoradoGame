package org.utwente.game.model;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardPowerException;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

import java.util.Optional;

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
        try {
            System.out.println("consumed power: " + resources.getFirst().getConsumedPower());
            System.out.println("card in use: " + resources.getFirst().toString());
            System.out.println("move power: " + getMovePower());
            resources.getFirst().removePower(getMovePower());
        } catch (CardPowerException e) {
            // TODO implement UI error display
            throw new RuntimeException(e);
        }

        // Add cavecoin to the CaveCoinPile of the player if eligible
        checkForCaveCoin();

        if (moveIsThroughBlockade()) {
            Blockade blockade = tileFrom.earnBlockade();
            player.addBlockade(blockade);
            return;
        }

        movePlayer();
    }

    private void checkForCaveCoin() {
        if (tileTo.getCaveCoinNeighbour() != null && tileFrom.getCaveCoinNeighbour() == null) {
            Tile caveCoinNeighbour = tileTo.getCaveCoinNeighbour();
            Optional<CaveCoin> caveCoin = caveCoinNeighbour.retrieveCoin();
            caveCoin.ifPresent(coin -> player.getCaveCoinPile().add(coin));
        }
    }

    private int getMovePower() {
        if (tileFrom.isBlockadeTile()) {
            return tileFrom.getBlockade().getPower();
        }

        return tileTo.getPower();
    }

    private TileType getMoveTileType() {
        if (tileFrom.isBlockadeTile()) {
            return tileFrom.getBlockade().getTileType();
        }

        return tileTo.getTileType();
    }

    private boolean moveIsThroughBlockade() {
        return this.tileFrom.isBlockadeTile();
    }

    private void movePlayer() {
        tileTo.placePlayer(this.player);
        tileFrom.removePlayer(this.player);
    }

    @Override
    public ValidationResult validate() {
        if (checkIfScientistCard()) {
            return new ValidationResult(true, "");
        }
        return checkNormalMovement();
    }

    private ValidationResult checkNormalMovement() {
        if (!isTileToNeighbour()) {
            return new ValidationResult(false, "Destination tile is not a neighbor of the player's tile.");
        }
        if (isTileToMountain()) {
            return new ValidationResult(false, "Destination tile is a mountain.");
        }
        if (!isNoPlayerOnToTile()) {
            return new ValidationResult(false, "There is another player on the destination tile.");
        }
        if (!resourceHasEnoughPower()) {
            return new ValidationResult(false, "Chosen card or coin does not have enough power.");
        }
        if (!isCardMatchingTile()) {
            return new ValidationResult(false, "The power type of the card or coin does not match the tile.");
        }

        return new ValidationResult(true, "");
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
        if (resource.remainingPower() <= 0) {
            player.discardResource(resource);
        }
    }

    public boolean isTileToNeighbour() {
        return tileTo.isNeighbor(tileFrom);
    }

    /**
     * check if another player is on TileTo
     *
     * @return true if destination tile isEmpty() and false if destination tile
     *         !isEmpty()
     */
    public boolean isNoPlayerOnToTile() {
        return tileTo.isEmpty();
    }

    public boolean resourceHasEnoughPower() {
        return this.getMovePower() <= this.getResource().remainingPower();
    }

    /**
     * compare not just power but also now whether this card type can be applied to
     * the tile you want to move to
     *
     * @return if resource matches to tile
     */
    public boolean isCardMatchingTile() {
        return getMoveTileType().getPowerTypeList().contains(this.getResource().getType());
    }

}
