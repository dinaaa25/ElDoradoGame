package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.awt.color.ColorSpace;

import org.junit.jupiter.api.Test;

class DrawPileTest {
    /**
     * Method under test: default or parameterless constructor of {@link DrawPile}
     */
    @Test
    void testNewDrawPile() {
        // Arrange, Act and Assert
        Color background = (new DrawPile(1).getBackground());
        ColorSpace expectedColorSpace = background.getColorSpace();
        assertSame(expectedColorSpace, background.darker().getColorSpace());
    }
}
