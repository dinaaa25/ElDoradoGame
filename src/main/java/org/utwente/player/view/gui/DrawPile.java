package org.utwente.player.view.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.CardHelper;

public class DrawPile extends JPanel {
  public DrawPile() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    ImageIcon icon = CardHelper.getImageIcon("back", new Dimension(CardComponent.WIDTH, CardComponent.HEIGHT));
    this.add(new JLabel(icon));
    this.add(new JLabel("Draw Pile"));
  }
}
