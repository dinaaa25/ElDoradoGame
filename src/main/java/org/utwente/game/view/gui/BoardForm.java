package org.utwente.game.view.gui;

import java.awt.*;
import javax.swing.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.utwente.Board.Path;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.PickBoardEvent;

public class BoardForm extends JPanel {

  public BoardForm() {
    var label = new JLabel("Select a Board for the Game.");
    this.add(label);
    final JComboBox<String> pathComboBox = new JComboBox<String>(
        Arrays.stream(Path.values()).map(e -> e.toString()).toArray(String[]::new));
    this.add(pathComboBox);
    var button = new JButton("Finish");
    String result = (String) pathComboBox.getSelectedItem();
    Path path = Path.valueOf(result);
    button.addActionListener(l -> EventManager.getInstance().notifying(EventType.PickBoard, new PickBoardEvent(path)));
    this.add(button);
  }
}
