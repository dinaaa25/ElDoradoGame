package org.utwente.market;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.market.exceptions.BuyException;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.CardTypeSpec;
import org.utwente.market.model.Market;
import org.utwente.market.model.MarketSetup;
import org.utwente.market.model.Order;

public class MarketTest {
  Market market;

  @BeforeEach
  public void setup() {
    market = new Market();
  }

  @Test
  public void createMarket() {
    assertNotNull(market.getCurrentCards());
    assertNotNull(market.getReserveCards());
    assertEquals(6, market.getCurrentCards().size());
    assertEquals(11, market.getReserveCards().size());
    assertEquals(85, market.getRemainingCardAmount());
  }

  @Test
  public void testBuyCard() {
    Order order = new Order(CardType.Fotografin, 4);
    Card card = null;

    try {
      card = market.buy(order);
    } catch (BuyException e) {

    }

    assertNotNull(card);
  }

  @Test
  public void testBuyCardFail() {
    Order order = new Order(CardType.Abenturerin, 0);
    Card card = null;

    try {
      card = market.buy(order);
    } catch (BuyException e) {

    }

    assertNull(card);
  }

  @Test
  public void testBuyCardWithNull() {
    Card card = null;

    try {
      card = market.buy(null);
    } catch (BuyException e) {

    }
    assertNull(card);
  }

  @Test
  public void testBuyAndReplaceInCurrent() {
    Order order = new Order(CardType.Kartograph, 4);
    assertFalse((market.canBuy(order)).status());
    try {
      for (int i = 0; i < 5; i++) {
        market.buy(new Order(CardType.Tausendsassa, 10));
      }
      market.buy(order);

    } catch (BuyException e) {

    }
    assertFalse(market.reserveIsOpen());
    assertTrue((market.canBuy(order)).status());
  }

  @Test
  public void testCanBuyCard() {
    Order order = new Order(CardType.Tausendsassa, 4);

    Card card = null;
    try {
      market.buy(order);
    } catch (BuyException e) {

    }
    assertNotNull(card);
    assertEquals(card.getCardType(), CardType.Tausendsassa);
    assertEquals(84, market.getRemainingCardAmount());
  }

  @Test
  public void testCannotBuyReserveCard() {
    Order order = new Order(CardType.Kartograph, 4);
    assertFalse((market.canBuy(order)).status());
  }

  @Test
  public void testCanBuyReserveCard() {
    Order order = new Order(CardType.Kartograph, 4);
    assertFalse((market.canBuy(order)).status());
    try {
      for (int i = 0; i < 5; i++) {
        market.buy(new Order(CardType.Tausendsassa, 10));
      }
    } catch (Exception e) {

    }
    assertTrue((market.canBuy(order)).status());
  }

  @Test
  public void cannotBuyCardOnceFullyConsumed() {
    var order = new Order(CardType.Tausendsassa, 1000);
    assertTrue((market.canBuy(order)).status());
    try {

      for (int i = 0; i < 5; i++) {
        market.buy(new Order(CardType.Tausendsassa, 10));
      }
    } catch (BuyException e) {
    }

    assertFalse((market.canBuy(order)).status());
  }

  @Test
  public void cannotBuyCardBecauseCashIsLow() {
    Order order = new Order(CardType.Tausendsassa, 0);
    Card card = null;
    try {
      card = market.buy(order);
    } catch (BuyException e) {

    }
    assertNull(card);
  }

  @Test
  public void testCardInReserve() {
    for (CardTypeSpec cardSpec : MarketSetup.reserve.cardSpecification) {
      assertFalse(market.cardInCurrent(cardSpec.getType()));
      assertTrue(market.cardInReserve(cardSpec.getType()));
    }
  }

  @Test
  public void testCardInCurrent() {
    for (CardTypeSpec cardSpec : MarketSetup.active.cardSpecification) {
      assertFalse(market.cardInReserve(cardSpec.getType()));
      assertTrue(market.cardInCurrent(cardSpec.getType()));
    }
  }

  @Test
  public void cannotBuyCardOutsideMarket() {
    Card card = null;
    try {
      market.buy(new Order(CardType.Forscher, 5));
    } catch (BuyException e) {

    }
    assertNull(card);
  }

  @Test
  public void nothingRemovedIfCardNotInMarket() {
    market.removeCardFromMarket(CardType.Forscher);
    assertEquals(85, market.getRemainingCardAmount());
  }

}
