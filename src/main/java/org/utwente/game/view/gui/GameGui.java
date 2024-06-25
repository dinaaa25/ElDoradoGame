package org.utwente.game.view.gui;

import java.awt.*;
import javax.swing.*;

import org.slf4j.LoggerFactory;
import org.utwente.Board.Board;
import org.utwente.Board.BoardView;
import org.utwente.Tile.TileView;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameView;
import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketGui;
import org.utwente.player.view.gui.PlayerDeck;
import org.utwente.util.ValidationResult;

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
    PlayerDeck playerDeck;
    JScrollPane boardViewScrollPane;
    JScrollPane marketViewScrollPane;

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
        if (game == null || game.getBoard() == null)
            return;
        BoardView boardView = new BoardView(game.getBoard(), this.game.getPhase());
        boardViewScrollPane = new JScrollPane(boardView);
        this.add(boardViewScrollPane, BorderLayout.CENTER);
        boardViewScrollPane.getViewport().setViewPosition(getViewportPosition(boardView));
    }

    private Point getViewportPosition(BoardView boardView) {
        TileView firstTile = new TileView(game.getBoard().getTileOfPlayer(game.getCurrentPlayer()),
                game.getBoard().isFlatTop(), false);
        Point offsets = boardView.calculateOffsets(game.getBoard());
        Point tileOffset = firstTile.hexagonToPixel(game.getBoard().isFlatTop(),
                game.getBoard().getStartingTiles().getFirst());
        return new Point(offsets.x + tileOffset.x, offsets.y + tileOffset.y);
    }

    public void addMarket() {
        if (game.getMarket() != null) {
            MarketGui marketGui = new MarketGui();
            Market market = this.game.getMarket();
            new MarketController(marketGui, market);
            marketViewScrollPane = marketGui.getMainComponent();
            marketViewScrollPane.setPreferredSize(new Dimension(600, 150));
            this.add(marketViewScrollPane, BorderLayout.WEST);
        }
    }

    public void addPlayerSection() {
        this.playerDeck = new PlayerDeck(game.getCurrentPlayer(), game.getPhase());
        this.add(this.playerDeck, BorderLayout.SOUTH);
    }

    @Override
    public void showMessage(ValidationResult message) {
        this.remove(playerDeck);
        this.playerDeck = new PlayerDeck(game.getCurrentPlayer(), game.getPhase());
        this.add(this.playerDeck, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
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
        this.add(new BoardForm(), BorderLayout.CENTER);
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

    @Override
    public void setCurrentPlayer() {
        this.remove(playerDeck);
        this.addPlayerSection();
        this.revalidate();
        this.repaint();
    }

    @Override
    public void setCurrentPhase() {
        // set the current phase.
        // reuse setCurrentPlayer for now to redraw.
        this.setCurrentPlayer();
    }

    @Override
    public void redraw() {
        this.remove(playerDeck);
        this.remove(boardViewScrollPane);
        this.remove(marketViewScrollPane);

        this.addBoard();
        this.addPlayerSection();
        this.addMarket();

        this.revalidate();
        this.repaint();
    }

}