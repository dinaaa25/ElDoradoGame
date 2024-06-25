package org.utwente.player.view.gui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.CardHelper;

public class DiscardCard extends JPanel {
  private static final int ICON_OFFSET = 10;

  public DiscardCard(int sizeOfDiscardPile) {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    int cardWidth = CardComponent.BASE_WIDTH;
    int cardHeight = CardComponent.BASE_HEIGHT;

    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setPreferredSize(new Dimension(cardWidth + ICON_OFFSET * (sizeOfDiscardPile - 1), cardHeight));


    // Add background icon when empty also ensures minimum height for playerDeck regardless if we have cards or not
    ImageIcon iconBackground = CardHelper.getImageIcon("backgrey", new Dimension(cardWidth, cardHeight));

      JLabel backgroundLabel = new JLabel(iconBackground);
      backgroundLabel.setBounds(0, 0, cardWidth, cardHeight);
      layeredPane.add(backgroundLabel, Integer.valueOf(-1));
      System.out.println("Background icon added successfully");

    // Add the normal icons on top
    ImageIcon icon = CardHelper.getImageIcon("back", new Dimension(cardWidth, cardHeight));

      for (int i = 0; i < sizeOfDiscardPile; i++) {
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(i * ICON_OFFSET, 0, cardWidth, cardHeight);
        layeredPane.add(iconLabel, Integer.valueOf(i));
//        System.out.println(String.format("Added card icon at layer %d with bounds (%d, %d, %d, %d)", i, i * ICON_OFFSET, 0, cardWidth, cardHeight));
      }


    this.add(layeredPane);
    this.add(new JLabel("Discard Pile"));
    this.add(new JLabel("Cards: " + sizeOfDiscardPile));

    this.revalidate();
    this.repaint();
  }
}
