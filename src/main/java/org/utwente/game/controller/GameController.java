package org.utwente.game.controller;

import lombok.Getter;

import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameView;
import org.utwente.player.model.Player;
import java.util.*;

@Getter
public class GameController {
    private final Game game;
    private final GameView gameView;

    public GameController(GameView gameView) {
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        Board board = boardBuilder.selectPath(Path.HillsOfGold).buildPath().addCaveCoinTiles().addBlockades().build();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        this.game = new Game("ElDorado", "Welcome to El Dorado Game", board, List.of(player1, player2));
        assert this.game.getBoard() != null;
        System.out.println(this.game.getBoard());
        this.gameView = gameView;
        this.gameView.setGame(game);
    }

    void gameSetup() {

    }

    @Override
    public boolean equals(Object subscriber) {
        if (!(subscriber instanceof GameController)) {
            return false;
        }

        GameController subscriberController = (GameController) subscriber;

        return false;
    }

    // @Override
    // public void update(EventType event) {
    // if (event == EventType.EndGame) {
    // game.setFinish(true);
    // } else if (event == EventType.StartGame) {
    // gameView.showMessage("Welcome to the game el Dorado");
    // }
    // }

}