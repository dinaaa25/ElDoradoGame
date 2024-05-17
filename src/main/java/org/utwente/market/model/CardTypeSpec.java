package org.utwente.market.model;

import lombok.Getter;

@Getter
public class CardTypeSpec {
  private int quantity;
  private CardType type;

  public CardTypeSpec(int quantity, CardType type) {
    this.quantity = quantity;
    this.type = type;
  }

  public CardTypeSpec(CardType type) {
    this(5, type);
  }
}
