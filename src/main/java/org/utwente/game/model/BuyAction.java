package org.utwente.game.model;


import org.utwente.market.model.Order;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

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
            this.boughtCard = this.market.buy(this.order);
        }catch(BuyException buyException) {

        }
    }

    // iterate over the list of resources and translate each resource to money
    private int getTotalMoney() {
        return (int) Math.floor(this.resources.stream().map(resource -> resource.getValue()).reduce((x, y) -> x + y).orElse(0.0));
    }

    @Override
    public boolean validate() {
        return market.canBuy(order).getStatus();
    }

    @Override
    public void discard() {

    }
}
