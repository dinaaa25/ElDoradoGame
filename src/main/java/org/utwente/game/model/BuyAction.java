package org.utwente.game.model;

import org.utwente.market.model.Order;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.market.exceptions.BuyException;
import org.utwente.market.model.*;

import java.util.List;

public class BuyAction extends Action {

    private Order order;
    private Market market;
    private Card boughtCard;

    public BuyAction(Player player, List<Resource> resources, CardType desiredCardType, Market market) {
        super(player, resources);
        this.order = new Order(desiredCardType, this.getTotalMoney());
        this.market = market;
    }

    @Override
    public void execute() {
        try {
            if (this.checkIfTransmitter()) {
                this.boughtCard = this.market.buy(this.order.getCardToken());
            } else {
                this.boughtCard = this.market.buy(this.order);
            }
        } catch (BuyException buyException) {

        }
    }

    /**
     * iterate over the list of resources and translate each resource to money
     * 
     * @return int value of money value for all resources in this.resources
     */
    private int getTotalMoney() {
        return (int) Math.floor(
                this.resources.stream().map(resource -> resource.getValue()).reduce((x, y) -> x + y).orElse(0.0));
    }

    private boolean checkIfTransmitter() {
        return this.getResource() instanceof Card
                && ((Card) this.getResource()).getCardType() == CardType.Fernsprechgerat;
    }

    @Override
    public ValidationResult validate() {
        // especially check for the transmitter card.
        if (this.checkIfTransmitter()) {
            boolean isCardAvailable = this.market.isCardAvailable(order.getCardToken());
            return new ValidationResult(isCardAvailable,
                    isCardAvailable ? "Chosen Card Is Available" : "Chosen Card is not available");
        }
        // otherwise check the money and card.
        return market.canBuy(order);
    }

    @Override
    public void discard() {
        player.discardCard(this.boughtCard);
        for (Resource r : this.resources) {
            if (r instanceof Card) {
                player.discardCard((Card) r);
            } else {
                // TODO: remove coin from the game coin pile basically
            }
        }
    }
}
