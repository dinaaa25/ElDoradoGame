package org.utwente.market.model;

public enum CardType {
  Kundeschafter(4, 1, PowerType.Machete, false),
  Forscher(1, null, PowerType.Machete, false),
  Entdecker(3, 3, PowerType.Machete, false),
  Pionier(5, 5, PowerType.Machete, false),
  MachtigeMachete(6, 3, PowerType.Machete, true),
  Kapitan(3, 2, PowerType.Paddle, false),
  Matrose(1, null, PowerType.Paddle, false),
  Reisende(1, null, PowerType.Coin, false),
  Fotografin(2, 2, PowerType.Coin, false),
  Schatztruhe(4, 3, PowerType.Coin, true),
  Millionarin(4, 5, PowerType.Coin, false),
  Journalistin(3, 3, PowerType.Coin, false),
  Tausendsassa(1, 2, PowerType.Wild, false),
  Abenteurerin(2, 4, PowerType.Wild, false),
  Propellerflugzeug(4, 4, PowerType.Wild, true),
  Kartograph(2, 4, PowerType.Effect, false),
  Kompass(3, 2, PowerType.Effect, true),
  Wissenschaftlerin(1, 4, PowerType.Effect, true),
  Ureinwohner(1, 5, PowerType.Effect, false),
  Fernsprechgerat(1, 4, PowerType.Effect, true),
  Reisetagebuch(2, 3, PowerType.Effect, true);

  public Integer power;
  public Integer purchaseValue;
  public PowerType powerType;
  public boolean oneTimeUse;

  private CardType(Integer power, Integer purchaseValue, PowerType powerType, boolean oneTimeUse) {
    this.power = power;
    this.purchaseValue = purchaseValue;
    this.powerType = powerType;
    this.oneTimeUse = oneTimeUse;
  }
}
