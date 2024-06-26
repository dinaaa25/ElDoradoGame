package org.utwente.market.view.gui;

import javax.swing.*;
        import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import org.utwente.market.model.Card;

public class CardComponent extends JButton {
  public static final int BASE_HEIGHT = 230;
  public static final int BASE_WIDTH = 150;
  private final Card card;
  private boolean selected;
  private double scaleFactor;
  private Object cardType;

  public CardComponent(Card card) {
    this(card, null, card.remainingPower(), false, 1.0);
  }

  public CardComponent(Card card, boolean selected) {
    this(card, null, card.remainingPower(), selected, 1.0);
  }

  public CardComponent(Card card, Integer remainingAmount, Integer remainingPower) {
    this(card, remainingAmount, remainingPower, false, 1.0);
  }

  public CardComponent(Card card, Integer remainingAmount, Integer remainingPower, boolean selected) {
    this(card, remainingAmount, remainingPower, selected, 1.0);
  }

  public CardComponent(Card card, double scaleFactor) {
    this(card, null, card.remainingPower(), false, scaleFactor);
  }

  public CardComponent(Card card, Integer remainingAmount, Integer remainingPower, boolean selected, double scaleFactor) {
    super();
    this.card = card;
    this.cardType = card.getCardType();
    this.selected = selected;
    this.scaleFactor = scaleFactor;
    setup();
    addBorder();
    addName();
    if (remainingAmount != null) {
      addRemaining(remainingAmount, true);
    } else {
      addValue();
    }
    if (remainingAmount == null) {
      addRemaining(remainingPower, false);
    }
    addPicture();
  }

  public void setup() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    this.setHorizontalTextPosition(JButton.CENTER);
    this.setVerticalTextPosition(JButton.BOTTOM);
    this.setPreferredSize(new Dimension((int)(BASE_WIDTH * scaleFactor), (int)(BASE_HEIGHT * scaleFactor)));
  }

  public void addPicture() {
    ImageIcon scaledIcon = CardHelper.getImageIcon(card.getCardType(),
            new Dimension((int)(BASE_WIDTH * scaleFactor), (int)(BASE_HEIGHT * scaleFactor - 30)));
    this.add(new JLabel(scaledIcon));
  }

  public void addPrice() {
    JLabel price = new JLabel(String.format("Price: %d coins", card.getCardType().purchaseValue));
    price.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    price.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    this.add(price);
  }

  public void addValue() {
    JLabel value = new JLabel(String.format("Market value: %.1f coins", card.getValue()));
    value.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    value.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    this.add(value);
  }

  public void addPower() {
    JLabel power = new JLabel(String.format("Power: %d", card.getCardType().power));
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
    Border bevelBorder = BorderFactory.createMatteBorder(2, 2, 2, 2,
            selected ? MarketConfig.SELECTED_MARKET_BORDER : MarketConfig.MARKET_BORDER);
    EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10); // Adjust the gap values as needed
    CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, bevelBorder);

    this.setBorder(compoundBorder);
  }

  public void addName() {
    JLabel name = new JLabel(this.card.getCardType().name());
    name.setFont(MarketConfig.MARKET_CARD_NAME);
    name.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    this.add(name);
  }

  public Object getCardType() {
    return cardType;
  }
}
