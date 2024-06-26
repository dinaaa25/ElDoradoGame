package org.utwente.market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.utwente.market.controller.BuyEvent;
import org.utwente.market.controller.MarketController;
import org.utwente.market.exceptions.BuyException;
import org.utwente.market.model.*;
import org.utwente.market.view.MarketCli;
import org.utwente.market.view.MarketView;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MarketControllerTest {

    private MarketController controller;
    private MarketView view;
    private Market model;

    @BeforeEach
    public void setup() {
        view = mock(MarketCli.class);
        model = mock(Market.class);
        controller = new MarketController(view, model);
    }

    @Test
    public void testSuccessfulCardPurchase() throws BuyException {
        Card expectedCard = new Card(CardType.Abenteurerin);
        when(model.buy(any(Order.class))).thenReturn(expectedCard);
        EventManager.getInstance().notifying(EventType.BuyCards,new BuyEvent(CardType.Abenteurerin));
        verify(model).buy(any(Order.class));
        verify(view).displayPurchaseResult(expectedCard);
        verify(view, times(2)).setMarket(any(Market.class));
    }

    @Test
    public void testFailedCardPurchase() throws BuyException {
        doThrow(new BuyException("Out of stock")).when(model).buy(any(Order.class));
        EventManager.getInstance().notifying(EventType.BuyCards, new BuyEvent(CardType.Abenteurerin));
        verify(view).displayError("Out of stock");
    }


    @Test
    public void testInvalidCardType() {
        // Arrange: Create a BuyEvent with an invalid card type

        // Act & Assert: Expect IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            EventManager.getInstance().notifying(EventType.BuyCards, new BuyEvent(CardType.valueOf("InvalidCardType")));
        });
    }



    @Test
    public void testViewUpdatesAfterTransaction() throws BuyException {
        Card expectedCard = new Card(CardType.Abenteurerin);
        when(model.buy(any(Order.class))).thenReturn(expectedCard);
        EventManager.getInstance().notifying(EventType.BuyCards, new BuyEvent(CardType.Abenteurerin));
        verify(view, times(2)).setMarket(any(Market.class));
    }
}
