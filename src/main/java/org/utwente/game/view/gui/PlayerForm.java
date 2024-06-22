package org.utwente.game.view.gui;

import javax.swing.*;

import org.utwente.util.event.AddPlayersEvent;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import java.awt.GridLayout;
import java.util.*;

public class PlayerForm extends JPanel {
  int playerAmount = 4;
  List<JTextField> textFields;

  public PlayerForm() {
    super();
    this.setLayout(new GridLayout(playerAmount + 1, 1));
    this.textFields = new ArrayList<JTextField>();
    for (int i = 0; i < playerAmount; i++) {
      this.add(new JLabel(String.format("Player %d", i + 1)));
      JTextField field = new JTextField();
      this.textFields.add(field);
      this.add(field);
    }
    JButton jButton = new JButton("Next");
    jButton.addActionListener(
        l -> EventManager.getInstance().notifying(EventType.AddPlayers,
            new AddPlayersEvent(textFields.stream().map(e -> e.getText()).toList())));
    this.add(jButton);
  }
}
