package org.utwente.market.model;

public enum CardType {
  Kundeschafter(2, 1, PowerType.Machete),
  Forscher(1, null, PowerType.Machete),
  Entdecker(3, 3, PowerType.Machete),
  Pionier(5, 5, PowerType.Machete),
  MachtigeMachete(6, 3, PowerType.Machete, true, null),
  Kapitan(3, 2, PowerType.Paddle),
  Matrose(1, null, PowerType.Paddle),
  Reisende(1, null, PowerType.Coin),
  Fotografin(2, 2, PowerType.Coin),
  Schatztruhe(4, 3, PowerType.Coin, true, null),
  Millionarin(4, 5, PowerType.Coin),
  Journalistin(3, 3, PowerType.Coin),
  Tausendsassa(1, 2, PowerType.Wild),
  Abenteurerin(2, 4, PowerType.Wild),
  Propellerflugzeug(4, 4, PowerType.Wild, true, null),
  Kartograph(2, 4, PowerType.Effect, false, 2),
  Kompass(3, 2, PowerType.Effect, true, 3),
  Wissenschaftlerin(1, 4, PowerType.Effect, false, 1),
  Ureinwohner(1, 5, PowerType.Effect),
  Fernsprechgerat(1, 4, PowerType.Effect, true, null),
  Reisetagebuch(2, 3, PowerType.Effect, true, 2);

  public Integer power;
  public Integer purchaseValue;
  public PowerType powerType;
  public boolean oneTimeUse;
  public Integer discardAmount;

  private CardType(Integer power, Integer purchaseValue, PowerType powerType, boolean oneTimeUse,
      Integer discardAmount) {
    this.power = power;
    this.purchaseValue = purchaseValue;
    this.powerType = powerType;
    this.oneTimeUse = oneTimeUse;
    this.discardAmount = discardAmount;
  }

  private CardType(Integer power, Integer purchaseValue, PowerType powerType) {
    this(power, purchaseValue, powerType, false, null);
  }
}
