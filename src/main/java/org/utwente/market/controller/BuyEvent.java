package org.utwente.market.controller;

import org.utwente.market.model.CardType;
import org.utwente.util.event.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyEvent extends Event {
  CardType cardType;

  public BuyEvent(CardType cardType) {
    this.cardType = cardType;
  }
}
