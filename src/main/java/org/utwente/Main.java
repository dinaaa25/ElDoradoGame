package org.utwente;

import lombok.Getter;
import org.utwente.Board.Board;
import org.utwente.Board.BoardController;
import org.utwente.Board.BoardView;
import org.utwente.Board.Path;
import org.utwente.Tile.TileImageLoader;
import org.utwente.game.model.Game;
import org.utwente.game.controller.GameController;
import org.utwente.game.view.GameGui;
import org.utwente.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JPanel {
    @Getter
    private final GameController gameController;
    private TileImageLoader tileImageLoader;
    @Getter
    private int offsetX;
    @Getter
    private int offsetY;

    public Main() {
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        Board board = boardBuilder.selectPath(Path.HillsOfGold).buildPath().addBlockades().build();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        gameController = new GameController(new Game("ElDorado", "Welcome to El Dorado Game", board, List.of(player1, player2)), new GameGui(), 1);
        loadImages();
        calculatePreferredSize(board);
    }

    private void loadImages() {
        TileImageLoader tileImageLoader = new TileImageLoader();
        tileImageLoader.loadTileImages();
        this.tileImageLoader = tileImageLoader;
    }

    public void calculatePreferredSize(Board board) {
        BoardView boardView = new BoardView();
        Dimension preferredSize = boardView.calculatePreferredSize(board);
        setPreferredSize(preferredSize);

        Point offsets = boardView.calculateOffsets(board);
        offsetX = offsets.x;
        offsetY = offsets.y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Game game = gameController.getGame();
        BoardController boardController = new BoardController(game.getBoard(), new BoardView());
        boardController.updateView(g2d, offsetX, offsetY, game.getBoard().isFlatTop(), tileImageLoader);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainPanel = new Main();
            GameController gameController = mainPanel.getGameController();
            JFrame frame = new JFrame(gameController.getGame().getGameName());
            gameController.getGame().placePlayersStart();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JScrollPane scrollPane = new JScrollPane(mainPanel);
            frame.add(scrollPane);

            frame.setSize(mainPanel.getPreferredSize());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}