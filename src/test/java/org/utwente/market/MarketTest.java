package org.utwente.market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.market.model.Market;

public class MarketTest {
  Market market;

  @BeforeEach
  public void setup() {
    market = new Market();
  }

  @Test
  public void testBuyCard() {
    Order order = new Order("", 1);
    market.buy(null);
  }

  @Test
  public void testBuyCardFail() {

  }

  @Test
  public void testCanBuyCard() {

  }

  @Test
  public void cannotBuyCard() {

  }

  @Test
  public void testLoadFromJson() {

  }
}
