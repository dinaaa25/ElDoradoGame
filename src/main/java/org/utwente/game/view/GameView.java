package org.utwente.game.view;

import org.utwente.game.model.Game;
import org.utwente.util.ValidationResult;

public interface GameView {

    void showMessage(ValidationResult message);

    void setGame(Game game);

    void setStageStart();

    void setPlayerSetup();

    void setBoardSetup();

    void setGameStage();

    void setCurrentPlayer();

    void setCurrentPhase();

    void redraw();

}
