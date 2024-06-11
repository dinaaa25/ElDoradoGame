package org.utwente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.utwente.game.controller.GameController;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameCLI;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ObserverPatternTest {
    Game game;

    GameController gameController;

    GameCLI gameView;
    Consumer<String> subscriber1;
    Consumer<String> subscriber2;

    EventManager eventManager;

    @BeforeEach
    public void setup() {
        game = mock(Game.class);
        gameView = mock(GameCLI.class);
        subscriber1 = mock(Consumer.class);
        subscriber2 = mock(Consumer.class);
        // gameController = new GameController(game, gameView);
        eventManager = EventManager.getInstance();
    }

    @Test
    public void checkAllEventsAreAvailable() {
        assertNotEquals(0, eventManager.getEventTypes());
    }

    @Test
    public void testNotifyingAllEvents() {
        eventManager.subscribe(subscriber1);
        eventManager.subscribe(subscriber2);

        eventManager.notifying(EventType.StartGame);

        verify(subscriber1).accept("");
        verify(subscriber2).accept("");

        eventManager.unsubscribe(subscriber1);

        eventManager.notifying(EventType.EndGame);

        verify(subscriber1, times(1)).accept("");
        verify(subscriber2, times(2)).accept("");
    }

    @Test
    public void testSubscribe() {
        assertEquals(0, eventManager.getSubscribers().size());

        eventManager.subscribe(subscriber1);
        assertEquals(5, eventManager.getSubscribers().size());

        eventManager.subscribe(subscriber2);
        assertEquals(10, eventManager.getSubscribers().size());

        eventManager.unsubscribe(subscriber2);
        assertEquals(5, eventManager.getSubscribers().size());

    }

    @Test
    public void subscribeToSpecificEvent() {
        eventManager.subscribe(subscriber1, EventType.BuyCards);
        eventManager.notifying(EventType.BuyCards, "buy entdecker");
        verify(subscriber1).accept("buy entdecker");
    }

    @Test
    public void unsubscribeToSpecificEvent() {
        eventManager.subscribe(subscriber1, EventType.BuyCards);
        eventManager.unsubscribe(subscriber1, EventType.BuyCards);

        eventManager.notifying(EventType.BuyCards, "buy entdecker");
        verify(subscriber1, times(0)).accept("buy entdecker");
    }

}
