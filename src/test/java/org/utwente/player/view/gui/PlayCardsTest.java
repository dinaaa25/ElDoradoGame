package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Pile;

class PlayCardsTest {
    /**
     * Method under test: {@link PlayCards#PlayCards(Pile)}
     */
    @Test
    void testNewPlayCards() {
        // Arrange, Act and Assert
        Color background = (new PlayCards(new Pile())).getBackground();
        ColorSpace expectedColorSpace = background.getColorSpace();
        assertSame(expectedColorSpace, background.darker().getColorSpace());
    }

    /**
     * Method under test: {@link PlayCards#PlayCards(Pile)}
     */
    @Test
    void testNewPlayCards2() {
        // Arrange
        List<Resource> cards = new ArrayList<>();
        cards.add(new Card(CardType.Kundeschafter));

        Pile pile = new Pile();
        pile.setResources(cards);

        // Act and Assert
        Color background = (new PlayCards(pile)).getBackground();
        ColorSpace expectedColorSpace = background.getColorSpace();
        assertSame(expectedColorSpace, background.darker().getColorSpace());
    }
}
