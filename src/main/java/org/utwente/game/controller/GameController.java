package org.utwente.game.controller;

import lombok.Getter;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameView;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.utwente.game.view.EventManager.EventType;

@Getter
public class GameController implements Subscriber {
    private final Game game;
    private final GameView gameView;
    private int controllerId;

    public GameController(Game game, GameView gameView, int controllerId) {
        this.game = game;
        this.gameView = gameView;
        this.controllerId = controllerId;
    }

    @Override
    public boolean equals(Object subscriber) {
        if(!(subscriber instanceof GameController)) {
            return false;
        }

        GameController subscriberController  = (GameController) subscriber;

        if(subscriberController.getControllerId() == controllerId) {
            return true;
        }
        return false;
    }


    @Override
    public void update(EventType event) {
        if(event == EventType.EndGame) {
            game.setFinish(true);
        } else if (event == EventType.StartGame) {
            gameView.showMessage("Welcome to the game el Dorado");
        }
    }

}