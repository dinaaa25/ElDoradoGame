package org.utwente.player.view.gui;

import javax.swing.*;
import java.awt.*;

import org.utwente.market.view.gui.CardComponent;
import org.utwente.player.model.Player;

public class DiscardPanel extends JPanel {
    private static final int DISCARD_PILE_WIDTH = CardComponent.BASE_WIDTH + 20; // Adjusted width for the discard pile

    public DiscardPanel(Player player) {
        super(new BorderLayout());

        // Panel for DiscardPile with fixed width
        JPanel discardPilePanel = new JPanel();
        discardPilePanel.setLayout(new BorderLayout());
        discardPilePanel.setPreferredSize(new Dimension(DISCARD_PILE_WIDTH, CardComponent.BASE_HEIGHT));
        int sizeOfDiscardPile = player.getDiscardPile().getCards().size();
        discardPilePanel.add(new DiscardCard(sizeOfDiscardPile), BorderLayout.CENTER);

        // Panel for FaceUpDiscard
        JPanel faceUpDiscardPanel = new JPanel();
        faceUpDiscardPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        faceUpDiscardPanel.add(new FaceUpDiscard(player.getFaceUpDiscardPile()));

        // Add panels to the main panel
        this.add(faceUpDiscardPanel, BorderLayout.CENTER);
        this.add(discardPilePanel, BorderLayout.EAST);
    }
}
