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

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

public class CardComponent extends JButton {
  public static final int HEIGHT = 230;
  public static final int WIDTH = 150;
  private CardType cardType;

  public CardComponent(Card card) {
    this(card.getCardType(), null, card.remainingPower());
  }

  public CardComponent(CardType card, Integer remainingAmount, Integer remainingPower) {
    super();
    this.cardType = card;
    setup();
    addBorder();
    addName();
    addPrice();
    addPower();
    if (remainingAmount != null) {
      addRemaining(remainingAmount, true);
    } else {
      addRemaining(remainingPower, false);
    }
    addPicture();
  }

  public void setup() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    this.setHorizontalTextPosition(JButton.CENTER);
    this.setVerticalTextPosition(JButton.BOTTOM);
  }

  public void addPicture() {
    ImageIcon scaledIcon = CardHelper.getImageIcon(cardType, new Dimension(WIDTH, HEIGHT - 30));
    this.add(new JLabel(scaledIcon));
  }

  public void addPrice() {
    JLabel price = new JLabel(String.format("Price: %d coins", cardType.purchaseValue));
    price.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    price.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    this.add(price);
  }

  public void addPower() {
    JLabel power = new JLabel(String.format("Power: %d", cardType.power));
    power.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    power.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    this.add(power);
  }

  public void addRemaining(int remainingAmount, boolean remaining) {
    JLabel remainingLabel = new JLabel(
        String.format(remaining ? "Remaining: %d" : "Remaining Power: %d", remainingAmount));
    remainingLabel.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    remainingLabel.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    this.add(remainingLabel);
  }

  public void addBorder() {
    Border bevelBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, MarketConfig.MARKET_BORDER);
    EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10); // Adjust the gap values as needed
    CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, bevelBorder);

    this.setBorder(compoundBorder);
  }

  public void addName() {
    JLabel name = new JLabel(this.cardType.name());
    name.setFont(MarketConfig.MARKET_CARD_NAME);
    name.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    this.add(name);
  }
}
