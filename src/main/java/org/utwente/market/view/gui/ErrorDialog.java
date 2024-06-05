package org.utwente.market.view.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ErrorDialog extends JDialog {
  private String errorMessage;
  Container contentPane;
  final int WIDTH = 300;
  final int HEIGHT = 300;

  public ErrorDialog(JFrame parent, String errorMessage) {
    super(parent, "Error", false);

    this.errorMessage = errorMessage;
    contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());

    setSize(WIDTH, HEIGHT);
  }

  public void display() {
    contentPane.setBackground(MarketConfig.MARKET_ERROR_BG);
    JLabel label = new JLabel(errorMessage, SwingConstants.CENTER);
    contentPane.add(label);
    this.setVisible(true);
  }

}
