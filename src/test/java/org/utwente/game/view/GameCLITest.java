package org.utwente.game.view;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.utwente.game.model.Game;
import org.utwente.util.event.Event;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

import java.util.List;

class GameCLITest {
    /**
     * Method under test: {@link GameCLI#start()}
     */
    @Test
    @Disabled("TODO: fix gameCLI.start")
    void testStart() {
        // Arrange
        Game game = Mockito.mock(Game.class);
        EventManager eventManager = Mockito.mock(EventManager.class);
        GameCLI gameCLI = new GameCLI();

        // Capture console output for verification
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Setup scanner input (simulating user input)
        String input = "start\nexit\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        // Mock behavior for notifying
        doNothing().when(eventManager).notifying(any(EventType.class));

        // Create mock subscribers and simulate their responses
        Consumer<Event> mockSubscriber = mock(Consumer.class);

        // Assume these subscribers are subscribed to StartGame and EndGame events
        when(eventManager.getSubscribers()).thenReturn(List.of(mockSubscriber));

        // Act
        gameCLI.start();

        // Assert
        // Check that the appropriate events are triggered
        verify(eventManager, times(1)).notifying(EventType.StartGame);
        verify(eventManager, times(1)).notifying(EventType.EndGame);

        // Verify that subscribers are called with the expected data
        //verify(mockSubscriber, times(1)).accept(Event); // Check for any specific string if required

        // Check console output for expected messages
        String output = outContent.toString();
        assertTrue(output.contains("Expected message when game starts"));
        assertTrue(output.contains("Expected message when game exits"));

        // Reset System.in and System.out to their original values
        System.setOut(System.out);
        System.setIn(System.in);
    }
}