package org.utwente.game;

import org.utwente.market.model.Order;
import org.utwente.market.model.Resource;
import org.utwente.player.Player;

public class BuyAction extends Action{

    private Order order;

    public BuyAction(Player player, Resource resource) {
        super(player, resource);
    }

    @Override
    void execute() {

    }

    @Override
    void validate() {

    }
}
