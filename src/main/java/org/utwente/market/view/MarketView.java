package org.utwente.market.view;

import org.utwente.market.controller.InputEvent;
import org.utwente.market.controller.MarketOrderEvent;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import org.utwente.util.EventHandler;

public interface MarketView {
  Order getOrder();

  void displayPurchaseResult();

  void displayMarket();

  void displayError(String errorMessage);

  void setMarket(Market market);

  void setOnOrder(EventHandler<MarketOrderEvent> eventHandler);

  void setOnInput(EventHandler<InputEvent> eventHandler);

  void exit();
}
