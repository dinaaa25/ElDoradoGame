package org.utwente.player.view.gui;

import javax.swing.*;

import org.utwente.CaveCoin.PlayCaveCoins;
import org.utwente.game.model.Phase;
import org.utwente.game.model.PhaseType;
import org.utwente.market.model.Card;
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
    addDiscardPile(player);
    addDeck(player);
    addDrawPile(player);
    if (phase.getActionMessage() != null) {
      this.setNotification(phase.getActionMessage());
    }
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  public void addPlayerRow(Player player) {
    JPanel playerRow = new JPanel();
    playerRow.setLayout(new FlowLayout());

    JButton actionButton = new JButton();
    updateActionButton(actionButton, phase, player);
    playerRow.add(actionButton);

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

    // only one step to be iterated:
    if (this.phase.getCurrentPhase().getIndex() == 0) {
      JButton nextPhaseButton = new JButton("Next Phase");
      nextPhaseButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.NextPhase));
      playerRow.add(nextPhaseButton);
    }

    this.add(playerRow, BorderLayout.NORTH);
  }

  private void updateActionButton(JButton actionButton, Phase phase, Player player) {
    if (this.phase.getCurrentPhase() == PhaseType.BUYING_AND_PLAYING_PHASE) {
      actionButton.setText("Make Move");
      actionButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.MakeMove));
    } else if (phase.getCurrentPhase() == PhaseType.DISCARD_PHASE) {
      actionButton.setText("Discard Currently Selected Cards");
      actionButton.addActionListener(l -> {
        EventManager.getInstance().notifying(EventType.DiscardCards);
      });
    } else if (phase.getCurrentPhase() == PhaseType.DRAW_PHASE) {
      actionButton.setText("Draw Cards");
      actionButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.DrawCards));
    } else {
      actionButton.setText("Action Not Available");
      actionButton.setEnabled(false);
    }
  }

  public void addDrawPile(Player player) {
    int sizeOfDrawPile = player.getDrawPile().getCards().size();
    this.add(new DrawPile(sizeOfDrawPile), BorderLayout.WEST);
  }

  public void addDiscardPile(Player player) {
    int sizeOfDiscardPile = player.getDiscardPile().getCards().size();
    this.add(new DiscardCard(sizeOfDiscardPile), BorderLayout.EAST);
  }

  private void addDeck(Player player) {
    this.add(new PlayCards(player.getPlayPile(), phase), BorderLayout.CENTER);
    this.add(new PlayCaveCoins(player.getCaveCoinPile(), phase), BorderLayout.SOUTH);
  }

  public void setNotification(ValidationResult notification) {
    this.add(new JLabel(notification.getMessage()), BorderLayout.SOUTH);
  }

}