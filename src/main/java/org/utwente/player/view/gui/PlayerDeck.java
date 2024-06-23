package org.utwente.player.view.gui;


import javax.swing.*;

import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinLoader;
import org.utwente.CaveCoin.PlayCaveCoins;
import org.utwente.game.model.PhaseType;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import java.awt.*;
import java.util.Collections;
import java.util.List;

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

    JButton makeMoveButton = new JButton("Make Move");
    makeMoveButton.addActionListener(l -> EventManager.getInstance().notifying(EventType.MakeMove));
    playerRow.add(makeMoveButton);

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

    // TODO this is just to show the Coins in the PlayerDeck, we can remove this to have to obtain the CaveCoins
    //  through the game dynamically
//    List<CaveCoin> caveCoinList = CaveCoinLoader.loadCoins();
//    Collections.shuffle(caveCoinList);
//    caveCoinList = caveCoinList.subList(0, 7);
//    for (CaveCoin cc : caveCoinList) {
//      player.getCaveCoinPile().add(cc);
//    }
    this.add(new PlayCaveCoins(player.getCaveCoinPile()), BorderLayout.SOUTH);
  }

}