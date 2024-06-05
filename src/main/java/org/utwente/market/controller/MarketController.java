package org.utwente.market.controller;

import org.utwente.market.exceptions.BuyException;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import org.utwente.market.view.MarketView;

import java.awt.event.*;

public class MarketController {
  private final MarketView view;
  private final Market model;

  public MarketController(MarketView view, Market model) {
    this.view = view;
    this.model = model;
    this.view.setMarket(model);
    this.view.setOnOrder(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Card card = model.buy(new Order(CardType.valueOf(e.getActionCommand()), 3));
          view.displayPurchaseResult(card);
          view.setMarket(model);
          view.displayMarket();
        } catch (BuyException exception) {
          view.displayError(exception.getMessage());
        } catch (IllegalArgumentException exception) {
          view.displayError(exception.getMessage());
        } catch (NullPointerException exception) {
          view.displayError(exception.getMessage());
        }
      }

    });
  }

}
