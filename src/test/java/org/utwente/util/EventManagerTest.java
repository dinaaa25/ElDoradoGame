
package org.utwente.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Consumer;
import java.util.List;

import org.utwente.market.controller.BuyEvent;
import org.utwente.market.model.CardType;
import org.utwente.util.event.EmptyEvent;
import org.utwente.util.event.Event;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

public class EventManagerTest {

    private EventManager eventManager;

    @BeforeEach
    public void setUp() {

        EventManager.getInstance(); // Reset the singleton before each test
        eventManager = EventManager.getInstance();
        eventManager.resetSubscribers();
    }

    @Test
    public void testSingletonInstance() {
        EventManager anotherInstance = EventManager.getInstance();
        assertSame(eventManager, anotherInstance, "Both instances should be the same");
    }

    @Test
    public void testRegisterListener() {
        Consumer<Event> mockListener = (event) -> System.out.println("Received: " + event);
        eventManager.subscribe(mockListener, EventType.StartGame);
        assertTrue(eventManager.getSubscribers().contains(mockListener), "Listener should be registered for StartGame");
    }

    @Test
    public void testReceiveInput() {
        StringBuilder result = new StringBuilder();
        Consumer<Event> mockListener = event -> result.append("TestPlay");
        eventManager.subscribe(mockListener, EventType.PlayCards);
        eventManager.notifying(EventType.PlayCards, new EmptyEvent());
        assertEquals("TestPlay", result.toString(), "Listener should receive the input data");
    }

    @Test
    public void testReceiveInputWithNoListeners() {
        String result = "";
        eventManager.notifying(EventType.BuyCards, new BuyEvent(CardType.Entdecker));
        assertEquals("", result, "No listeners, so no data should be received");
    }

    @Test
    public void testUnsubscribeListener() {
        Consumer<Event> mockListener = (event) -> System.out.println("Received: " + event);
        eventManager.subscribe(mockListener, EventType.DiscardCards);
        eventManager.unsubscribe(mockListener, EventType.DiscardCards);
        assertFalse(eventManager.getSubscribers().contains(mockListener),
                "Listener should be unsubscribed from DiscardCards");
    }

    @Test
    public void testResetSubscribers() {
        Consumer<Event> mockListener = (event) -> System.out.println("Received: " + event);
        eventManager.subscribe(mockListener, EventType.EndGame);
        eventManager.resetSubscribers();
        assertFalse(eventManager.getSubscribers().contains(mockListener), "All subscribers should be cleared");
    }

    @Test
    public void testMultipleSubscriptions() {
        Consumer<Event> listenerA = (event) -> System.out.println("A Received: " + event);
        Consumer<Event> listenerB = (event) -> System.out.println("B Received: " + event);
        eventManager.subscribe(listenerA, EventType.PlayCards);
        eventManager.subscribe(listenerB, EventType.PlayCards);
        assertTrue(eventManager.getSubscribers().contains(listenerA) &&
                        eventManager.getSubscribers().contains(listenerB),
                "Both listeners should be subscribed to PlayCards");
    }

    @Test
    public void testNotifyAllListeners() {
        StringBuilder resultA = new StringBuilder();
        StringBuilder resultB = new StringBuilder();
        Consumer<Event> listenerA = event -> resultA.append("NotifyEndGame");
        Consumer<Event> listenerB = event -> resultB.append("NotifyEndGame");
        eventManager.subscribe(listenerA, EventType.EndGame);
        eventManager.subscribe(listenerB, EventType.EndGame);
        eventManager.notifying(EventType.EndGame, new EmptyEvent());
        assertEquals("NotifyEndGame", resultA.toString(), "Listener A should receive the notification");
        assertEquals("NotifyEndGame", resultB.toString(), "Listener B should receive the notification");
    }
}