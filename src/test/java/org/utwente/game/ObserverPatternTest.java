package org.utwente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.game.controller.GameController;
import org.utwente.game.controller.Subscriber;
import org.utwente.game.model.Game;
import org.utwente.game.view.EventManager;
import org.utwente.game.view.GameCLI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ObserverPatternTest {
    Game game;

    GameController gameController;

    GameCLI gameView;
    Subscriber subscriber1;
    Subscriber subscriber2;

    EventManager eventManager;


    @BeforeEach
    public void setup() {
        game = mock(Game.class);
        gameView = mock(GameCLI.class);
        subscriber1 = mock(Subscriber.class);
        subscriber2 = mock(Subscriber.class);
        gameController = new GameController(game, gameView, 1);
        eventManager = EventManager.getEventManager();
        eventManager.resetSubscribers();
    }

    @Test
    public void testUpdate() {
        gameController.update(EventManager.EventType.StartGame);
        verify(gameView).showMessage("Welcome to the game el Dorado");

        gameController.update(EventManager.EventType.EndGame);
        verify(game).setFinish(true);
    }

    @Test
    public void testEqualSubscriber() {
        GameController gameController1 = new GameController(game, gameView, 1);
        GameController gameController2 = new GameController(game, gameView, 2);
        assertTrue(gameController.equals(gameController1));
        assertFalse(gameController.equals(gameController2));
    }

    @Test
    public void testNotifying() {
        eventManager.subscribe(subscriber1);
        eventManager.subscribe(subscriber2);

        eventManager.notifying(EventManager.EventType.StartGame);

        verify(subscriber1).update(EventManager.EventType.StartGame);
        verify(subscriber2).update(EventManager.EventType.StartGame);

        eventManager.unsubscribe(subscriber1);

        eventManager.notifying(EventManager.EventType.EndGame);

        verify(subscriber1, times(0)).update(EventManager.EventType.EndGame);
        verify(subscriber2, times(1)).update(EventManager.EventType.EndGame);
    }

    @Test
    public void testSubscribe() {
        assertEquals(0, eventManager.getSubscribers().size());

        eventManager.subscribe(subscriber1);
        assertEquals(1, eventManager.getSubscribers().size());

        eventManager.subscribe(subscriber2);
        assertEquals(2, eventManager.getSubscribers().size());

        eventManager.unsubscribe(subscriber2);
        assertEquals(1, eventManager.getSubscribers().size());

    }


}
