package org.utwente.market.view.gui;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import org.utwente.market.model.CardType;

public class CardComponent extends JButton {
  public final int HEIGHT = 230;
  public final int WIDTH = 150;

  public CardComponent(CardType card) {
    super(card.name() + "\n" + "$" + card.purchaseValue);
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    URL url = classloader.getResource(String.format("images/%s.png", card.name().toLowerCase()));
    ImageIcon icon = new ImageIcon(url);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    Image image = icon.getImage();
    Image scaledImage = image.getScaledInstance(WIDTH, HEIGHT - 30, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    Font titleFont = new Font("Sans", Font.BOLD, 18);
    this.setIcon(scaledIcon);
    this.setFont(titleFont);
    this.setForeground(new Color(241, 245, 249));
    this.setHorizontalTextPosition(JButton.CENTER);
    this.setVerticalTextPosition(JButton.BOTTOM);
    this.setContentAreaFilled(false);
    this.setBorderPainted(false);
    this.setFocusPainted(false);
    this.setOpaque(false);
    this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
  }
}
