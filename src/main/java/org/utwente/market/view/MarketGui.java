package org.utwente.market.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import javax.swing.*;
import javax.swing.border.*;

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;

import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.CardHelper;
import org.utwente.market.view.gui.GridCoordinate;
import org.utwente.market.view.gui.MarketConfig;

import java.util.*;

public class MarketGui extends JFrame implements MarketView {

  // JFrame
  static JFrame f;

  // Label to display text
  static JLabel l;

  JPanel panel;

  GridCoordinate coord = new GridCoordinate(0, 0);

  ActionListener onOrder;

  Market market;

  @Override
  public Order getOrder() {
    throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
  }

  private void addSuccessMessage(CardType card) {
    GridBagConstraints c = coord.toGridBagConstraints(4);

    ImageIcon scaledIcon = CardHelper.getImageIcon(card, new Dimension(140, 200));
    JLabel label = new JLabel(
        scaledIcon, SwingConstants.CENTER);

    coord.nextRow();
    c = coord.toGridBagConstraints(4);
    panel.add(label, c);

    coord.nextRow();
    c = coord.toGridBagConstraints(4);

    JLabel msg = new JLabel(
        "Congratulations on successfully purchasing: " + card.name(), SwingConstants.CENTER);
    msg.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    msg.setFont(MarketConfig.MARKET_CARD_DESCRIPTION);
    msg.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    panel.add(msg, c);

    addBackButton();
  }

  @Override
  public void displayPurchaseResult(Card card) {
    resetCoordinates();
    panel.removeAll();

    addSuccessMessage(card.getCardType());

    panel.revalidate();
    panel.repaint();
  }

  private void resetCoordinates() {
    coord = new GridCoordinate(0, 0); // Reset coordinates
  }

  @Override
  public void displayMarket() {
    panel.removeAll(); // Clear existing components
    resetCoordinates();

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

  private void addErrorMessage(String errorMessage) {
    GridBagConstraints c = coord.toGridBagConstraints(4);

    l = new JLabel(errorMessage);
    l.setFont(MarketConfig.MARKET_CARD_NAME);
    l.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    l.setBorder(
        BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, MarketConfig.MARKET_ERROR_BG),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    panel.add(l, c);
    addBackButton();
  }

  private void addBackButton() {
    this.coord.nextRow();
    GridBagConstraints c = coord.toGridBagConstraints(4);
    JButton confirm = new JButton("< Go Back");
    confirm.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        displayMarket();
      }

    });
    panel.add(confirm, c);
  }

  @Override
  public void displayError(String errorMessage) {
    resetCoordinates();
    panel.removeAll();

    addMainTitle(4);
    addErrorMessage(errorMessage);
    // Refresh the panel to reflect changes
    panel.revalidate();
    panel.repaint();
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

  private void addSubtitle(int width) {
    JLabel subtitle = new JLabel("What would you like to buy today?");
    subtitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
    subtitle.setFont(MarketConfig.MARKET_CARD_FONT);
    subtitle.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);

    var c = coord.toGridBagConstraints(width);
    panel.add(subtitle, c);
  }

  private void addMainTitle(int width) {
    GridBagConstraints c = coord.toGridBagConstraints(width);
    coord.nextRow();

    l = new JLabel("Welcome to el Dorado Market.");
    l.setFont(MarketConfig.MARKET_TITLE);
    l.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    panel.add(l, c);
  }

  public void addTitle() {
    addMainTitle(3);
    addSubtitle(3);
  }

  private void addReserveText() {
    GridBagConstraints c = coord.toGridBagConstraints(3);
    JLabel subtitle = new JLabel("These are the reserve cards:");
    subtitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
    subtitle.setFont(MarketConfig.MARKET_CARD_FONT);
    subtitle.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    panel.add(subtitle, c);
  }

  public void setBackground(JComponent panel) {
    panel.setBackground(new Color(24, 24, 27));
  }

  public void addCard(CardType card, GridCoordinate coord) {
    CardComponent cardComponent = new CardComponent(card, market.getRemainingAmount(card));

    GridBagConstraints c = coord.toGridBagConstraints(1);

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
