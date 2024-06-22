package org.utwente;

import lombok.Getter;

import org.slf4j.LoggerFactory;
import org.utwente.game.model.Configuration;
import org.utwente.game.controller.GameController;
import org.utwente.game.view.GameCLI;
import org.utwente.game.view.GameView;
import org.utwente.game.view.gui.GameFrame;
import org.utwente.game.view.gui.GameGui;

import ch.qos.logback.classic.Level;

@Getter
public class Main {

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

        // required to run the constructor of the GameController
        new GameController(view);

        if (config.gui) {
            assert view instanceof GameGui;
            (new GameFrame((GameGui) view)).display();
        }
    }
}