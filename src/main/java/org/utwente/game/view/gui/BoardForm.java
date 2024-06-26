package org.utwente.game.view.gui;

import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import org.utwente.Board.Path;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.PickBoardEvent;

public class BoardForm extends JPanel {

  public BoardForm() {
    this.setLayout(new GridLayout(3, 1));
    var label = new JLabel("Select a Board for the Game.");
    this.add(label);

    final JComboBox<String> pathComboBox = new JComboBox<>(
            Arrays.stream(Path.values()).map(Enum::toString).toArray(String[]::new)
    );
    this.add(pathComboBox);

    var button = new JButton("Finish");
    button.addActionListener(l -> {
      String selectedPath = (String) pathComboBox.getSelectedItem();
      if (selectedPath != null) {
        Path path = Path.valueOf(selectedPath);
        EventManager.getInstance().notifying(EventType.PickBoard, new PickBoardEvent(path));
      }
    });
    this.add(button);

    JButton otherTeamButton = new JButton("Use board of Team04");
    otherTeamButton.addActionListener(l -> {
      String selectedPath = (String) pathComboBox.getSelectedItem();
      if (selectedPath != null) {
        Path path = Path.valueOf(selectedPath);
        EventManager.getInstance().notifying(EventType.PickBoard, new PickBoardEvent(path, true));
      }
    });
    this.add(otherTeamButton);
  }
}