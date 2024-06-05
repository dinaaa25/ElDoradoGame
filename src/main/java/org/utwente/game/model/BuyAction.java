package org.utwente.game.model;

import org.utwente.market.model.Order;
import org.utwente.market.model.Resource;
import org.utwente.player.Player;

public class BuyAction extends Action {

    private Order order;

    public BuyAction(Player player, Resource resource) {
        super(player, resource);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean validate() {
        return true;
    }
}
