package org.utwente.market.view;

import org.utwente.market.model.Card;
import org.utwente.market.model.Market;
import java.util.function.*;

public interface MarketView {
  void displayPurchaseResult(Card card);

  void displayMarket();

  void displayError(String errorMessage);

  void setMarket(Market market);

  void exit();

  void run();
}
