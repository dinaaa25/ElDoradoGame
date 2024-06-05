package org.utwente.market.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import org.utwente.market.view.gui.AboutDialog;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.ErrorDialog;
import org.utwente.market.view.gui.GridCoordinate;
import org.utwente.market.view.gui.MarketConfig;

import java.util.*;

public class MarketGui extends JFrame implements MarketView {

  // JFrame
  static JFrame f;

  // JButton
  static JButton b, b1, b2, b3;

  // Label to display text
  static JLabel l;

  JPanel panel;

  GridCoordinate coord = new GridCoordinate(0, 0);

  ActionListener onOrder;

  Market market;

  @Override
  public Order getOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
  }

  @Override
  public void displayPurchaseResult(Card card) {
    AboutDialog dialog = new AboutDialog(this);
    dialog.setCard(card.getCardType());
    dialog.display();
  }

  @Override
  public void displayMarket() {
    panel.removeAll(); // Clear existing components
    coord = new GridCoordinate(0, 0); // Reset coordinates

    // Re-add the title and subtitle
    addTitle();
    coord.nextRow();

    // Add current market cards
    addCardsToGrid(market.getCurrent());
    coord.nextRow();

    // Add reserve section and cards
    addReserveText();
    coord.nextRow();
    addCardsToGrid(market.getReserve());

    // Refresh the panel to reflect changes
    panel.revalidate();
    panel.repaint();
  }

  @Override
  public void displayError(String errorMessage) {
    ErrorDialog errorDialog = new ErrorDialog(f, errorMessage);
    errorDialog.display();
  }

  @Override
  public void setMarket(Market market) {
    this.market = market;
  }

  @Override
  public void setOnOrder(ActionListener eventHandler) {
    this.onOrder = eventHandler;
  }

  @Override
  public void setOnInput(ActionListener eventHandler) {
    throw new UnsupportedOperationException("Unimplemented method 'setOnInput'");
  }

  @Override
  public void exit() {

  }

  public void addTitle() {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = coord.x;
    c.gridy = coord.y;
    coord.nextRow();

    l = new JLabel("Welcome to el Dorado Market.");
    l.setFont(MarketConfig.MARKET_TITLE);
    l.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    panel.add(l, c);

    JLabel subtitle = new JLabel("What would you like to buy today?");
    subtitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
    subtitle.setFont(MarketConfig.MARKET_CARD_FONT);
    subtitle.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = coord.x;
    c.gridy = coord.y;
    panel.add(subtitle, c);
  }

  public void addReserveText() {
    GridBagConstraints c = new GridBagConstraints();
    JLabel subtitle = new JLabel("These are the reserve cards:");
    subtitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
    subtitle.setFont(MarketConfig.MARKET_CARD_FONT);
    subtitle.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = coord.x;
    c.gridy = coord.y;
    panel.add(subtitle, c);
  }

  public void setBackground(JComponent panel) {
    panel.setBackground(new Color(24, 24, 27));
  }

  public void addCard(CardType card, GridCoordinate coord) {
    CardComponent cardComponent = new CardComponent(card);

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.2;
    c.gridwidth = 1;
    c.gridx = coord.x;
    c.gridy = coord.y;

    cardComponent.setActionCommand(card.name());
    cardComponent.addActionListener(onOrder);

    panel.add(cardComponent, c);
  }

  private void addCardsToGrid(List<CardType> cards) {
    for (CardType card : cards) {
      addCard(card, coord);
      coord.nextColumn();
    }
  }

  public void run() {
    f = new JFrame("Market");
    panel = new JPanel(new GridBagLayout());
    panel.setBorder(new EmptyBorder(20, 20, 20, 20));
    addTitle();
    coord.nextRow();
    addCardsToGrid(market.getCurrent());
    coord.nextRow();
    addReserveText();
    coord.nextRow();
    addCardsToGrid(market.getReserve());
    JScrollPane scrollPane = new JScrollPane(panel);
    setBackground(panel);
    setBackground(scrollPane);
    f.add(scrollPane);
    f.setSize(1000, 800);
    f.setVisible(true);
  }

}
