package org.utwente.market.controller;

import org.utwente.market.model.Market;
import org.utwente.market.view.MarketView;

public class MarketController {
  private final MarketView view;
  private final Market model;

  public MarketController(MarketView view, Market model) {
    this.view = view;
    this.model = model;

    // EventManager manager = EventManager.getInstance();
    // manager.subscribe(new Consumer<Event>() {
    // @Override
    // public void accept(Event event) {
    // if (event instanceof BuyEvent) {
    // BuyEvent buyEvent = (BuyEvent) event;
    // try {
    // Card card = model.buy(new Order(buyEvent.getCardType(), 3));
    // view.displayPurchaseResult(card);
    // view.setMarket(model);
    // } catch (BuyException exception) {
    // view.displayError(exception.getMessage());
    // } catch (IllegalArgumentException exception) {
    // view.displayError(String.format("%s is not a card in the game.",
    // buyEvent.getCardType()));
    // } catch (NullPointerException exception) {
    // view.displayError(exception.getMessage());
    // }
    // }
    // }
    // }, EventType.BuyCards);
    this.view.setMarket(model);
  }
}