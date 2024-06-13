package org.utwente.game.model;

import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

public class DiscardAction extends Action {

    public DiscardAction(Player player, Resource resource) {
        super(player, resource);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void discard() {

    }
}

