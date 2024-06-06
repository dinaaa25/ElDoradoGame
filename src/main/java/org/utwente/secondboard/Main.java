package org.utwente.secondboard;


import org.utwente.secondboard.boardPieces.HexagonGameBoard;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagon Game Board");
        HexagonGameBoard board = new HexagonGameBoard(28, 28, 30,false);
        JScrollPane scrollPane = new JScrollPane(board);
        frame.add(scrollPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    	 

    }
     }
    
