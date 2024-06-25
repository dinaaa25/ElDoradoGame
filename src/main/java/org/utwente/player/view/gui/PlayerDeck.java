package org.utwente.player.view.gui;

import javax.swing.*;

import org.utwente.CaveCoin.PlayCaveCoins;
import org.utwente.game.model.EffectPhaseEnum;
import org.utwente.game.model.Phase;
import org.utwente.game.model.PhaseType;
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

    if (phase.getEffectPhase() != null && phase.getEffectPhase().getEffectPhaseEnum() == EffectPhaseEnum.Scientist) {
      System.out.println("Scientist EffectPhase reached");
      addScientistPhase(playerRow);
      this.add(playerRow, BorderLayout.NORTH);
      return;
    }

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

    // only one step to be iterated (made it responsive to PhaseType phases) :
    if (this.phase.getCurrentPhase().getIndex() < PhaseType.values().length - 1) {
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

  public void addEffectPhaseText(JPanel panel) {
    JLabel effectPhase = new JLabel(String.format("EffectPhase: %s", this.phase.getEffectPhase().getEffectPhaseEnum()));
    effectPhase.setFont(PlayerConfig.NAME_FONT);
    panel.add(effectPhase);
  }

  public void addScientistPhase(JPanel playerRow) {
    JPanel scientistPanel = new JPanel();
    scientistPanel.setLayout(new BoxLayout(scientistPanel, BoxLayout.Y_AXIS));
    addEffectPhaseText(scientistPanel);
    addScientistStep1(scientistPanel);
    addScientistStep2(scientistPanel);
    addEffectPhaseDoneButton(scientistPanel);
    playerRow.add(scientistPanel);
  }

  public void addScientistStep1(JPanel panel) {
    EventType eventType = EventType.ScientistStep1;

    JPanel scientistStep1Panel = new JPanel();
    scientistStep1Panel.setLayout(new BoxLayout(scientistStep1Panel, BoxLayout.X_AXIS));
    JLabel scientistStep1Label = new JLabel("1. Draw an additional card");
    JButton scientistStep1Button = new JButton("Draw card");
    scientistStep1Button.setEnabled(phase.getEffectPhase().getCurrentStep() == eventType);
    scientistStep1Button.addActionListener(l -> {
      EventManager.getInstance().notifying(eventType);
    });
    scientistStep1Panel.add(scientistStep1Label);
    scientistStep1Panel.add(scientistStep1Button);
    panel.add(scientistStep1Panel);
  }

  public void addScientistStep2(JPanel panel) {
    EventType eventType = EventType.ScientistStep2;

    JPanel scientistStep2Panel = new JPanel();
    scientistStep2Panel.setLayout(new BoxLayout(scientistStep2Panel, BoxLayout.X_AXIS));
    JLabel scientistStep2Label = new JLabel("2. (Optional) Select 1 card and then remove it from the game");
    JButton scientistStep2Button = new JButton("Remove selected card");
    scientistStep2Button.setEnabled(phase.getEffectPhase().getCurrentStep() == eventType);
    scientistStep2Button.addActionListener(l -> {
      EventManager.getInstance().notifying(eventType);
    });
    scientistStep2Panel.add(scientistStep2Label);
    scientistStep2Panel.add(scientistStep2Button);
    panel.add(scientistStep2Panel);
  }

  public void addEffectPhaseDoneButton(JPanel panel) {
    JButton effectPhaseDoneButton = new JButton("Done");
    effectPhaseDoneButton.setEnabled(phase.getEffectPhase().allMandatoryStepsCompleted());
    effectPhaseDoneButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.EffectPhaseDone));
    panel.add(effectPhaseDoneButton);
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