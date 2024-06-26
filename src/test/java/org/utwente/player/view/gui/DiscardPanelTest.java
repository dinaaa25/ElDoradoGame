package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;

class DiscardPanelTest {
    /**
     * Method under test: {@link DiscardPanel#DiscardPanel(Player)}
     */
    @Test
    void testNewDiscardPanel() {
        // Arrange and Act
        DiscardPanel actualDiscardPanel = new DiscardPanel(new Player("Name"));

        // Assert
        Object expectedTreeLock = actualDiscardPanel.getTreeLock();
        assertSame(expectedTreeLock, (actualDiscardPanel.getComponents()[1]).getTreeLock());
    }

    /**
     * Method under test: {@link DiscardPanel#DiscardPanel(Player)}
     */
    @Test
    void testNewDiscardPanel2() {
        // Arrange
        CardPile discardPile = new CardPile();
        discardPile.add(new Card(CardType.Kundeschafter));

        Player player = new Player("Name");
        player.setDiscardPile(discardPile);

        // Act
        DiscardPanel actualDiscardPanel = new DiscardPanel(player);

        // Assert
        Object expectedTreeLock = actualDiscardPanel.getTreeLock();
        assertSame(expectedTreeLock, (actualDiscardPanel.getComponents()[1]).getTreeLock());
    }

    /**
     * Method under test: {@link DiscardPanel#DiscardPanel(Player)}
     */
    @Test
    void testNewDiscardPanel3() {
        // Arrange
        CardPile faceUpDiscardPile = new CardPile();
        faceUpDiscardPile.add(new Card(CardType.Kundeschafter));

        Player player = new Player("Name");
        player.setFaceUpDiscardPile(faceUpDiscardPile);

        // Act
        DiscardPanel actualDiscardPanel = new DiscardPanel(player);

        // Assert
        Object expectedTreeLock = actualDiscardPanel.getTreeLock();
        assertSame(expectedTreeLock, (actualDiscardPanel.getComponents()[1]).getTreeLock());
    }

    /**
     * Method under test: {@link DiscardPanel#DiscardPanel(Player)}
     */
    @Test
    void testNewDiscardPanel4() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(170);

        CardPile faceUpDiscardPile = new CardPile();
        faceUpDiscardPile.add(card);

        Player player = new Player("Name");
        player.setFaceUpDiscardPile(faceUpDiscardPile);

        // Act
        DiscardPanel actualDiscardPanel = new DiscardPanel(player);

        // Assert
        Object expectedTreeLock = actualDiscardPanel.getTreeLock();
        assertSame(expectedTreeLock, (actualDiscardPanel.getComponents()[1]).getTreeLock());
    }
}
