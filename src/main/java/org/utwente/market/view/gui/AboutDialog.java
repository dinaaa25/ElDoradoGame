package org.utwente.market.view.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import org.utwente.market.model.CardType;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class AboutDialog extends JDialog {
  CardType card;
  Container contentPane;

  public AboutDialog(JFrame parent) {
    super(parent, "AboutDialog", false); // Non-modal

    contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());

    setSize(150, 300);
  }

  public void setCard(CardType card) {
    this.card = card;
  }

  public void display() {

    if (card != null) {
      JLabel label = new JLabel(
          "Successfully Purchased" + card.name(), SwingConstants.CENTER);
      contentPane.add(label);

      ImageIcon icon = new ImageIcon(
          String.format("/Users/mark/Projects/master-cs/set2024team03project/src/main/resources/%s.png",
              card.name().toLowerCase()));
      Image image = icon.getImage();
      Image scaledImage = image.getScaledInstance(140, 200, Image.SCALE_SMOOTH);
      ImageIcon scaledIcon = new ImageIcon(scaledImage);
      label = new JLabel(
          scaledIcon, SwingConstants.CENTER);
      contentPane.add(label);
    } else {
      JLabel label = new JLabel(
          "Failed to buy", SwingConstants.CENTER);
      contentPane.add(label);
    }
  }
}
