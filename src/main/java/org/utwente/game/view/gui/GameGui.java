package org.utwente.game.view.gui;

import java.awt.*;
import javax.swing.*;

import org.slf4j.LoggerFactory;
import org.utwente.Board.Board;
import org.utwente.Board.BoardView;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameView;
import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketGui;
import org.utwente.player.model.Player;
import org.utwente.player.view.gui.PlayerDeck;
import ch.qos.logback.classic.Logger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameGui extends JPanel implements GameView {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GameGui.class);
    Game game;
    int offsetX;
    int offsetY;

    public GameGui() {
        super();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void calculatePreferredSize(Board board) {
        if (board != null) {
            BoardView boardView = new BoardView(board);
            Dimension preferredSize = boardView.calculatePreferredSize(board);
            setPreferredSize(preferredSize);

            Point offsets = boardView.calculateOffsets(board);
            offsetX = offsets.x;
            offsetY = offsets.y;
        }
    }

    public void setupGame() {
        calculatePreferredSize(this.game.getBoard());
        this.setLayout(new BorderLayout());
        this.addBoard();
        this.addMarket();
        this.addPlayerSection();
    }

    public void startScreen() {

    }

    public void setupPlayerForm() {
        this.add(new PlayerForm(), BorderLayout.CENTER);
    }

    public void addBoard() {
        if (this.game != null && this.game.getBoard() != null) {
            logger.info("Adding board");
            BoardView boardView = new BoardView(this.game.getBoard());
            JScrollPane scrollPane = new JScrollPane(boardView);
            this.add(scrollPane, BorderLayout.CENTER);
            logger.info(String.format("offset x: %d, offset y: %d", offsetX, offsetY));
            scrollPane.getViewport().setViewPosition(new Point(offsetX, offsetY));
        }
    }

    public void addMarket() {
        if (game.getMarket() != null) {
            MarketGui marketGui = new MarketGui();
            Market market = this.game.getMarket();
            new MarketController(marketGui, market);
            JComponent marketComponent = marketGui.getMainComponent();
            marketComponent.setPreferredSize(new Dimension(600, 150));
            this.add(marketComponent, BorderLayout.WEST);
        }
    }

    public void addPlayerSection() {
        this.add(new PlayerDeck(new Player("Stijn")), BorderLayout.SOUTH);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setStageStart() {
        this.add(new GameStart(), BorderLayout.CENTER);
    }

    @Override
    public void setPlayerSetup() {
        this.removeAll();

        this.setupPlayerForm();

        this.revalidate();
        this.repaint();
    }

    void setupBoardForm() {
        this.add(new BoardForm());
    }

    @Override
    public void setBoardSetup() {
        this.removeAll();

        this.setupBoardForm();

        this.revalidate();
        this.repaint();
    }

    @Override
    public void setGameStage() {
        this.removeAll();

        this.setupGame();

        this.revalidate();
        this.repaint();
    }

}