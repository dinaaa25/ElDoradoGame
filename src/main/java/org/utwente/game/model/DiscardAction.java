package org.utwente.game.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

public class DiscardAction extends Action {

    public DiscardAction(Player player, Resource resource) {
        super(player, resource);
    }

    @Override
    public void execute() {
        if (getResource() instanceof Card) {
            player.discardCard((Card) getResource());
        }
    }

    @Override
    public boolean validate() {
        return getResource() instanceof Card;
    }

    @Override
    public void discard() {

    }
}
