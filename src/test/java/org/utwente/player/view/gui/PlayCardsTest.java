package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import org.utwente.game.model.CartographerEffectPhase;
import org.utwente.game.model.Phase;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;

class PlayCardsTest {
    /**
     * Method under test: {@link PlayCards#PlayCards(CardPile, Phase)}
     */
    @Test
    void testNewPlayCards() {
        // Arrange
        CardPile pile = new CardPile();

        // Act and Assert
        Color background = (new PlayCards(pile, new Phase())).getBackground();
        ColorSpace expectedColorSpace = background.getColorSpace();
        assertSame(expectedColorSpace, background.darker().getColorSpace());
    }

    /**
     * Method under test: {@link PlayCards#PlayCards(CardPile, Phase)}
     */
    @Test
    void testNewPlayCards2() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(0);

        ArrayList<Card> resources = new ArrayList<>();
        resources.add(card);

        CardPile pile = new CardPile();
        pile.setResources(resources);

        // Act
        PlayCards actualPlayCards = new PlayCards(pile, new Phase());

        // Assert
        Object expectedTreeLock = actualPlayCards.getTreeLock();
        assertSame(expectedTreeLock, (actualPlayCards.getComponents()[0]).getTreeLock());
    }

    /**
     * Method under test: {@link PlayCards#PlayCards(CardPile, Phase)}
     */
    @Test
    void testNewPlayCards3() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(0);

        ArrayList<Card> resources = new ArrayList<>();
        resources.add(card);

        CardPile pile = new CardPile();
        pile.setResources(resources);

        Phase phase = new Phase();
        Card resource = new Card(CardType.Kundeschafter);
        phase.setEffectPhase(new CartographerEffectPhase(resource, new Player("Name")));
        phase.setSelectedResources(new Stack<>());

        // Act
        PlayCards actualPlayCards = new PlayCards(pile, phase);

        // Assert
        Object expectedTreeLock = actualPlayCards.getTreeLock();
        assertSame(expectedTreeLock, (actualPlayCards.getComponents()[0]).getTreeLock());
    }

    /**
     * Method under test: {@link PlayCards#PlayCards(CardPile, Phase)}
     */
    @Test
    void testNewPlayCards4() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(1);

        ArrayList<Card> resources = new ArrayList<>();
        resources.add(card);

        CardPile pile = new CardPile();
        pile.setResources(resources);

        // Act
        PlayCards actualPlayCards = new PlayCards(pile, new Phase());

        // Assert
        Object expectedTreeLock = actualPlayCards.getTreeLock();
        assertSame(expectedTreeLock, (actualPlayCards.getComponents()[0]).getTreeLock());
    }

    /**
     * Method under test: {@link PlayCards#PlayCards(CardPile, Phase)}
     */
    @Test
    void testNewPlayCards5() {
        // Arrange
        CardPile pile = new CardPile();
        pile.add(new Card(CardType.Reisende));

        // Act
        PlayCards actualPlayCards = new PlayCards(pile, new Phase());

        // Assert
        Object expectedTreeLock = actualPlayCards.getTreeLock();
        assertSame(expectedTreeLock, (actualPlayCards.getComponents()[0]).getTreeLock());
    }
}
