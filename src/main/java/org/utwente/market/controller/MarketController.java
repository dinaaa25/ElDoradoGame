package org.utwente.market.controller;

import org.utwente.market.model.Market;
import org.utwente.market.view.MarketView;
import org.utwente.util.EventHandler;

public class MarketController {
  private MarketView view;
  private Market model;

  public MarketController(MarketView view, Market model) {
    this.view = view;
    this.model = model;
    this.view.setMarket(model);
    this.view.setOnOrder(new EventHandler<MarketOrderEvent>() {
      @Override
      public void handle(MarketOrderEvent event) {
        System.out.println("from controller " + event.toString());
      }
    });
    this.view.setOnInput(new EventHandler<InputEvent>() {
      @Override
      public void handle(InputEvent event) {
        if (event.getInput().equals("exit")) {
          view.exit();
        }
        if (event.getInput().equals("show")) {
          view.displayMarket();
        }
      }
    });
  }

}
