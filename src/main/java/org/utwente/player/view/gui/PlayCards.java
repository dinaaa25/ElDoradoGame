package org.utwente.player.view.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.utwente.game.model.Phase;
import org.utwente.market.model.Card;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.player.model.CardPile;

public class PlayCards extends JPanel {

    public PlayCards(CardPile pile, Phase phase) {
        super(new FlowLayout());
        for (Card card : pile.getResources()) {
            this.add(new CardComponent(card, phase.getSelectedResources().contains(card)));
        }
    }
}
