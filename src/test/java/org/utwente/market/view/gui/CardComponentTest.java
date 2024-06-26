package org.utwente.market.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JLabel;

@Nested
class CardComponentTest {
    /**
     * Method under test: {@link CardComponent#setup()}
     */
    void shouldDisplayCorrectCardName() {
        CardComponent cardComponent = new CardComponent(CardType.Kundeschafter, 1, 1);
        JLabel nameLabel = (JLabel) cardComponent.getComponent(0);
        assertEquals("Kundeschafter", nameLabel.getText());
    }

    @Test
    void shouldDisplayCorrectCardPrice() {
        CardComponent cardComponent = new CardComponent(CardType.Kundeschafter, 1, 1);
        JLabel priceLabel = (JLabel) cardComponent.getComponent(1);
        assertEquals("Price: 1 coins", priceLabel.getText());
    }

    @Test
    void shouldDisplayCorrectCardPower() {
        CardComponent cardComponent = new CardComponent(CardType.Kundeschafter, 1, 1);
        JLabel powerLabel = (JLabel) cardComponent.getComponent(2);
        assertEquals("Power: 2", powerLabel.getText());
    }

    @Test
    void shouldDisplayCorrectRemainingAmount() {
        CardComponent cardComponent = new CardComponent(CardType.Kundeschafter, 1, 1);
        JLabel remainingLabel = (JLabel) cardComponent.getComponent(3);
        assertEquals("Remaining: 1", remainingLabel.getText());
    }

    @Test
    void shouldDisplayCorrectRemainingPowerWhenAmountIsNull() {
        CardComponent cardComponent = new CardComponent(CardType.Kundeschafter, null, 1);
        JLabel remainingLabel = (JLabel) cardComponent.getComponent(3);
        assertEquals("Remaining Power: 1", remainingLabel.getText());
    }

    @Test
    void shouldThrowExceptionWhenCardTypeIsNull() {
        assertThrows(NullPointerException.class, () -> new CardComponent(null, 1, 1));
    }

    @Test
    void setupShouldInitializeCardComponent() {
        CardComponent cardComponent = new CardComponent(new Card(CardType.Kundeschafter));
        cardComponent.setup();

        assertNotNull(cardComponent.getCardType());
    }


    @Test
    void cardComponentShouldInitializeWithCardType() {
        CardComponent cardComponent = new CardComponent(CardType.Kundeschafter, 1, 1);

        assertEquals(CardType.Kundeschafter, cardComponent.getCardType());
    }
}