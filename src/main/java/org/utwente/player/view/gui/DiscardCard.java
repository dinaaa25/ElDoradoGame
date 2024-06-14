package org.utwente.player.view.gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.CardHelper;

public class DiscardCard extends JPanel {
  public DiscardCard() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    ImageIcon icon = CardHelper.getImageIcon("back", new Dimension(CardComponent.WIDTH, CardComponent.HEIGHT));
    this.add(new JLabel(icon));
    this.add(new JLabel("Discard Pile"));

  }
}
