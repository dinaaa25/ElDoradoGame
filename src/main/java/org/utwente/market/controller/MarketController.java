package org.utwente.market.controller;

import org.utwente.market.exceptions.BuyException;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import org.utwente.market.view.MarketView;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import java.util.function.*;

public class MarketController {
  private final MarketView view;
  private final Market model;

  public MarketController(MarketView view, Market model) {
    this.view = view;
    this.model = model;

    EventManager manager = EventManager.getInstance();
    manager.subscribe(new Consumer<String>() {
      @Override
      public void accept(String data) {
        try {
          Card card = model.buy(new Order(CardType.valueOf(data), 3));
          view.displayPurchaseResult(card);
          view.setMarket(model);
        } catch (BuyException exception) {
          view.displayError(exception.getMessage());
        } catch (IllegalArgumentException exception) {
          view.displayError(String.format("%s is not a card in the game.", data));
        } catch (NullPointerException exception) {
          view.displayError(exception.getMessage());
        }
      }
    }, EventType.BuyCards);
    this.view.setMarket(model);
  }

}
