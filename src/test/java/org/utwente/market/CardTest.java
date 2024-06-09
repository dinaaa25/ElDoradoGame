package org.utwente.market;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardPowerException;
import org.utwente.market.model.CardType;

public class CardTest {
  private Card card;

  @BeforeEach
  public void setup() {
    this.card = new Card(CardType.Abenteurerin);
  }

  @Test
  public void testRemainingPower() {
    assertEquals(CardType.Abenteurerin.power.intValue(), card.remainingPower());
  }

  @Test
  public void testRemovePower() {
    try {
      card.removePower(1);
    } catch (CardPowerException e) {

    }
    assertEquals(1, card.remainingPower());
  }

  @Test
  public void testRemoveMorePowerThanAvailable() {
    assertThrows(CardPowerException.class, () -> {
      card.removePower(4);
    });
  }

  @Test
  void testSettersCard() {
    card.setCardType(CardType.Entdecker);
    assertEquals(CardType.Entdecker, card.getCardType());
    card.setConsumedPower(3);
    assertEquals(3, card.getConsumedPower());
  }

}
