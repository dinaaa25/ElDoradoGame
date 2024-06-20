package org.utwente.game.view.gui;

import java.awt.Dimension;

import javax.swing.*;

public class GameFrame extends JFrame {

  public GameFrame(JPanel gamePanel) {
    super("El Dorado");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(gamePanel);
    this.setLocationRelativeTo(null);
    this.setMinimumSize(new Dimension(400, 400));
    this.pack();
  }

  public void display() {
    this.setVisible(true);
  }
}
