package org.utwente.market;

import org.junit.jupiter.api.BeforeEach;
import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketCli;
import org.utwente.market.view.MarketView;

public class MarketControllerTest {
  MarketController controller;

  @BeforeEach
  public void setup() {
    MarketView view = new MarketCli();
    Market market = new Market();
    controller = new MarketController(view, market);
  }

}
