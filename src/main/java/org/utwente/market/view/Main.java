package org.utwente.market.view;

import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;

public class Main {

  public static void main(String[] args) {
    MarketGui mGui = new MarketGui();
    Market market = new Market();
    MarketController marketController = new MarketController(mGui, market);
    mGui.run();
  }
}
