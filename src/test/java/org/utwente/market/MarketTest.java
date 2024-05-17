package org.utwente.market;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
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
    Card card = market.buy(order);

    assertNotNull(card);
  }

  @Test
  public void testBuyCardFail() {
    Order order = new Order(CardType.Abenturerin, 0);
    Card card = market.buy(order);

    assertNull(card);
  }

  @Test
  public void testBuyCardWithNull() {
    Card card = market.buy(null);
    assertNull(card);
  }

  @Test
  public void testCanBuyCard() {
    Order order = new Order(CardType.Tausendsassa, 4);
    Card card = market.buy(order);
    assertNotNull(card);
    assertEquals(card.getCardType(), CardType.Tausendsassa);
    assertEquals(84, market.getRemainingCardAmount());
  }

  @Test
  public void testCannotBuyReserveCard() {
    Order order = new Order(CardType.Kartograph, 4);
    assertFalse(market.canBuy(order));
  }

  @Test
  public void testCanBuyReserveCard() {
    Order order = new Order(CardType.Kartograph, 4);
    assertFalse(market.canBuy(order));
    for (int i = 0; i < 5; i++) {
      market.buy(new Order(CardType.Tausendsassa, 10));
    }
    assertTrue(market.canBuy(order));
  }

  @Test
  public void cannotBuyCardOnceFullyConsumed() {
    var order = new Order(CardType.Tausendsassa, 1000);
    assertTrue(market.canBuy(order));
    for (int i = 0; i < 5; i++) {
      market.buy(new Order(CardType.Tausendsassa, 10));
    }
    assertFalse(market.canBuy(order));
  }

  @Test
  public void cannotBuyCardBecauseCashIsLow() {
    Order order = new Order(CardType.Tausendsassa, 0);
    Card card = market.buy(order);
    assertNull(card);
  }

}
