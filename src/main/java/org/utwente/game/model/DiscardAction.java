package org.utwente.game.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

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
    public ValidationResult validate() {
        if (getResource() instanceof Card) {
            return new ValidationResult(true, "");
        }
        return new ValidationResult(false, "Can only discard cards.");
    }

    @Override
    public void discard() {

    }
}
