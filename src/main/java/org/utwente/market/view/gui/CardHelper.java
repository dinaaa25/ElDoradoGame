package org.utwente.market.view.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import org.utwente.market.model.CardType;

public class CardHelper {
  public static ImageIcon getImageIcon(String card) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    URL url = classloader.getResource(String.format("images/cards/%s.png", card));
    return new ImageIcon(url);
  }

  public static ImageIcon getImageIcon(String name, Dimension dimension) {
    ImageIcon icon = getImageIcon(name);
    return scaleIcon(icon, dimension);
  }

  public static ImageIcon getImageIcon(CardType card, Dimension dimension) {
    ImageIcon icon = getImageIcon(card.name().toLowerCase());
    return scaleIcon(icon, dimension);
  }

  private static ImageIcon scaleIcon(ImageIcon icon, Dimension dimension) {
    Image image = icon.getImage();
    Image scaledImage = image.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(),
        Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    return scaledIcon;
  }
}
