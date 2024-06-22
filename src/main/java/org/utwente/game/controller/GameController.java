package org.utwente.game.controller;

import lombok.Getter;

import org.utwente.Board.Board;
import org.utwente.Tile.TileClickEvent;
import org.utwente.game.model.Game;
import org.utwente.game.model.MoveAction;
import org.utwente.game.view.GameView;
import org.utwente.market.model.Card;
import org.utwente.player.model.Player;
import org.utwente.util.event.AddPlayersEvent;
import org.utwente.util.event.Event;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.PickBoardEvent;
import org.utwente.util.event.PlayCardEvent;

import java.util.List;
import java.util.NoSuchElementException;

@Getter
public class GameController {
    private final Game game;
    private final GameView gameView;

    public GameController(GameView gameView) {
        this.game = new Game();
        this.gameView = gameView;
        this.gameView.setGame(game);

        // put the game on the starting page:
        this.gameView.setStageStart();
        // register event listeners:
        EventManager eventManager = EventManager.getInstance();
        eventManager.subscribe(this::onGameStart, EventType.StartGame);
        eventManager.subscribe(this::onPlayersAdded, EventType.AddPlayers);
        eventManager.subscribe(this::onPickBoard, EventType.PickBoard);
        eventManager.subscribe(this::onNextTurn, EventType.NextTurn);
        eventManager.subscribe(this::onNextPhase, EventType.NextPhase);
        eventManager.subscribe(this::onTileClick, EventType.ClickTile);
        eventManager.subscribe(this::onPlayerCardClick, EventType.PlayCards);
        eventManager.subscribe(this::onMakeMove, EventType.MakeMove);
    }

    void onPlayerCardClick(Event event) {
        if (event instanceof PlayCardEvent data) {
            Card card = data.getCard();
            card.switchSelected();
            gameView.redraw();
        }
    }

    Card getCurrentlySelectedCard() {
        return this.game.getCurrentPlayer().getPlayPile().getCards().stream()
                .filter(Card::isSelected)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No selected card found"));
    }

    void onMakeMove(Event event) {
        Card selectedCard = getCurrentlySelectedCard();
        MoveAction action = new MoveAction(this.game.getCurrentPlayer(), selectedCard, game.getBoard().getTileOfPlayer(game.getCurrentPlayer()), this.game.getPhase().getSelectedTile());
        action.validateExecute();
        removeSemiUsedCards(event);
        this.gameView.redraw();
    }

    void removeSemiUsedCards(Event event) {
        List<Card> currentPlayerCards = this.game.getCurrentPlayer().getPlayPile().getCards();
        currentPlayerCards.removeIf(card -> card.getConsumedPower() != 0 && !card.isSelected());
    }

    void onTileClick(Event event) {
        if (event instanceof TileClickEvent data) {
            game.getPhase().setSelectedTile(data.getTile());
            this.gameView.redraw();
        }
    }

    void onNextPhase(Event event) {
        game.nextPhase();
        gameView.setCurrentPhase();
        gameView.redraw();
    }

    void onNextTurn(Event event) {
        game.nextPlayer();
        gameView.setCurrentPlayer();
    }

    /**
     * handle click on the start game button.
     * 
     * @param event
     */
    void onGameStart(Event event) {
        this.gameView.setPlayerSetup();
    }

    void onPlayersAdded(Event event) {
        if (event instanceof AddPlayersEvent) {
            AddPlayersEvent eventData = (AddPlayersEvent) event;
            this.game.setPlayers(eventData.getPlayerNames().stream().map(name -> new Player(name)).toList());
            this.gameView.setBoardSetup();
        }
    }

    void onPickBoard(Event event) {
        if (event instanceof PickBoardEvent) {
            PickBoardEvent data = (PickBoardEvent) event;
            Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
            Board board = boardBuilder.selectPath(data.getPath()).buildPath().addCaveCoinTiles().addBlockades()
                    .build();
            this.game.setBoard(board);
            this.game.placePlayersStart();
            this.gameView.setGameStage();
        }
    }

    void gameSetup() {

    }

    @Override
    public boolean equals(Object subscriber) {
        if (!(subscriber instanceof GameController)) {
            return false;
        }

        GameController subscriberController = (GameController) subscriber;

        return false;
    }

    // @Override
    // public void update(EventType event) {
    // if (event == EventType.EndGame) {
    // game.setFinish(true);
    // } else if (event == EventType.StartGame) {
    // gameView.showMessage("Welcome to the game el Dorado");
    // }
    // }

}