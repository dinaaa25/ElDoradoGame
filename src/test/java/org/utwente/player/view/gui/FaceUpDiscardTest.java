package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.player.model.CardPile;

class FaceUpDiscardTest {
    /**
     * Method under test: {@link FaceUpDiscard#FaceUpDiscard(CardPile)}
     */
    @Test
    void testNewFaceUpDiscard() {
        // Arrange, Act and Assert
        Locale locale = (new FaceUpDiscard(new CardPile())).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link FaceUpDiscard#FaceUpDiscard(CardPile)}
     */
    @Test
    void testNewFaceUpDiscard2() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(0);

        ArrayList<Card> resources = new ArrayList<>();
        resources.add(card);

        CardPile faceUpDiscardPile = new CardPile();
        faceUpDiscardPile.setResources(resources);

        // Act and Assert
        Locale locale = (new FaceUpDiscard(faceUpDiscardPile)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link FaceUpDiscard#FaceUpDiscard(CardPile)}
     */
    @Test
    void testNewFaceUpDiscard3() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(1);

        ArrayList<Card> resources = new ArrayList<>();
        resources.add(card);

        CardPile faceUpDiscardPile = new CardPile();
        faceUpDiscardPile.setResources(resources);

        // Act and Assert
        Locale locale = (new FaceUpDiscard(faceUpDiscardPile)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link FaceUpDiscard#FaceUpDiscard(CardPile)}
     */
    @Test
    void testNewFaceUpDiscard4() {
        // Arrange
        CardPile faceUpDiscardPile = new CardPile();
        faceUpDiscardPile.add(new Card(CardType.Reisende));

        // Act and Assert
        Locale locale = (new FaceUpDiscard(faceUpDiscardPile)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }
}
