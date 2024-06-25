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
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setPreferredSize(new Dimension(CardComponent.WIDTH + ICON_OFFSET * sizeOfDiscardPile, CardComponent.HEIGHT));

    ImageIcon icon = CardHelper.getImageIcon("back", new Dimension(CardComponent.WIDTH, CardComponent.HEIGHT));

    for (int i = 0; i < sizeOfDiscardPile; i++) {
      JLabel iconLabel = new JLabel(icon);
      iconLabel.setBounds(-i * ICON_OFFSET, 0, icon.getIconWidth(), icon.getIconHeight());
      layeredPane.add(iconLabel, Integer.valueOf(i));
    }

    this.add(layeredPane);
    this.add(new JLabel("Discard Pile"));
    this.add(new JLabel("Cards: " + sizeOfDiscardPile));
  }
}
