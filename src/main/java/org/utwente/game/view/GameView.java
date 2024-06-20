package org.utwente.game.view;

import org.utwente.game.model.Game;

public interface GameView {

    void showMessage(String message);

    void setGame(Game game);

    void setStageStart();

    void setPlayerSetup();

    void setBoardSetup();

    void setGameStage();

}
