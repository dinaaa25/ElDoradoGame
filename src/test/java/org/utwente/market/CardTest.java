package org.utwente.market;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
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
    public void testRemovePowerSequentially() {
        try {
            card.removePower(1);
            card.removePower(1);
        } catch (Exception e) {
        }
        assertEquals(CardType.Abenteurerin.power.intValue() - 2, card.remainingPower());
    }

    @Test
    public void testRemoveMorePowerThanAvailable() {
        assertThrows(CardPowerException.class, () -> {
            card.removePower(4);
        });
    }

    @Test
    public void testRemoveZeroPower() {
        try {
            card.removePower(0);
        } catch (Exception e) {
        }
        assertEquals(CardType.Abenteurerin.power.intValue(), card.remainingPower());
    }

    @Test
    public void testRemoveNegativePower() {
        assertThrows(IllegalArgumentException.class, () -> {
            card.removePower(-1);
        });
    }

    @Test
    public void testRemoveExactPower() {
        try {
            card.removePower(CardType.Abenteurerin.power.intValue());
        } catch (Exception e) {
        }
        assertEquals(0, card.remainingPower());
    }

    @Test
    void testSettersCard() {
        try {
            card.setCardType(CardType.Entdecker);
        } catch (Exception e) {
        }
        assertEquals(CardType.Entdecker, card.getCardType());
        try {
            card.setConsumedPower(3);
        } catch (Exception e) {
        }
        assertEquals(3, card.getConsumedPower());
    }

    @Test
    void testGetTypeConsistency() {
        assertEquals(CardType.Abenteurerin.powerType, card.getType());
        card.setCardType(CardType.Entdecker);
        assertEquals(CardType.Entdecker.powerType, card.getType());
    }
}
