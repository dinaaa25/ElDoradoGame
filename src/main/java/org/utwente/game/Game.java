package org.utwente.game;


import org.utwente.Board.Board;
import org.utwente.Tile.Tile;
import org.utwente.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private String gameName;
    private String gameDescription;
    private Board board;
    private List<Player> players = new ArrayList<>();
    private Player finalWinner;
    private int currentPlayer;
    private boolean isFinished = false;

    public Game(String gameName, String gameDescription, Board board, List<Player> players) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.board = board;
        this.players = players;
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getGameDescription() {
        return this.gameDescription;
    }

    public Board getBoard() {
        return this.board;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer(){
        return this.players.get(currentPlayer);
    }

    public int nextPlayer() {
        return 0;
    }

    public Player getFinalWinner() {
        return this.finalWinner;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void setFinish(boolean finished) {
        this.isFinished = true;
    }

    public void executeAction(Action action) {

    }

    public List<Tile> placePlayersStart() {
        return null;
    }

    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        while(!this.isFinished()) {
            for (Player player : players) {
                System.out.println("Its your turn: " + player.getName() + ", Enter your move: ");
                String result = scanner.nextLine();
                if(result.contains("quit")) {
                    this.setFinish(true);
                    break;
                }
            }
        }
    }
}

