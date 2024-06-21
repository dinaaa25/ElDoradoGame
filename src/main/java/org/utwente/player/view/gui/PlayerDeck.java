package org.utwente.player.view.gui;

import java.awt.*;

import javax.swing.*;

import org.utwente.game.model.PhaseType;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

public class PlayerDeck extends JPanel {
  int col = 0;
  PhaseType phaseType;

  public PlayerDeck(Player player, PhaseType phase) {
    super(new BorderLayout());
    phaseType = phase;
    addPlayerRow(player);
    addDiscardPile();
    addDeck(player);
    addDrawPile();
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  public void addPlayerRow(Player player) {
    JPanel playerRow = new JPanel();
    playerRow.setLayout(new FlowLayout());
    JLabel name = new JLabel(String.format("Current Player: %s", player.getName()));
    name.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    name.setFont(PlayerConfig.NAME_FONT);
    playerRow.add(name);

    JLabel phase = new JLabel(String.format("Current Phase: %s", phaseType.toString()));
    name.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    name.setFont(PlayerConfig.NAME_FONT);
    playerRow.add(phase);

    JButton nextTurnButton = new JButton("Next Turn");
    nextTurnButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.NextTurn));
    playerRow.add(nextTurnButton);

    JButton nextPhaseButton = new JButton("Next Phase");
    nextPhaseButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.NextPhase));
    playerRow.add(nextPhaseButton);

    this.add(playerRow, BorderLayout.NORTH);
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