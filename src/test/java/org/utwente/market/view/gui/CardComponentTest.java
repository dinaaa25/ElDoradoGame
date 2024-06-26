
package org.utwente.market.view.gui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

import javax.swing.*;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class CardComponentTest {
    @Test
    void shouldDisplayCorrectCardName() {
        Card card = new Card(CardType.Kundeschafter);
        CardComponent cardComponent = new CardComponent(card, 1, 1);
        JLabel nameLabel = (JLabel) cardComponent.getComponent(0);
        assertEquals("Kundeschafter", nameLabel.getText());
    }


    @Test
    void shouldThrowExceptionWhenCardTypeIsNull() {
        assertThrows(NullPointerException.class, () -> new CardComponent(null, 1, 1));
    }

    @Test
    void setupShouldInitializeCardComponent() {
        Card card = new Card(CardType.Kundeschafter);
        CardComponent cardComponent = new CardComponent(card);
        cardComponent.setup();

        assertNotNull(cardComponent.getCardType());
    }

    @Test
    void cardComponentShouldInitializeWithCardType() {
        Card card = new Card(CardType.Kundeschafter);
        CardComponent cardComponent = new CardComponent(card, 1, 1);

        assertEquals(CardType.Kundeschafter, cardComponent.getCardType());
    }
    /**
     * Method under test: {@link CardComponent#getCardType()}
     */
    @Test
    void testGetCardType() {
        // Arrange and Act
        Object actualCardType = (new CardComponent(new Card(CardType.Kundeschafter))).getCardType();

        // Assert
        assertTrue(actualCardType instanceof CardType);
        assertEquals(CardType.Kundeschafter, actualCardType);
    }

    /**
     * Method under test: {@link CardComponent#CardComponent(Card)}
     */
    @Test
    void testNewCardComponent() {
        // Arrange, Act and Assert
        Locale locale = (new CardComponent(new Card(CardType.Kundeschafter))).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test: {@link CardComponent#CardComponent(Card)}
     */
    @Test
    void testNewCardComponent2() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(1);

        // Act
        CardComponent actualCardComponent = new CardComponent(card);

        // Assert
        KeyStroke expectedKeyStroke = actualCardComponent.getRegisteredKeyStrokes()[1];
        assertSame(expectedKeyStroke, actualCardComponent.getInputMap().getParent().keys()[1]);
    }

    /**
     * Method under test: {@link CardComponent#CardComponent(Card)}
     */
    @Test
    void testNewCardComponent3() {
        // Arrange, Act and Assert
        Locale locale = (new CardComponent(new Card(CardType.Reisende))).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test: {@link CardComponent#CardComponent(Card, double)}
     */
    @Test
    void testNewCardComponent4() {
        // Arrange, Act and Assert
        Locale locale = (new CardComponent(new Card(CardType.Kundeschafter), 10.0d)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test: {@link CardComponent#CardComponent(Card, double)}
     */
    @Test
    void testNewCardComponent5() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(1);

        // Act
        CardComponent actualCardComponent = new CardComponent(card, 10.0d);

        // Assert
        KeyStroke expectedKeyStroke = actualCardComponent.getRegisteredKeyStrokes()[1];
        assertSame(expectedKeyStroke, actualCardComponent.getInputMap().getParent().keys()[1]);
    }

    /**
     * Method under test: {@link CardComponent#CardComponent(Card, double)}
     */
    @Test
    void testNewCardComponent6() {
        // Arrange, Act and Assert
        Locale locale = (new CardComponent(new Card(CardType.Reisende), 10.0d)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer)}
     */
    @Test
    void testNewCardComponent7() {
        // Arrange, Act and Assert
        Locale locale = (new CardComponent(new Card(CardType.Kundeschafter), 1, 1)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer)}
     */
    @Test
    void testNewCardComponent8() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(0);

        // Act and Assert
        Locale locale = (new CardComponent(card, null, 1)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer)}
     */
    @Test
    void testNewCardComponent9() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(1);

        // Act
        CardComponent actualCardComponent = new CardComponent(card, null, 1);

        // Assert
        KeyStroke expectedKeyStroke = actualCardComponent.getRegisteredKeyStrokes()[1];
        assertSame(expectedKeyStroke, actualCardComponent.getInputMap().getParent().keys()[1]);
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer)}
     */
    @Test
    void testNewCardComponent10() {
        // Arrange
        Card card = new Card(CardType.Reisende);
        card.setConsumedPower(0);

        // Act and Assert
        Locale locale = (new CardComponent(card, null, 1)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer, boolean)}
     */
    @Test
    void testNewCardComponent11() {
        // Arrange, Act and Assert
        Locale locale = (new CardComponent(new Card(CardType.Kundeschafter), 1, 1, true)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer, boolean)}
     */
    @Test
    void testNewCardComponent12() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(0);

        // Act and Assert
        Locale locale = (new CardComponent(card, null, 1, true)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer, boolean)}
     */
    @Test
    void testNewCardComponent13() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(1);

        // Act
        CardComponent actualCardComponent = new CardComponent(card, null, 1, true);

        // Assert
        KeyStroke expectedKeyStroke = actualCardComponent.getRegisteredKeyStrokes()[1];
        assertSame(expectedKeyStroke, actualCardComponent.getInputMap().getParent().keys()[1]);
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer, boolean)}
     */
    @Test
    void testNewCardComponent14() {
        // Arrange, Act and Assert
        Locale locale = (new CardComponent(new Card(CardType.Kundeschafter), 1, 1, false)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }

    /**
     * Method under test:
     * {@link CardComponent#CardComponent(Card, Integer, Integer, boolean)}
     */
    @Test
    void testNewCardComponent15() {
        // Arrange
        Card card = new Card(CardType.Reisende);
        card.setConsumedPower(0);

        // Act and Assert
        Locale locale = (new CardComponent(card, null, 1, true)).getLocale();
        Set<String> expectedExtensionKeys = locale.getUnicodeLocaleAttributes();
        assertSame(expectedExtensionKeys, locale.getExtensionKeys());
    }
}



