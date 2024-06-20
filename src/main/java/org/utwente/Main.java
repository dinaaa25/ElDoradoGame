package org.utwente;

import lombok.Getter;

import org.slf4j.LoggerFactory;
import org.utwente.game.model.Configuration;
import org.utwente.game.controller.GameController;
import org.utwente.game.view.GameCLI;
import org.utwente.game.view.GameFrame;
import org.utwente.game.view.GameGui;
import org.utwente.game.view.GameView;

import ch.qos.logback.classic.Level;

@Getter
public class Main {
    private int offsetX;
    private int offsetY;

    private static void setLoggingLevel(String level) {
        Level loggingLevel;
        try {
            loggingLevel = Level.valueOf(level);
        } catch (Exception e) {
            loggingLevel = Level.ALL;
        }
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ROOT")).setLevel(loggingLevel);
    }

    public static void main(String[] args) {
        Configuration config = Configuration.getInstance();
        setLoggingLevel(config.loggingLevel);

        GameView view;

        if (config.gui) {
            view = new GameGui();
        } else {
            view = new GameCLI();
        }

        GameController gameController = new GameController(view);
        gameController.getGame().placePlayersStart();

        if (config.gui) {
            (new GameFrame((GameGui) view)).display();
        }
    }
}