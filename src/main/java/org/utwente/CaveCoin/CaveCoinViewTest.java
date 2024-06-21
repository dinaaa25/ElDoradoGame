package org.utwente.CaveCoin;

import javax.swing.*;
import java.awt.*;

public class CaveCoinViewTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CaveCoinView Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            CaveCoinType[] caveCoinTypes = CaveCoinType.values();
            int numCaveCoins = caveCoinTypes.length;
            int gridSize = (int) Math.ceil(Math.sqrt(numCaveCoins));

            JPanel gridPanel = new JPanel(new GridLayout(gridSize, gridSize));

            for (CaveCoinType type : caveCoinTypes) {
                CaveCoin caveCoin = new CaveCoin(1, type);
                CaveCoinView caveCoinView = new CaveCoinView(caveCoin);
                gridPanel.add(caveCoinView);
            }

            frame.add(gridPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}