package org.utwente.game.view;

import java.awt.*;
import javax.swing.*;

import org.utwente.Board.BoardView;
import org.utwente.game.model.Game;
import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketGui;
import org.utwente.player.model.Player;
import org.utwente.player.view.gui.PlayerDeck;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameGui extends JPanel implements GameView {
    Game game;
    int offsetX;
    int offsetY;

    public GameGui(Game game) {
        super();
        this.game = game;
        this.setLayout(new BorderLayout());
        this.addBoard();
        this.addMarket();
        this.addPlayerSection();
    }

    public void addBoard() {
        BoardView boardView = new BoardView(this.game.getBoard());
        JScrollPane scrollPane = new JScrollPane(boardView);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setViewPosition(new Point(offsetX, offsetY));
    }

    public void addMarket() {
        MarketGui marketGui = new MarketGui();
        Market market = this.game.getMarket();
        new MarketController(marketGui, market);
        JComponent marketComponent = marketGui.getMainComponent();
        marketComponent.setPreferredSize(new Dimension(600, 150));
        this.add(marketComponent, BorderLayout.WEST);
    }

    public void addPlayerSection() {
        this.add(new PlayerDeck(new Player("Stijn")), BorderLayout.SOUTH);
    }

    @Override
    public void showMessage(String message) {

    }

}