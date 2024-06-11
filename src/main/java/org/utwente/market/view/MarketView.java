package org.utwente.market.view;

import org.utwente.market.model.Card;
import org.utwente.market.model.Market;
import java.util.function.*;
import org.utwente.market.model.OrderEvent;

public interface MarketView {
  void displayPurchaseResult(Card card);

  void displayMarket();

  void displayError(String errorMessage);

  void setMarket(Market market);

  void setOnOrder(Consumer<String> eventHandler);

  void exit();

  void run();
}
