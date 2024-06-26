package org.utwente.market.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.CardHelper;
import org.utwente.market.view.gui.GridCoordinate;
import org.utwente.market.view.gui.MarketConfig;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.market.controller.BuyEvent;

import java.util.*;

public class MarketGui implements MarketView {
  JFrame f;
  JPanel panel;
  JScrollPane scrollPane;
  final int maxColumns = 3;
  GridCoordinate coord = new GridCoordinate(0, 0, maxColumns);
  Market market;
  boolean missingDrawCards;

  public MarketGui() {
    panel = new JPanel(new GridBagLayout());
    panel.setBorder(new EmptyBorder(20, 20, 20, 20));
    scrollPane = new JScrollPane(panel);
    addTitle();
    coord.nextRow();
    missingDrawCards = true;
  }

  private void addSuccessMessage(CardType card) {
    GridBagConstraints c = coord.toGridBagConstraints(maxColumns);

    ImageIcon scaledIcon = CardHelper.getImageIcon(card, new Dimension(140, 200));
    JLabel label = new JLabel(
        scaledIcon, SwingConstants.CENTER);

    coord.nextRow();
    c = coord.toGridBagConstraints(maxColumns);
    panel.add(label, c);

    coord.nextRow();
    c = coord.toGridBagConstraints(maxColumns);

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
    missingDrawCards = false;
    resetCoordinates();
    panel.removeAll();

    addSuccessMessage(card.getCardType());

    panel.revalidate();
    panel.repaint();
  }

  private void resetCoordinates() {
    coord = new GridCoordinate(0, 0, maxColumns); // Reset coordinates
  }

  private void addCards() {
    if (market != null) {
      addCardsToGrid(market.getCurrent());

      coord.nextRow();
      addReserveText();
      coord.nextRow();

      addCardsToGrid(market.getReserve());
    }
  }

  @Override
  public void displayMarket() {
    panel.removeAll(); // Clear existing components
    resetCoordinates();

    // Re-add the title and subtitle
    addTitle();
    coord.nextRow();

    this.addCards();

    // Refresh the panel to reflect changes
    panel.revalidate();
    panel.repaint();
  }

  private void addErrorMessage(String errorMessage) {
    System.out.println("displaying error.");
    GridBagConstraints c = coord.toGridBagConstraints(maxColumns);

    JLabel l = new JLabel(errorMessage);
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
    GridBagConstraints c = coord.toGridBagConstraints(maxColumns);
    JButton confirm = new JButton("◄ Go Back");
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
    missingDrawCards = false;
    resetCoordinates();
    panel.removeAll();

    addMainTitle(maxColumns);
    addErrorMessage(errorMessage);
    // Refresh the panel to reflect changes
    panel.revalidate();
    panel.repaint();
  }

  @Override
  public void setMarket(Market market) {
    this.market = market;

    if (missingDrawCards) {
      this.addCards();
    }
  }

  @Override
  public void exit() {
    f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
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

    JLabel l = new JLabel("Welcome to el Dorado Market.");
    l.setFont(MarketConfig.MARKET_TITLE);
    l.setForeground(MarketConfig.MARKET_TEXT_COLOR);
    panel.add(l, c);
  }

  public void addTitle() {
    addMainTitle(maxColumns - 1);
    addSubtitle(maxColumns - 1);
  }

  private void addReserveText() {
    GridBagConstraints c = coord.toGridBagConstraints(maxColumns - 1);
    JLabel subtitle = new JLabel("These are the reserve resources:");
    subtitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
    subtitle.setFont(MarketConfig.MARKET_CARD_FONT);
    subtitle.setForeground(MarketConfig.MARKET_TEXT_SECONDARY);
    panel.add(subtitle, c);
  }

  public void setBackground(JComponent panel) {
    panel.setBackground(new Color(24, 24, 27));
  }

  public void addCard(CardType cardType, GridCoordinate coord) {
    CardComponent cardComponent = new CardComponent(new Card(cardType), market.getRemainingAmount(cardType), null);
    GridBagConstraints c = coord.toGridBagConstraints(1);

    cardComponent.setActionCommand(cardType.name());
    cardComponent.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        EventManager.getInstance().notifying(EventType.BuyCards, new BuyEvent(cardType));
      }

    });

    panel.add(cardComponent, c);
  }

  private void addCardsToGrid(List<CardType> cards) {
    for (CardType card : cards) {
      addCard(card, coord);
      coord.nextColumn();
    }
  }

  public JScrollPane getMainComponent() {
    return this.scrollPane;
  }

  public void run() {
    f = new JFrame("Market");
    f.add(getMainComponent());
    f.setSize(1000, 800);
    f.setVisible(true);
  }

}