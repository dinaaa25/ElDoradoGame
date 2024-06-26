package org.utwente.player.view.gui;

import javax.swing.*;

import org.utwente.CaveCoin.PlayCaveCoins;
import org.utwente.game.model.CaveCoinSymbolEffectPhase;
import org.utwente.game.model.EffectStep;
import org.utwente.game.model.Phase;
import org.utwente.game.model.PhaseType;
import org.utwente.market.model.PowerType;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.SelectSymbolTypeEvent;

import java.util.Map.Entry;

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

    if (phase.getEffectPhase() != null) {
      System.out.println("Any EffectPhase reached " + phase.getEffectPhase().getEffectPhaseEnum());
      this.addEffectCardPhase(playerRow);
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

    JLabel phaseLabel = new JLabel(String.format("Current Phase: %s", this.phase.getCurrentPhase().toString()));
    name.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    name.setFont(PlayerConfig.NAME_FONT);
    playerRow.add(phaseLabel);

    if (this.phase.getCurrentPhase() == PhaseType.DRAW_PHASE) {
      JButton nextTurnButton = new JButton("Next Turn");
      nextTurnButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.NextTurn));
      playerRow.add(nextTurnButton);
    }

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

  public void addSelectSymbolType(JPanel panel) {
    JPanel rowPanel = new JPanel();
    rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
    JButton macheteButton = new JButton(String.valueOf(PowerType.Machete));
    macheteButton.addActionListener(l -> {EventManager.getInstance().notifying(EventType.CaveCoinSymbolSelectType, new SelectSymbolTypeEvent(PowerType.Machete));});
    macheteButton.setSelected(((CaveCoinSymbolEffectPhase) this.phase.getEffectPhase()).getPowerTypeToChangeTo() == PowerType.Machete);
    JButton paddleButton = new JButton(String.valueOf(PowerType.Paddle));
    paddleButton.addActionListener(l -> {EventManager.getInstance().notifying(EventType.CaveCoinSymbolSelectType, new SelectSymbolTypeEvent(PowerType.Paddle));});
    paddleButton.setSelected(((CaveCoinSymbolEffectPhase) this.phase.getEffectPhase()).getPowerTypeToChangeTo() == PowerType.Paddle);
    JButton coinButton = new JButton(String.valueOf(PowerType.Coin));
    coinButton.addActionListener(l -> {EventManager.getInstance().notifying(EventType.CaveCoinSymbolSelectType, new SelectSymbolTypeEvent(PowerType.Coin));});
    coinButton.setSelected(((CaveCoinSymbolEffectPhase) this.phase.getEffectPhase()).getPowerTypeToChangeTo() == PowerType.Coin);
    rowPanel.add(macheteButton);
    rowPanel.add(paddleButton);
    rowPanel.add(coinButton);
    panel.add(rowPanel);
  }

  public void addEffectCardPhase(JPanel playerRow) {
    JPanel specialCardPanel = new JPanel();
    specialCardPanel.setLayout(new BoxLayout(specialCardPanel, BoxLayout.Y_AXIS));

    for (var entry : this.phase.getEffectPhase().getSteps().entrySet()) {
      if (entry.getKey() != null && entry.getKey() == EventType.CaveCoinSymbolSelectType) {
        addSelectSymbolType(specialCardPanel);
      } else {
        addEffectRow(specialCardPanel, entry);
      }
    }

    addEffectPhaseDoneButton(specialCardPanel);
    playerRow.add(specialCardPanel);
  }

  public void addEffectRow(JPanel cardPanel, Entry<EventType, EffectStep> step) {

    JPanel rowPanel = new JPanel();
    rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
    JLabel label = new JLabel(step.getValue().getStepLabel());
    JButton button = new JButton(step.getValue().getButtonText());
    button.setEnabled(phase.getEffectPhase().getCurrentStep() == step.getKey());
    button.addActionListener(l -> {
      EventManager.getInstance().notifying(step.getKey());
    });
    rowPanel.add(label);
    rowPanel.add(button);
    cardPanel.add(rowPanel);
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
//    this.add(new JLabel(notification.getMessage()), BorderLayout.BEFORE_LINE_BEGINS);
  }

}