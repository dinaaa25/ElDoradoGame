package org.utwente.player.view.gui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.CardHelper;

public class DrawPile extends JPanel {
  private static final int ICON_OFFSET = 10;

  public DrawPile(int sizeOfDrawPile) {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    int cardWidth = CardComponent.BASE_WIDTH;
    int cardHeight = CardComponent.BASE_HEIGHT;

    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setPreferredSize(new Dimension(cardWidth + ICON_OFFSET * (sizeOfDrawPile - 1), cardHeight));


    ImageIcon icon = CardHelper.getImageIcon("back", new Dimension(cardWidth, cardHeight));
    if (icon == null) {
      return;
    } else {
    }

    for (int i = 0; i < sizeOfDrawPile; i++) {
      JLabel iconLabel = new JLabel(icon);
      iconLabel.setBounds(i * ICON_OFFSET, 0, cardWidth, cardHeight);
      layeredPane.add(iconLabel, Integer.valueOf(i));
    }

    this.add(layeredPane);
    this.add(new JLabel("Draw Pile"));
    this.add(new JLabel("Cards: " + sizeOfDrawPile));

    this.revalidate();
    this.repaint();
  }
}
