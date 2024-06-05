package org.utwente.player.view.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.utwente.market.model.Card;
import org.utwente.player.model.Pile;
import org.utwente.player.model.Player;

public class PlayerDeck extends JPanel {
  int col = 0;

  public PlayerDeck(Player player) {
    super(new GridBagLayout());
    addPlayerName(player);
    addDiscardPile();
    addDeck(player);
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  public void addPlayerName(Player player) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridwidth = 10;
    c.gridx = 0;
    c.gridy = 0;
    this.add(new Label(player.getName()), c);
  }

  public void addDiscardPile() {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 1;
    c.gridx = 9;
    c.gridy = 1;
    this.add(new Label("Discard Pile"));
  }

  public void addDeck(Player player) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 1;
    c.gridy = 1;

    Pile playPile = player.getPlayPile();
    for (Card card : playPile.getCards()) {
      c.gridx = col;
      this.add(new Label(card.getCardType().name()), c);
      col++;
    }
  }

}