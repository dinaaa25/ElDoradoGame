package org.utwente.game.view.gui;

import javax.swing.*;

import org.utwente.game.view.GameConfig;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import java.awt.*;

public class GameStart extends JPanel {
  String welcomeString = "Welcome To El Dorado Game.";

  public GameStart() {
    super();
    this.setLayout(new GridLayout(2, 1));
    JLabel title = new JLabel(welcomeString);
    title.setFont(GameConfig.GAME_TITLE);
    title.setForeground(GameConfig.GAME_TITLE_COLOR);
    this.add(title);
    JButton startButton = new JButton("Click to start the adventure.");
    startButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.StartGame));
    this.add(startButton);
  }
}
