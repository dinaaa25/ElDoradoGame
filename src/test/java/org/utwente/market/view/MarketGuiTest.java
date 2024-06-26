package org.utwente.market.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.awt.*;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import static org.mockito.Mockito.*;
import javax.swing.*;
import java.awt.event.WindowEvent;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.utwente.Main;
import org.utwente.game.view.gui.BoardForm;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.CardTypeSpec;
import org.utwente.market.model.Market;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.market.view.gui.GridCoordinate;
import org.utwente.market.model.MarketSetup;


class MarketGuiTest {

    MarketGui marketGui;

    @BeforeEach
    public void setUp() {
        marketGui = new MarketGui();
    }

    /**
     * Method under test: {@link MarketGui#displayPurchaseResult(Card)}
     */
    @Test
    void testDisplayPurchaseResult() {
        // Arrange

        // Act
        marketGui.displayPurchaseResult(new Card(CardType.Kundeschafter));

        // Assert
        GridCoordinate gridCoordinate = marketGui.coord;
        assertEquals(0, gridCoordinate.x);
        assertEquals(3, gridCoordinate.columns);
        assertEquals(3, gridCoordinate.y);
        assertFalse(marketGui.missingDrawCards);
    }

    /**
     * Method under test: {@link MarketGui#displayMarket()}
     */
    /**
     * Method under test: {@link MarketGui#displayPurchaseResult(Card)}
     */
    @Test
    void testDisplayPurchaseResult2() {
        // Arrange

        // Act
        marketGui.displayPurchaseResult(new Card(CardType.Forscher));

        // Assert
        GridCoordinate gridCoordinate = marketGui.coord;
        assertEquals(0, gridCoordinate.x);
        assertEquals(3, gridCoordinate.columns);
        assertEquals(3, gridCoordinate.y);
        assertFalse(marketGui.missingDrawCards);
    }

    @Test
    void testDisplayMarket() {
        // Arrange

        // Act
        marketGui.displayMarket();

        // Assert
        GridCoordinate gridCoordinate = marketGui.coord;
        assertEquals(0, gridCoordinate.x);
        assertEquals(2, gridCoordinate.y);
        assertEquals(3, gridCoordinate.columns);
    }

    /**
     * Method under test: {@link MarketGui#displayMarket()}
     */
    @Test
    @Disabled
    //TODO: cant get this to not be flakey
    void testDisplayMarket2() {
        // Arrange
        marketGui.setMarket(new Market());

        // Act
        marketGui.displayMarket();

        // Assert
        GridCoordinate gridCoordinate = marketGui.coord;
        assertEquals(2, gridCoordinate.x);
        assertEquals(3, gridCoordinate.columns);
        assertEquals(9, gridCoordinate.y);
        assertEquals(CardType.Millionarin, ((CardComponent) marketGui.panel.getComponents()[19]).getCardType());
    }

    /**
     * Method under test: {@link MarketGui#displayError(String)}
     */
    @Test
    void testDisplayError() {
        // Arrange

        // Act
        marketGui.displayError("An error occurred");

        // Assert
        GridCoordinate gridCoordinate = marketGui.coord;
        assertEquals(0, gridCoordinate.x);
        assertEquals(2, gridCoordinate.y);
        assertEquals(3, gridCoordinate.columns);
        assertFalse(marketGui.missingDrawCards);
    }

    /**
     * Method under test: {@link MarketGui#setMarket(Market)}
     */
    @Test
    @Disabled
    //TODO: Cant get this to not be flaky
    void testSetMarket() {
        // Arrange
        Market market = new Market();

        // Act
        marketGui.setMarket(market);

        // Assert
        GridCoordinate gridCoordinate = marketGui.coord;
        assertEquals(2, gridCoordinate.x);
        assertEquals(market.getRemainingCardAmount(), marketGui.market.getRemainingCardAmount());
        assertEquals(9, gridCoordinate.y);

        CardComponent foundCardComponent = null;
        for (Component component : marketGui.panel.getComponents()) {
            if (component instanceof CardComponent) {
                foundCardComponent = (CardComponent) component;
                break;
            }
        }
        assertEquals(CardType.Fernsprechgerat, foundCardComponent.getCardType());
    }




/**
     * Method under test: {@link MarketGui#setMarket(Market)}
     */
    @Test
    void testSetMarket2() {
        // Arrange

        // Act
        marketGui.setMarket(null);

        // Assert
        assertNull(marketGui.market);
    }



    /**
     * Method under test: {@link MarketGui#setMarket(Market)}
     */
    @Test
    void testSetMarket3() {
        // Arrange
        marketGui.displayPurchaseResult(new Card(CardType.Kundeschafter));

        // Act
        marketGui.setMarket(new Market());

        // Assert
        assertEquals(51, marketGui.market.getRemainingCardAmount());
    }


    /**
     * Method under test: {@link MarketGui#exit()}
     */
    @Test
    void testExit() {
        // Arrange
        JFrame frameMock = mock(JFrame.class);
        marketGui.f = frameMock; // Inject the mock JFrame

        // Act
        marketGui.exit();

        // Assert
        verify(frameMock).dispatchEvent(any(WindowEvent.class));
    }

    /**
     * Method under test: {@link MarketGui#addTitle()}
     */
    @Test
    void testAddTitle() {
        // Arrange

        // Act
        marketGui.addTitle();

        // Assert
        GridCoordinate gridCoordinate = marketGui.coord;
        assertEquals(0, gridCoordinate.x);
        assertEquals(3, gridCoordinate.y);
    }

    /**
     * Method under test: {@link MarketGui#setBackground(JComponent)}
     */
    @Test
    void testSetBackground() {
        // Arrange

        // Act
        marketGui.setBackground(new BoardForm());

        // Assert that nothing has changed
        assertEquals(3, marketGui.coord.columns);
        assertTrue(marketGui.missingDrawCards);
    }

    /**
     * Method under test: {@link MarketGui#setBackground(JComponent)}
     */
    @Test
    void testSetBackground2() {
        // Arrange

        BoardForm panel = new BoardForm();
        panel.addVetoableChangeListener(mock(VetoableChangeListener.class));

        // Act
        marketGui.setBackground(panel);

        // Assert that nothing has changed
        assertEquals(3, marketGui.coord.columns);
        assertTrue(marketGui.missingDrawCards);
    }

    /**
     * Method under test: {@link MarketGui#addCard(CardType, GridCoordinate)}
     */
    @Test
    void testAddCard() {
        // Arrange
        ArrayList<CardTypeSpec> current = new ArrayList<>();
        marketGui.setMarket(new Market(current, new ArrayList<>()));

        // Act
        marketGui.addCard(CardType.Kundeschafter, new GridCoordinate(2, 3));

        // Assert
        assertEquals(CardType.Kundeschafter, ((CardComponent) marketGui.panel.getComponents()[3]).getCardType());

    }



    /**
     * Method under test: {@link MarketGui#getMainComponent()}
     */

    @Test
    void testGetMainComponent() {
        // Arrange

        // Act and Assert
        assertSame(marketGui.scrollPane, marketGui.getMainComponent());
    }

    /**
     * Method under test: default or parameterless constructor of {@link MarketGui}
     */
    @Test
    void testNewMarketGui() {
        // Arrange and Act
        MarketGui actualMarketGui = new MarketGui();

        // Assert
        Object expectedTreeLock = actualMarketGui.getMainComponent().getTreeLock();
        assertSame(expectedTreeLock, actualMarketGui.panel.getTreeLock());
    }
}
