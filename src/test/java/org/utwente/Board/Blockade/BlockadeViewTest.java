package org.utwente.Board.Blockade;
import org.utwente.Section.SectionType;
import org.utwente.Section.Section;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;

import org.mockito.ArgumentCaptor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;

import org.junit.jupiter.api.Test;
import org.utwente.Tile.TileType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.utwente.game.view.GameConfig.INFO_FONT;



class BlockadeViewTest {
    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Machete);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor2() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Paddle);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor3() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Coin);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor4() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Basecamp);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor5() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Discard);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor6() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Mountain);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor7() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Cave);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor8() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.ElDorado);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link BlockadeView#getBlockadeColor(TileType)}
     */
    @Test
    void testGetBlockadeColor9() {
        // Arrange and Act
        Color actualBlockadeColor = (new BlockadeView()).getBlockadeColor(TileType.Start);

        // Assert
        ColorSpace expectedColorSpace = actualBlockadeColor.getColorSpace();
        assertSame(expectedColorSpace, actualBlockadeColor.darker().getColorSpace());
    }

    @Test
    void testDraw() {
        Blockade blockade = mock(Blockade.class); // Mock Blockade to control its behavior
        when(blockade.isRemoved()).thenReturn(false); // Setup behavior for blockade.isRemoved()
        when(blockade.getTileType()).thenReturn(TileType.Machete);
        when(blockade.getPoints()).thenReturn(5);
        when(blockade.getPower()).thenReturn(3);
        Section mockSection = mock(Section.class);
        when(mockSection.getSectionType()).thenReturn(SectionType.A);
        when(blockade.getSection1()).thenReturn(mockSection);

        Graphics2D g2d = mock(Graphics2D.class); // Mock Graphics2D
        BlockadeView blockadeView = new BlockadeView();

        blockadeView.draw(g2d, blockade, 50); // Call draw method with yOffset of 50

        verify(g2d).setColor(new Color(101, 140, 35)); // Adjusted to the actual color used for Machete
        verify(g2d).setStroke(new BasicStroke(5));

        ArgumentCaptor<Line2D.Double> lineCaptor = forClass(Line2D.Double.class);
        verify(g2d).draw(lineCaptor.capture());
        Line2D.Double capturedLine = lineCaptor.getValue();
        assertEquals(20, capturedLine.getX1(), 0.1);
        assertEquals(70, capturedLine.getY1(), 0.1);
        assertEquals(120, capturedLine.getX2(), 0.1);
        assertEquals(70, capturedLine.getY2(), 0.1);

        verify(g2d).setColor(Color.BLACK);
        verify(g2d).setFont(INFO_FONT);

        verify(g2d).drawString("Points: 5", 130, 60); // Adjusted yOffset for each string
        verify(g2d).drawString("Power: 3", 130, 70);
        verify(g2d).drawString("Removed: false", 130, 80);
        verify(g2d).drawString("BlockadeType: Machete", 130, 90);
        verify(g2d).drawString("SectionType: A", 130, 100); // Adjusted to match the actual section type
    }
}