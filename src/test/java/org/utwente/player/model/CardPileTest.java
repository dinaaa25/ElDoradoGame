package org.utwente.player.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

class CardPileTest {
    /**
     * Method under test: {@link CardPile#draw(int)}
     */
    @Test
    void testDraw() {
        // Arrange
        CardPile cardPile = new CardPile();

        // Act
        CardPile actualDrawResult = cardPile.draw(1);

        // Assert
        assertNull(actualDrawResult.getPileType());
        assertNull(actualDrawResult.getPlayer());
        assertTrue(cardPile.isEmpty());
        assertTrue(actualDrawResult.isEmpty());
    }

    /**
     * Method under test: {@link CardPile#draw(int)}
     */
    @Test
    void testDraw2() {
        // Arrange
        CardPile cardPile = new CardPile();
        cardPile.add(new Card(CardType.Kundeschafter));

        // Act
        CardPile actualDrawResult = cardPile.draw(1);

        // Assert
        assertNull(actualDrawResult.getPileType());
        assertNull(actualDrawResult.getPlayer());
        assertFalse(actualDrawResult.isEmpty());
        assertTrue(cardPile.isEmpty());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link CardPile#CardPile()}
     *   <li>{@link CardPile#getCards()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CardPile actualCardPile = new CardPile();
        List<Card> actualCards = actualCardPile.getCards();

        // Assert
        assertSame(actualCardPile.getResources(), actualCards);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link CardPile#CardPile(List, Player, PileType)}
     *   <li>{@link CardPile#getCards()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<Card> cards = new ArrayList<>();

        // Act and Assert
        assertSame(cards, (new CardPile(cards, new Player("Name"), PileType.Discard)).getCards());
    }
}
