package org.utwente.player.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.player.model.Player;
import org.utwente.player.view.PlayerCli;
import org.utwente.player.view.PlayerView;
import static org.junit.jupiter.api.Assertions.assertSame;


class PlayerControllerTest {
    /**
     * Method under test: {@link PlayerController#PlayerController(List, PlayerView)}
     */
    @Test
    void testNewPlayerController() {
        // Arrange
        List<Player> playerModel = new ArrayList<>();
        PlayerView playerView = new PlayerCli();

        // Act
        PlayerController playerController = new PlayerController(playerModel, playerView);

        // Assert
        assertSame(playerModel, playerController.getPlayerModel());
        assertSame(playerView, playerController.getPlayerView());
    }
}