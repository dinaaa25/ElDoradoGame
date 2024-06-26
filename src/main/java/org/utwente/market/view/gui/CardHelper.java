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
    if (url == null) {
      System.err.println("Image not found: images/cards/" + card + ".png");
      return null;
    }
    return new ImageIcon(url);
  }

  public static ImageIcon getImageIcon(String name, Dimension dimension) {
    ImageIcon icon = getImageIcon(name);
    if (icon == null) {
      System.err.println("Failed to load icon for: " + name);
      return null;
    }
    return scaleIcon(icon, dimension);
  }

  public static ImageIcon getImageIcon(CardType card, Dimension dimension) {
    ImageIcon icon = getImageIcon(card.name().toLowerCase());
    if (icon == null) {
      System.err.println("Failed to load icon for card: " + card.name().toLowerCase());
      return null;
    }
    return scaleIcon(icon, dimension);
  }

  private static ImageIcon scaleIcon(ImageIcon icon, Dimension dimension) {
    if (icon == null) {
      System.err.println("Cannot scale null icon");
      return null;
    }
    Image image = icon.getImage();
    Image scaledImage = image.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH);
    if (scaledImage == null) {
      System.err.println("Failed to scale image");
      return null;
    }
    return new ImageIcon(scaledImage);
  }
}
