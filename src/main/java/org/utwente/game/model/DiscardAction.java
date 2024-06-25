package org.utwente.game.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

public class DiscardAction extends Action {

    private Card cardToDiscard;

    public DiscardAction(Player player, Resource resource, Phase phase) {
        super(player, resource);
    }

    @Override
    public void execute() {
        // not needed
    }

    @Override
    public ValidationResult validate() {
        if (getResource() instanceof Card) {
            cardToDiscard = (Card) getResource();
            return new ValidationResult(true, "");
        } else {
            return new ValidationResult(false, "No card to discard.");
        }
    }

    @Override
    public void discard() {
        player.getFaceUpDiscardPile().add(cardToDiscard);
        player.getPlayPile().remove(cardToDiscard);
    }

}
