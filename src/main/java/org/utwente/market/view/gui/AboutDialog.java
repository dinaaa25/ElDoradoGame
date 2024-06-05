package org.utwente.market.view.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.utwente.market.model.CardType;

import java.awt.Dimension;
import javax.swing.ImageIcon;

public class AboutDialog extends JDialog {
  CardType card;
  Container contentPane;
  final int HEIGHT = 350;
  final int WIDTH = 300;

  public AboutDialog(JFrame parent) {
    super(parent, "AboutDialog", false);

    contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());

    setSize(WIDTH, HEIGHT);
  }

  public void setCard(CardType card) {
    this.card = card;
  }

  public void display() {

    if (card != null) {
      JLabel label = new JLabel(
          "Successfully Purchased: " + card.name(), SwingConstants.CENTER);
      contentPane.add(label);
      ImageIcon scaledIcon = CardHelper.getImageIcon(card, new Dimension(140, 200));
      label = new JLabel(
          scaledIcon, SwingConstants.CENTER);
      contentPane.add(label);
    } else {
      JLabel label = new JLabel(
          "Failed to buy", SwingConstants.CENTER);
      contentPane.add(label);
    }
    this.setVisible(true);
  }
}
