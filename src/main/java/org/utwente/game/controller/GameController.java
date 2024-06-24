package org.utwente.game.controller;

import lombok.Getter;

import org.utwente.Board.Board;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinClickEvent;
import org.utwente.Tile.TileClickEvent;
import org.utwente.game.model.BuyAction;
import org.utwente.game.model.Game;
import org.utwente.game.model.MoveAction;
import org.utwente.game.model.Phase;
import org.utwente.game.view.GameView;
import org.utwente.market.controller.BuyEvent;
import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.AddPlayersEvent;
import org.utwente.util.event.Event;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.PickBoardEvent;
import org.utwente.util.event.PlayCardEvent;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

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
        eventManager.subscribe(this::onBuyCardFromMarket, EventType.BuyCards);
        eventManager.subscribe(this::onPlayerCaveCoinClick, EventType.ClickCaveCoin);
    }

    void onBuyCardFromMarket(Event event) {
        if (event instanceof BuyEvent data) {
            List<Resource> resources = game.getPhase().getSelectedResources();
            BuyAction action = new BuyAction(this.game.getCurrentPlayer(), resources, data.getCardType(),
                    this.game.getMarket());
            ValidationResult result = action.validateExecute();
            this.game.getPhase().setActionMessage(result);
            this.gameView.redraw();
        }
    }

    void onPlayerCardClick(Event event) {
        if (event instanceof PlayCardEvent data) {
            Card card = data.getCard();
            toggleSelectedResource(card);
        }
    }

    void onPlayerCaveCoinClick(Event event) {
        if (event instanceof CaveCoinClickEvent data) {
            CaveCoin caveCoin = data.getCaveCoin();
            toggleSelectedResource(caveCoin);
        }
    }

    void toggleSelectedResource(Resource resource) {
        Stack<Resource> resources = game.getPhase().getSelectedResources();
        if (resources.contains(resource)) {
            resources.remove(resource);
        } else {
            resources.add(resource);
        }
        gameView.redraw();
    }

    Card getCurrentlySelectedCard() {
        return game.getPhase().getSelectedResources().stream()
                .filter(resource -> resource instanceof Card)
                .map(resource -> (Card) resource)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No selected card found"));
    }

    void onMakeMove(Event event) {
        Card selectedCard = getCurrentlySelectedCard();
        MoveAction action = new MoveAction(this.game.getCurrentPlayer(), selectedCard,
                game.getBoard().getTileOfPlayer(game.getCurrentPlayer()), this.game.getPhase().getSelectedTile(),
                game.getPhase());
        ValidationResult result = action.validateExecute();
        this.game.getPhase().setActionMessage(result);
        removeSemiUsedResources(event);
        removeUsedResources(event);
        this.gameView.redraw();
    }

    void removeSemiUsedResources(Event event) {
        List<Card> currentResources = this.game.getCurrentPlayer().getPlayPile().getResources();
        currentResources.removeIf(resource -> resource.getConsumedPower() != 0 && !isResourceSelected(resource));
    }

    void removeUsedResources(Event event) {
        List<Card> currentResources = this.game.getCurrentPlayer().getPlayPile().getResources();
        currentResources.removeIf(resource -> resource.getPower() <= 0);
        this.game.getPhase().getSelectedResources().removeIf(resource -> resource.getPower() <= 0);
    }

    boolean isResourceSelected(Resource resource) {
        Phase phase = game.getPhase();
        return phase.getSelectedResources().contains(resource);
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
        Player player = game.getCurrentPlayer();
        player.drawPlayCards();
        game.nextPlayer();
        gameView.redraw();
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