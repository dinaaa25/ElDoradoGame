package org.utwente.game;

import org.utwente.market.model.Resource;
import org.utwente.player.Player;

public class DiscardAction extends Action {

    public DiscardAction(Player player, Resource resource) {
        super(player, resource);
    }

    @Override
    void execute() {

    }

    @Override
    boolean validate() {
        return true;
    }
}

