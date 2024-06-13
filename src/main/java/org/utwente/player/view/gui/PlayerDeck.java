package org.utwente.player.view.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.utwente.player.model.Player;

public class PlayerDeck extends JPanel {
  int col = 0;

  public PlayerDeck(Player player) {
    super(new BorderLayout());
    addPlayerName(player);
    addDiscardPile();
    addDeck(player);
    addDrawPile();
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  public void addPlayerName(Player player) {
    JLabel name = new JLabel(String.format("Current Player: %s", player.getName()));
    name.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    name.setFont(PlayerConfig.NAME_FONT);
    this.add(name, BorderLayout.NORTH);
  }

  public void addDrawPile() {
    this.add(new DrawPile(), BorderLayout.WEST);
  }

  public void addDiscardPile() {
    this.add(new DiscardCard(), BorderLayout.EAST);
  }

  private void addDeck(Player player) {
    this.add(new PlayCards(player.getPlayPile()), BorderLayout.CENTER);
  }

}