package org.utwente.market.view.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import org.utwente.market.model.CardType;

public class CardComponent extends JButton {
  public final int HEIGHT = 230;
  public final int WIDTH = 150;

  public CardComponent(CardType card) {
    super();
    Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, MarketConfig.MARKET_BORDER,
        MarketConfig.MARKET_SHADOW);
    EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10); // Adjust the gap values as needed
    CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, bevelBorder);

    this.setBorder(compoundBorder);
    ImageIcon scaledIcon = CardHelper.getImageIcon(card, new Dimension(WIDTH, HEIGHT - 30));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setForeground(MarketConfig.MARKET_TEXT_COLOR);

    JLabel name = new JLabel(card.name());
    name.setFont(MarketConfig.MARKET_CARD_NAME);
    name.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    this.add(name);

    JLabel price = new JLabel(String.format("Price: %d coins", card.purchaseValue));
    price.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    price.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    this.add(price);

    JLabel power = new JLabel(String.format("Power: %d", card.power));
    power.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    power.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    this.add(power);

    this.add(new JLabel(scaledIcon));
    this.setHorizontalTextPosition(JButton.CENTER);
    this.setVerticalTextPosition(JButton.BOTTOM);
  }
}
