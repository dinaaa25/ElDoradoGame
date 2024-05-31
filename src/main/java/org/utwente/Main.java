package org.utwente;

import lombok.Getter;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.game.Game;
import org.utwente.game.GameController;
import org.utwente.game.GameView;
import org.utwente.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Main extends JPanel {
    @Getter
    private final GameController gameController;
    private BufferedImage macheteImage;
    private static final int PANEL_SIZE = 1500;

    public Main() {
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        Board board = boardBuilder.selectPath(Path.HillsOfGold).buildPath().build();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        gameController = new GameController(new Game("ElDorado", "Welcome to El Dorado Game", board, List.of(player1, player2)), new GameView());
        loadImages();
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
    }

    private void loadImages() {
        try {
            macheteImage = ImageIO.read(getClass().getResource("/images/machete-bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int offsetX = getWidth() / 2;
        int offsetY = getHeight() / 2;

        g2d.translate(offsetX, offsetY);

        gameController.updateView(g2d, macheteImage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainPanel = new Main();
            JFrame frame = new JFrame(mainPanel.getGameController().getGame().getGameName());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JScrollPane scrollPane = new JScrollPane(mainPanel);
            frame.add(scrollPane);

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}