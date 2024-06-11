package org.utwente.market.model;

import java.util.*;

public enum MarketSetup {
  reserve(
      List.of(
          new CardTypeSpec(CardType.Kapitan),
          new CardTypeSpec(CardType.Millionarin),
          new CardTypeSpec(CardType.Pionier),
          new CardTypeSpec(CardType.Propellerflugzeug),
          new CardTypeSpec(CardType.Abenteurerin),
          new CardTypeSpec(CardType.MachtigeMachete),
          new CardTypeSpec(CardType.Ureinwohner),
          new CardTypeSpec(CardType.Kartograph),
          new CardTypeSpec(CardType.Kompass),
          new CardTypeSpec(CardType.Wissenschaftlerin),
          new CardTypeSpec(CardType.Journalistin))),
  active(
      List.of(
          new CardTypeSpec(CardType.Kundeschafter),
          new CardTypeSpec(CardType.Entdecker),
          new CardTypeSpec(CardType.Tausendsassa),
          new CardTypeSpec(CardType.Fotografin),
          new CardTypeSpec(CardType.Schatztruhe),
          new CardTypeSpec(CardType.Fernsprechgerat)));

  public final List<CardTypeSpec> cardSpecification;

  private MarketSetup(List<CardTypeSpec> cardTypeSpecs) {
    cardSpecification = cardTypeSpecs;
  }
}
