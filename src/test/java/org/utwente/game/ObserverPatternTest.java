package org.utwente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.game.controller.GameController;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameCLI;
import org.utwente.market.controller.BuyEvent;
import org.utwente.market.model.CardType;
import org.utwente.util.event.EmptyEvent;
import org.utwente.util.event.Event;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ObserverPatternTest {
    Game game;

    GameController gameController;

    GameCLI gameView;
    Consumer<Event> subscriber1;
    Consumer<Event> subscriber2;

    EventManager eventManager;

    @BeforeEach
    public void setup() {
        game = mock(Game.class);
        gameView = mock(GameCLI.class);
        subscriber1 = mock(Consumer.class);
        subscriber2 = mock(Consumer.class);
        // gameController = new GameController(game, gameView);
        eventManager = EventManager.getInstance();
        eventManager.resetSubscribers();
    }

    @Test
    public void checkAllEventsAreAvailable() {
        assertNotEquals(0, eventManager.getEventTypes());
    }

    @Test
    public void testNotifyingAllEvents() {
        eventManager.subscribe(subscriber1);
        eventManager.subscribe(subscriber2);

        eventManager.notifying(EventType.StartGame, new EmptyEvent() {});

        verify(subscriber1).accept(any(Event.class));
        verify(subscriber2).accept(any(Event.class));

        eventManager.unsubscribe(subscriber1);

        eventManager.notifying(EventType.EndGame, new EmptyEvent() {});

        verify(subscriber1, times(1)).accept(any(Event.class));
        verify(subscriber2, times(2)).accept(any(Event.class));
    }

    @Test
    public void testSubscribe() {
        assertEquals(0, eventManager.getSubscribers().size());

        eventManager.subscribe(subscriber1);
        assertEquals(EventType.values().length, eventManager.getSubscribers().size());

        eventManager.subscribe(subscriber2);
        assertEquals(EventType.values().length * 2, eventManager.getSubscribers().size());

        eventManager.unsubscribe(subscriber2);
        assertEquals(EventType.values().length, eventManager.getSubscribers().size());
    }

    @Test
    public void testSingleEventTypeSubscribe() {
        assertEquals(0, eventManager.getSubscribers().size());
        eventManager.subscribe(subscriber1, EventType.BuyCards);
        assertEquals(1, eventManager.getSubscribers().size());
    }

    @Test
    public void subscribeToSpecificEvent() {
        eventManager.subscribe(subscriber1, EventType.BuyCards);
        eventManager.notifying(EventType.BuyCards, new BuyEvent(CardType.Entdecker));
        verify(subscriber1).accept(any(BuyEvent.class));
    }

    @Test
    public void unsubscribeToSpecificEvent() {
        eventManager.subscribe(subscriber1, EventType.BuyCards);
        eventManager.unsubscribe(subscriber1, EventType.BuyCards);

        eventManager.notifying(EventType.BuyCards, new BuyEvent(CardType.Entdecker));
        verify(subscriber1, times(0)).accept(any(BuyEvent.class));
    }

    @Test
    public void testClear() {
        eventManager.subscribe(subscriber1, EventType.BuyCards);
        eventManager.resetSubscribers();

        eventManager.notifying(EventType.BuyCards, new BuyEvent(CardType.Entdecker));
        verify(subscriber1, times(0)).accept(any(BuyEvent.class));
    }
}