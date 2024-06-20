package org.utwente.game.controller;

import lombok.Getter;

import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameView;
import org.utwente.player.model.Player;
import org.utwente.util.event.AddPlayersEvent;
import org.utwente.util.event.Event;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.PickBoardEvent;

import java.util.*;

@Getter
public class GameController {
    private final Game game;
    private final GameView gameView;

    public GameController(GameView gameView) {
        this.game = new Game();
        this.gameView = gameView;
        this.gameView.setGame(game);

        // put the game on the starting page:
        this.gameView.setStageStart();
        // register event listeners:
        EventManager eventManager = EventManager.getInstance();
        eventManager.subscribe(this::onGameStart, EventType.StartGame);
        eventManager.subscribe(this::onPlayersAdded, EventType.AddPlayers);
        eventManager.subscribe(this::onPickBoard, EventType.PickBoard);
    }

    /**
     * handle click on the start game button.
     * 
     * @param event
     */
    void onGameStart(Event event) {
        this.gameView.setPlayerSetup();
    }

    void onPlayersAdded(Event event) {
        if (event instanceof AddPlayersEvent) {
            AddPlayersEvent eventData = (AddPlayersEvent) event;
            this.game.setPlayers(eventData.getPlayerNames().stream().map(name -> new Player(name)).toList());
            this.gameView.setBoardSetup();
        }
    }

    void onPickBoard(Event event) {
        if (event instanceof PickBoardEvent) {
            PickBoardEvent data = (PickBoardEvent) event;
            Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
            Board board = boardBuilder.selectPath(data.getPath()).buildPath().addCaveCoinTiles().addBlockades()
                    .build();
            this.game.setBoard(board);
            this.game.placePlayersStart();
            this.gameView.setGameStage();
        }
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