package org.utwente.market.view;

import org.utwente.market.model.Card;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import java.awt.event.*;

public interface MarketView {
  Order getOrder();

  void displayPurchaseResult(Card card);

  void displayMarket();

  void displayError(String errorMessage);

  void setMarket(Market market);

  void setOnOrder(ActionListener eventHandler);

  void setOnInput(ActionListener eventHandler);

  void exit();
}
