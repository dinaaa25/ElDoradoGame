package org.utwente.player.view.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.CardHelper;

public class DiscardCard extends JPanel {
  public DiscardCard() {
    ImageIcon icon = CardHelper.getImageIcon("back", new Dimension(CardComponent.WIDTH, CardComponent.HEIGHT));
    this.add(new JLabel(icon));
  }
}
