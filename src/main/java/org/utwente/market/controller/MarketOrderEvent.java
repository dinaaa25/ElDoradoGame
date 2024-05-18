package org.utwente.market.controller;

import org.utwente.market.model.Order;
import org.utwente.util.Event;

import lombok.*;

@Getter
@Setter
public class MarketOrderEvent implements Event {
  private Order order;

  public MarketOrderEvent(Order order) {
    this.order = order;
  }

}
