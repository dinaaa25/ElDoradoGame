package org.utwente.game.view;

import org.utwente.game.model.Game;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventManager;

import lombok.Getter;
import lombok.Setter;

import java.io.PrintStream;
import java.util.Scanner;

@Getter
@Setter
public class GameCLI implements GameView {
    Scanner scanner = new Scanner(System.in);
    Game game;
    boolean isExited;
    EventManager eventManager;
    private PrintStream stream;

    public GameCLI() {
    }

    public void start() {
        // EventType eventT = null;
        // while (!isExited) {
        // String input = scanner.nextLine();
        // if (input.equals("exit")) {
        // isExited = true;
        // eventT = EventType.EndGame;
        // eventManager.notifying(eventT);
        // } else if (input.equals("start")) {
        // eventT = EventType.StartGame;
        // eventManager.notifying(eventT);
        // }
        // }

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void setStageStart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStageStart'");
    }

    @Override
    public void setPlayerSetup() {

    }

    @Override
    public void setBoardSetup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBoardSetup'");
    }

    @Override
    public void setGameStage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGameStage'");
    }

    @Override
    public void setCurrentPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCurrentPlayer'");
    }

    @Override
    public void setCurrentPhase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCurrentPhase'");
    }

    @Override
    public void redrawBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'redrawBoard'");
    }
}
