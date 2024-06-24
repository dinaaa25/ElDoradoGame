package org.utwente.player.view.gui;

import javax.swing.*;

import org.utwente.CaveCoin.PlayCaveCoins;
import org.utwente.game.model.Phase;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import java.awt.*;

public class PlayerDeck extends JPanel {
  int col = 0;
  Phase phase;

  public PlayerDeck(Player player, Phase phase) {
    super(new BorderLayout());
    this.phase = phase;
    addPlayerRow(player);
    addDiscardPile();
    addDeck(player);
    addDrawPile();
    if (phase.getActionMessage() != null) {
      this.setNotification(phase.getActionMessage());
    }
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  public void addPlayerRow(Player player) {
    JPanel playerRow = new JPanel();
    playerRow.setLayout(new FlowLayout());

    JButton makeMoveButton = new JButton("Make Move");
    makeMoveButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.MakeMove));
    playerRow.add(makeMoveButton);

    JLabel name = new JLabel(String.format("Current Player: %s (%s)", player.getName(), player.getColor().name()));
    name.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    name.setFont(PlayerConfig.NAME_FONT);
    playerRow.add(name);

    JLabel phase = new JLabel(String.format("Current Phase: %s", this.phase.getCurrentPhase().toString()));
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
    this.add(new PlayCards(player.getPlayPile(), phase), BorderLayout.CENTER);
    this.add(new PlayCaveCoins(player.getCaveCoinPile(), phase), BorderLayout.SOUTH);
  }

  public void setNotification(ValidationResult notification) {
    System.out.println("Notification:");
    System.out.println(notification);
    this.add(new JLabel(notification.getMessage()), BorderLayout.SOUTH);
  }

}