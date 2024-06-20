package org.utwente;

import lombok.Getter;
import org.utwente.Board.Board;
import org.utwente.Board.BoardView;
import org.utwente.Board.Path;
import org.utwente.game.model.Game;
import org.utwente.game.controller.GameController;
import org.utwente.game.view.GameGui;
import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketGui;
import org.utwente.player.model.Player;
import org.utwente.player.view.gui.PlayerDeck;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
public class Main extends JPanel {
    private final GameController gameController;
    private int offsetX;
    private int offsetY;

    public Main() {
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        Board board = boardBuilder.selectPath(Path.HillsOfGold).buildPath().addCaveCoinTiles().addBlockades().build();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        gameController = new GameController(
                new Game("ElDorado", "Welcome to El Dorado Game", board, List.of(player1, player2)), new GameGui());
        calculatePreferredSize(board);
    }

    public void calculatePreferredSize(Board board) {
        BoardView boardView = new BoardView(board);
        Dimension preferredSize = boardView.calculatePreferredSize(board);
        setPreferredSize(preferredSize);

        Point offsets = boardView.calculateOffsets(board);
        offsetX = offsets.x;
        offsetY = offsets.y;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            GameController gameController = main.getGameController();
            JFrame frame = new JFrame(gameController.getGame().getGameName());
            gameController.getGame().placePlayersStart();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel borderPanel = new JPanel(new BorderLayout());

            // MVC Board
            BoardView boardView = new BoardView(gameController.getGame().getBoard());
            JScrollPane scrollPane = new JScrollPane(boardView);
            borderPanel.add(scrollPane, BorderLayout.CENTER);
            scrollPane.getViewport().setViewPosition(new Point(main.getOffsetX(), main.getOffsetY()));

            // MVC Market:
            MarketGui marketGui = new MarketGui();
            Market market = gameController.getGame().getMarket();
            new MarketController(marketGui, market);
            JComponent marketComponent = marketGui.getMainComponent();
            marketComponent.setPreferredSize(new Dimension(600, 150));
            borderPanel.add(marketComponent, BorderLayout.WEST);

            // MVC Player Cards
            borderPanel.add(new PlayerDeck(new Player("Stijn")), BorderLayout.SOUTH);

            frame.add(borderPanel);

            frame.setSize(main.getPreferredSize());
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        });
    }
}