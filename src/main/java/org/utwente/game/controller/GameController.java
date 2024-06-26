package org.utwente.game.controller;

import lombok.Getter;

import org.utwente.Board.Board;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinClickEvent;
import org.utwente.Tile.TileClickEvent;
import org.utwente.game.model.*;
import org.utwente.game.view.GameView;
import org.utwente.market.controller.BuyEvent;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardPowerException;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.PileType;
import org.utwente.player.model.Player;
import org.utwente.secondboard.SecondBoardLoader;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.*;
import org.utwente.game.model.Configuration;

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
        eventManager.subscribe(this::onDiscardCards, EventType.DiscardCards);
        eventManager.subscribe(this::onDrawCards, EventType.DrawCards);
        eventManager.subscribe(this::onScientistStep1, EventType.ScientistStep1);
        eventManager.subscribe(this::onScientistStep2, EventType.ScientistStep2);
        eventManager.subscribe(this::onCartographerEvent, EventType.CartographerEvent);
        eventManager.subscribe(this::onEffectPhaseDone, EventType.EffectPhaseDone);
    }

    void onDrawCards(Event event) {
        Player currentPlayer = this.game.getCurrentPlayer();
        currentPlayer.drawPlayCards();
        this.gameView.redraw();
    }

    void onEffectPhaseDone(Event event) {
        boolean result = game.getPhase().getEffectPhase().allMandatoryStepsCompleted();
        if (!result) {
            this.game.getPhase().setActionMessage(
                    new ValidationResult(false, "Not all mandatory steps of the phase are completed."));
        } else {
            this.game.getPhase().getEffectPhase().discardEffectResource();
            this.game.getPhase().setActionMessage(new ValidationResult(true,
                    game.getPhase().getEffectPhase().getEffectPhaseEnum() + " phase successfully completed."));
            this.game.getPhase().setEffectPhase(null);
        }
        this.gameView.redraw();
    }

    void onScientistStep1(Event event) {
        try {
            game.getPhase().getEffectPhase().completeStep(EventType.ScientistStep1);
            DrawAction drawAction = new DrawAction(game.getCurrentPlayer(),
                    game.getPhase().getEffectPhase().getResource());
            drawAction.validateExecute();
        } catch (IllegalArgumentException e) {
            this.game.getPhase().setActionMessage(new ValidationResult(false, e.toString()));
        } finally {
            this.gameView.redraw();
        }
    }

    void onScientistStep2(Event event) {
        try {
            game.getPhase().getEffectPhase().completeStep(EventType.ScientistStep2);
            RemoveAction action = new RemoveAction(this.game.getCurrentPlayer(), getCurrentlySelectedResource());
            action.validateExecute();
        } catch (IllegalArgumentException e) {
            this.game.getPhase().setActionMessage(new ValidationResult(false, e.toString()));
        } finally {
            this.gameView.redraw();
        }
    }

    void onDiscardCards(Event event) {
        try {
            List<Card> selectedCards = getAllCurrentlySelectedCards().stream()
                    .filter(card -> game.getCurrentPlayer().getPlayPile().getCards().contains(card))
                    .toList();

            for (Card selectedCard : selectedCards) {
                DiscardAction action = new DiscardAction(this.game.getCurrentPlayer(), selectedCard, game.getPhase());
                ValidationResult result = action.validateExecute();
                this.game.getPhase().setActionMessage(result);
                this.gameView.redraw();
            }

            // Deselect all cards after processing
            game.getPhase().getSelectedResources().clear();
            this.gameView.redraw();
        } catch (NoSuchElementException e) {
            System.out.println("No selected card found: " + e.getMessage());
        }
    }

    void onBuyCardFromMarket(Event event) {
        // we could buy cards from market after drawing cards in p3 before this.
        if (this.game.getPhase().getCurrentPhase() != PhaseType.BUYING_AND_PLAYING_PHASE){
            this.game.getPhase().setActionMessage(new ValidationResult(false, "Not in the right phase to buy cards."));
            return;
        }
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

    Resource getCurrentlySelectedResource() {
        return game.getPhase().getSelectedResources().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No selected card found"));
    }

    List<Card> getAllCurrentlySelectedCards() {
        return game.getPhase().getSelectedResources().stream()
                .filter(resource -> resource instanceof Card)
                .map(resource -> (Card) resource)
                .toList();
    }

    boolean triggerSubPhase(Resource resource) {
        if (resource instanceof Card card) {
            switch (card.getCardType()) {
                case CardType.Wissenschaftlerin:
                    this.game.getPhase()
                            .setEffectPhase(new ScientistEffectPhase(resource, this.game.getCurrentPlayer()));
                    return true;
                case CardType.Kartograph:
                    this.game.getPhase()
                            .setEffectPhase(new CartographerEffectPhase(resource, this.game.getCurrentPlayer()));
                    return true;
                default:
                    break;
            }
        }
        if (resource instanceof CaveCoin coin) {

        }

        return false;
    }

    void onCartographerEvent(Event event) {
        game.getPhase().getEffectPhase().completeStep(EventType.CartographerEvent);
        DrawAction action = new DrawAction(this.game.getCurrentPlayer(),
                this.game.getPhase().getEffectPhase().getResource());
        ValidationResult result = action.validateExecute();
        this.gameView.redraw();
    }

    void onMakeMove(Event event) {
        Resource usedResource = getCurrentlySelectedResource();
        // add played resource to phase.
        this.game.getPhase().addPlayedResource(usedResource);
        // trigger sub phase if needed.
        // if this is triggered it's not necessary to move.
        boolean subphaseTriggered = this.triggerSubPhase(usedResource);
        if (!subphaseTriggered) {
            MoveAction action = new MoveAction(this.game.getCurrentPlayer(), usedResource,
                    game.getBoard().getTileOfPlayer(game.getCurrentPlayer()), this.game.getPhase().getSelectedTile());
            ValidationResult result = action.validateExecute();
            this.game.getPhase().setActionMessage(result);
        } else {
            game.getPhase().getSelectedResources().clear();
        }
        removeSemiUsedResources(event);
        removeUsedResources(event);

        this.gameView.redraw();
    }

    void removeSemiUsedResources(Event event) {
        List<Card> currentResources = this.game.getCurrentPlayer().getPlayPile().getResources();
        currentResources.removeIf(resource -> resource.getConsumedPower() != 0 && !isResourceSelected(resource));
    }

    void removeUsedResources(Event event) {
        this.game.getPhase().getSelectedResources().removeIf(resource -> resource.remainingPower() <= 0);
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

    void checklistEndPhase() {
        Player currentPlayer = game.getCurrentPlayer();
        if (game.getPhase().getEffectPhase() != null) {
            //additionally check if the effect phase is completed
            game.getPhase().setActionMessage(new ValidationResult(false, "Effect phase not completed."));
            return;
        }
        if (game.getPhase().getCurrentPhase() == PhaseType.DISCARD_PHASE) {
            currentPlayer.getDiscardPile().addAll(currentPlayer.getFaceUpDiscardPile());
            currentPlayer.clearFaceUpDiscardPile();
        }
        if (game.getPhase().getCurrentPhase() == PhaseType.DRAW_PHASE) {
            //force draw if we try to end the draw phase without drawing all cards
            currentPlayer.drawPlayCards();
            int cardsToDraw= Player.DECK_CARDS- currentPlayer.getPlayPile().getCards().size();
            if (cardsToDraw>0){
                currentPlayer.getPlayPile().addAll(currentPlayer.getDrawPile().draw(cardsToDraw));
            }
        }
        game.getPhase().setActionMessage(new ValidationResult(true, "All requirements met."));
    }


    void onNextPhase(Event event) {

        if (Configuration.getInstance().xray){
            game.getCurrentPlayer().xRayEyes();
        }

        checklistEndPhase();
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
            Board board;
            if (data.isOtherBoard() || true) {
                SecondBoardLoader secondBoardLoader = new SecondBoardLoader();
                board = secondBoardLoader.getConvertedBoard();

            } else {
                Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
                board = boardBuilder.selectPath(data.getPath()).buildPath().addCaveCoinTiles().addBlockades()
                        .build();

            }
            this.game.setBoard(board);
            this.game.placePlayersStart();
            this.gameView.setGameStage();
        }
    }

}
