package org.utwente.game.controller;

import lombok.Getter;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameView;

@Getter
public class GameController {
    private final Game game;
    private final GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
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