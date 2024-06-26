package org.utwente.player.view.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.utwente.game.model.Phase;
import org.utwente.market.model.Card;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.player.model.CardPile;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.PlayCardEvent;

public class PlayCards extends JPanel {

    public PlayCards(CardPile pile, Phase phase) {
        super(new FlowLayout());
        for (Card card : pile.getResources()) {
            if (phase != null && phase.getEffectPhase() != null && phase.getEffectPhase().getResource() == card) {
                continue;
            }
            CardComponent cardComponent = new CardComponent(card, phase.getSelectedResources().contains(card));
            cardComponent.addActionListener(
                    e -> EventManager.getInstance().notifying(EventType.PlayCards, new PlayCardEvent(card)));
            this.add(cardComponent);
        }
    }
}
