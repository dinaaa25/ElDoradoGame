
package org.utwente.player.view.gui;

import javax.swing.*;
        import java.awt.*;
        import java.util.ArrayList;
import java.util.List;
import org.utwente.CaveCoin.PlayCaveCoins;
import org.utwente.game.model.Phase;
import org.utwente.game.model.PhaseType;
import org.utwente.market.model.Card;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

public class PlayerDeck extends JPanel {
  int col = 0;
  Phase phase;
  private List<JButton> actionButtons = new ArrayList<>();
  private JButton nextTurnButton;

  public PlayerDeck(Player player, Phase phase) {
    super(new BorderLayout());
    this.phase = phase;
    addPlayerRow(player);
    addDeck(player);
    addDrawPile(player);
    addDiscardPanel(player);
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
    actionButtons.add(actionButton); // Store reference to the action button

    JLabel name = new JLabel(String.format("Current Player: %s (%s)", player.getName(), player.getColor().name()));
    name.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    name.setFont(PlayerConfig.NAME_FONT);
    playerRow.add(name);

    JLabel phase = new JLabel(String.format("Current Phase: %s", this.phase.getCurrentPhase().toString()));
    phase.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    phase.setFont(PlayerConfig.NAME_FONT);
    playerRow.add(phase);

    nextTurnButton = new JButton("Next Turn");
    nextTurnButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.NextTurn));
    playerRow.add(nextTurnButton);

    JButton nextPhaseButton = new JButton("Next Phase");
    nextPhaseButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.NextPhase));
    playerRow.add(nextPhaseButton);
    actionButtons.add(nextPhaseButton); // Store reference to the next phase button

    this.add(playerRow, BorderLayout.NORTH);
  }

  private void updateActionButton(JButton actionButton, Phase phase, Player player) {
    if (this.phase.getCurrentPhase() == PhaseType.BUYING_AND_PLAYING_PHASE) {
      actionButton.setText("Make Move");
      actionButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.MakeMove));
    } else if (phase.getCurrentPhase() == PhaseType.DISCARD_PHASE) {
      actionButton.setText("Discard Currently Selected Cards");
      actionButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.DiscardCards));
    } else if (phase.getCurrentPhase() == PhaseType.DRAW_PHASE) {
      actionButton.setText("Draw Cards");
      actionButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.DrawCards));
    } else {
      actionButton.setText("End of Turn/Action Not Available");
      actionButton.setEnabled(false);
    }
  }


  public void enableEndTurnButtonOnly() {
    for (JButton button : actionButtons) {
//      button.setEnabled(false); // Disable all action buttons
    }
    nextTurnButton.setEnabled(true);// should look into making the next turn button the action button after drawing cards
  }

  public void addDrawPile(Player player) {
    int sizeOfDrawPile = player.getDrawPile().getCards().size();
    this.add(new DrawPile(sizeOfDrawPile), BorderLayout.WEST);
  }

  public void addDiscardPanel(Player player) {
    this.add(new DiscardPanel(player), BorderLayout.EAST);
  }

  private void addDeck(Player player) {
    this.add(new PlayCards(player.getPlayPile(), phase), BorderLayout.CENTER);
    this.add(new PlayCaveCoins(player.getCaveCoinPile(), phase), BorderLayout.SOUTH);
  }

  public void setNotification(ValidationResult notification) {
    this.add(new JLabel(notification.getMessage()), BorderLayout.SOUTH);
  }
}
