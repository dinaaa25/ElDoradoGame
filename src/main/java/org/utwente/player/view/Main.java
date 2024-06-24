package org.utwente.player.view;

import javax.swing.JFrame;

import org.utwente.game.model.Phase;
import org.utwente.player.model.Player;
import org.utwente.player.view.gui.PlayerDeck;

public class Main {
  static JFrame f;

  public static void main(String[] args) {
    f = new JFrame();
    f.add(new PlayerDeck(new Player("Larry"), new Phase()));
    f.setSize(800, 400);
    f.setVisible(true);
  }
}