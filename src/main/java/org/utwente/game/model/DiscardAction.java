package org.utwente.game.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

public class DiscardAction extends Action {

    private Phase phase;
    private Card cardToDiscard;

    public DiscardAction(Player player, Resource resource, Phase phase) {
        super(player, resource);
        this.phase = phase;
        if (resource instanceof Card) {
            this.cardToDiscard = (Card) resource;
        }else {
            this.cardToDiscard = null;
        }
    }

    @Override
    public void execute() {
        //todo:FIX
        if (phase.getCurrentPhase() == PhaseType.DRAW_PHASE) {
            if (cardToDiscard != null) {
                player.discardCard(cardToDiscard);
                phase.addPlayedResource(cardToDiscard);
                System.out.println("Card discarded.");
            } else {
                System.out.println("No card to discard.");
            }
        } else {
            System.out.println("Discard action can only be performed during the discard phase.");
        }
    }
    @Override
    public ValidationResult validate() {
        if (phase.getCurrentPhase() != PhaseType.DISCARD_PHASE) {
            return new ValidationResult(false, "Discard action can only be performed during the discard phase.");
        }
        if (cardToDiscard == null) {
            return new ValidationResult(false, "No card to discard.");
        }
        return new ValidationResult(true, "");
    }

    @Override
    public void discard() {
        player.discardCard(cardToDiscard);
        // Empty implementation
    }


}
