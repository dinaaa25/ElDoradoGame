package org.utwente.game.model;

import org.utwente.market.model.Order;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.CaveCoin.CaveCoin;
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
            if (Configuration.getInstance().freeMarket) {
                Card boughtCard = this.market.buy(this.order.getCardToken());
                //added print to make sure that we recognize that this is not a normal behavior
                System.out.println("FREEMARKET: Moving bought card to play pile instead of discarding");
                this.player.getPlayPile().add(boughtCard);
                return;
            }
            if (this.checkIfTransmitter()) {
                Card boughtCard = this.market.buy(this.order.getCardToken());
                this.player.getDiscardPile().add(boughtCard);
                return;
            }
            this.boughtCard = this.market.buy(this.order);
            this.player.getFaceUpDiscardPile().add(this.boughtCard);
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
        // override with config variable to test advanced cards.
        System.out.println(Configuration.getInstance().freeMarket);
        if (Configuration.getInstance().freeMarket) {
            return new ValidationResult(true, "using free market configuration 💵");
        }
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
        for (Resource r : this.resources) {
            if (r instanceof Card card) {
                player.discardResource(card);
            } else if (r instanceof CaveCoin coin) {
                player.discardCoin(coin);
            }
        }
    }
}
