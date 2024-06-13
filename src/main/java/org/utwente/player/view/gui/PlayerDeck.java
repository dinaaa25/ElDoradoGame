package org.utwente.player.view.gui;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.utwente.market.model.Card;
import org.utwente.player.model.Player;

public class PlayerDeck extends JPanel {
  int col = 0;

  public PlayerDeck(Player player) {
    super(new BorderLayout());
    addPlayerName(player);
    addDiscardPile();
    addDeck(player);
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  public void addPlayerName(Player player) {
    this.add(new Label(String.format("Current Player: %s", player.getName())), BorderLayout.NORTH);
  }

  public void addDiscardPile() {
    this.add(new DiscardCard(), BorderLayout.EAST);
  }

  private void addDeck(Player player) {
    this.add(new PlayCards(player.getPlayPile()), BorderLayout.CENTER);
  }

}