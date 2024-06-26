package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.awt.color.ColorSpace;

import org.junit.jupiter.api.Test;

class DiscardCardTest {
    /**
     * Method under test: default or parameterless constructor of
     * {@link DiscardCard}
     */
    @Test
    void testNewDiscardCard() {
        // Arrange, Act and Assert
        Color background = (new DiscardCard(1)).getBackground();
        ColorSpace expectedColorSpace = background.getColorSpace();
        assertSame(expectedColorSpace, background.darker().getColorSpace());
    }
}
