package org.utwente.market.view.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import org.utwente.market.model.CardType;

public class CardHelper {
  public static ImageIcon getImageIcon(CardType card) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    URL url = classloader.getResource(String.format("images/%s.png", card.name().toLowerCase()));
    return new ImageIcon(url);
  }

  public static ImageIcon getImageIcon(CardType card, Dimension dimension) {
    ImageIcon icon = getImageIcon(card);
    Image image = icon.getImage();
    Image scaledImage = image.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(),
        Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    return scaledIcon;
  }
}
