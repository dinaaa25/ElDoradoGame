package org.utwente.game.model;

import lombok.Getter;
import lombok.Setter;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Board;
import org.utwente.Tile.Tile;
import org.utwente.market.model.Market;
import org.utwente.player.PlayerColor;
import org.utwente.player.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Game {
    private String gameName;
    private String gameDescription;
    private Board board;
    private List<Player> players = new ArrayList<>();
    private Player finalWinner;
    private final Market market;
    private int currentPlayer;
    private boolean enteredWaitingState = false;
    private int waitingPlayerIndex = 0;
    private boolean isFinished = false;
    private int waitCounter = 0;
    private Phase phase;

    public Game(String gameName, String gameDescription, Board board, List<Player> players) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.board = board;
        this.players = players;
        this.market = new Market();
        this.phase = new Phase();
    }

    public Game() {
        this.market = new Market();
        this.phase = new Phase();
    }

    public Player getCurrentPlayer() {
        return this.players.get(currentPlayer);
    }

    private int waitingTurns() {
        return this.players.size() - (this.waitingPlayerIndex + 1);
    }

    private void checkWaitingForWinTurn() {
        boolean isWaiting = isInWaitingState();
        if (!isWaiting) {
            return;
        }
        // someone is in the ending tiles.

        // entering waiting for the first time:
        if (!enteredWaitingState) {
            enteredWaitingState = true;
            waitingPlayerIndex = currentPlayer;
            return;
        }

        int waitingTurns = this.waitingTurns();
        // still turns remaining before the finish:
        if (waitCounter + 1 < waitingTurns) {
            waitCounter++;
        } else {
            // no turns remaining.
            isFinished = true;
            setWinner();
        }
    }

    public int nextPlayer() {
        checkWaitingForWinTurn();
        this.phase = new Phase();
        if (currentPlayer < this.players.size() - 1) {
            currentPlayer = currentPlayer + 1;
            return currentPlayer;
        }
        currentPlayer = 0;
        return currentPlayer;
    }

    public List<Player> getMaxBlockadePlayers() {
        List<Player> maxBlockadePlayers = new ArrayList<>();
        int maxBlockadeCount = 1;
        for (Player player : players) {
            int playerCount = player.getBlockadeCount();
            if (playerCount > maxBlockadeCount) {
                maxBlockadePlayers.clear();
                maxBlockadeCount = playerCount;
                maxBlockadePlayers.add(player);
            } else if (playerCount == maxBlockadeCount) {
                maxBlockadePlayers.add(player);
            }
        }
        return maxBlockadePlayers;
    }

    public Player compareBlockadePower(List<Player> players) {
        assert players.size() != 0;
        int maxBlockadePower = 0;
        Player maxBlockade = null;
        for (Player player : players) {
            Blockade playerPower = player.getMaxBlockade();
            if (playerPower.getPower() > maxBlockadePower) {
                maxBlockadePower = playerPower.getPower();
                maxBlockade = player;
            }
        }
        assert maxBlockade != null;
        return maxBlockade;
    }

    public void setWinner() {
        List<Tile> tiles = board.getLastWaitingTiles();
        List<Player> playersLastTile = tiles.stream().flatMap(tile -> tile.getPlayers().stream()).toList();
        if (playersLastTile.size() > 1) {
            // win with two players on the waiting.
            List<Player> maxBlockadePlayers = getMaxBlockadePlayers();
            if (maxBlockadePlayers.size() == 1) {
                this.finalWinner = maxBlockadePlayers.get(0);
            } else if (maxBlockadePlayers.size() > 1) {
                this.finalWinner = compareBlockadePower(maxBlockadePlayers);
            } else {
                this.finalWinner = this.players.get(waitingPlayerIndex);
            }
        } else if (playersLastTile.size() == 1) {
            // win with one player only.
            this.finalWinner = playersLastTile.get(0);
        }
    }

    public boolean isInWaitingState() {
        for (Tile t : board.getLastWaitingTiles()) {
            if (!t.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void setFinish(boolean finished) {
        this.isFinished = true;
    }

    public List<Tile> placePlayersStart() {
        List<Tile> startingTiles = board.getStartingTiles();
        for (int index = 0; index < this.players.size(); index++) {
            players.get(index).setColor(PlayerColor.values()[index]);
            board.placePlayer(startingTiles.get(index), players.get(index));
        }
        return startingTiles;
    }

    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        while (!this.isFinished()) {
            for (Player player : players) {
                System.out.println("Its your turn: " + player.getName() + ", Enter your move: ");
                String result = scanner.nextLine();
                if (result.contains("quit")) {
                    this.setFinish(true);
                    break;
                }
            }
        }
    }

    public void nextPhase() {
        this.phase.next();
    }
}
