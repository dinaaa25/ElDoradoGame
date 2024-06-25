package org.utwente.player.view.gui;

import java.awt.*;
import javax.swing.*;

import org.utwente.market.view.gui.CardComponent;
import org.utwente.player.model.CardPile;
import org.utwente.market.model.Card;

public class FaceUpDiscard extends JPanel {
    private static final double SCALE_FACTOR = 0.5;

    public FaceUpDiscard(CardPile faceUpDiscardPile) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking

        JPanel cardsPanel = new JPanel(new FlowLayout()); // Panel to hold the cards
        for (Card card : faceUpDiscardPile.getCards()) {
            cardsPanel.add(new CardComponent(card, SCALE_FACTOR));
        }

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JLabel discardLabel = new JLabel("To be discarded");
        JLabel cardCountLabel = new JLabel("Cards: " + faceUpDiscardPile.getCards().size());
        textPanel.add(cardCountLabel);
        textPanel.add(discardLabel);

        this.add(cardsPanel);
        //this.add(cardCountLabel);
    }
}

