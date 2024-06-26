package org.utwente.game.view.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.awt.Component;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Board;
import org.utwente.game.model.Game;
import org.utwente.game.view.gui.GameGui;
import org.utwente.player.model.Player;
import org.utwente.market.model.Market;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.Section.Section;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.game.model.CartographerEffectPhase;
import org.utwente.game.model.EffectPhase;
import org.utwente.game.model.EffectPhaseEnum;
import org.utwente.game.model.EffectStep;
import org.utwente.game.model.Game;
import org.utwente.game.model.Phase;
import org.utwente.game.model.ScientistEffectPhase;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.player.PlayerColor;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.EventType;

class GameGuiTest {



  @BeforeAll
  public static void setUpHeadlessMode() {
    System.setProperty("java.awt.headless", "true");
  }
  

  /**
   * Method under test: {@link GameGui#calculatePreferredSize(Board)}
   */
  @Test
  void testCalculatePreferredSize() {
    // Arrange
    GameGui gameGui = new GameGui();
    ArrayList<Section> sections = new ArrayList<>();

    // Act
    gameGui.calculatePreferredSize(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));

    // Assert
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
  }

  /**
   * Method under test: {@link GameGui#calculatePreferredSize(Board)}
   */
  @Test
  void testCalculatePreferredSize2() {
    // Arrange
    GameGui gameGui = new GameGui();

    // Act
    gameGui.calculatePreferredSize(null);

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#calculatePreferredSize(Board)}
   */
  @Test
  void testCalculatePreferredSize3() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.addVetoableChangeListener(mock(VetoableChangeListener.class));
    ArrayList<Section> sections = new ArrayList<>();

    // Act
    gameGui.calculatePreferredSize(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));

    // Assert
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
  }

  /**
   * Method under test: {@link GameGui#calculatePreferredSize(Board)}
   */
  @Test
  void testCalculatePreferredSize4() {
    // Arrange
    GameGui gameGui = new GameGui();

    ArrayList<Section> sections = new ArrayList<>();
    sections.add(new Section(new ArrayList<>(), SectionType.A));

    // Act
    gameGui.calculatePreferredSize(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));

    // Assert
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
  }

  /**
   * Method under test: {@link GameGui#calculatePreferredSize(Board)}
   */
  @Test
  void testCalculatePreferredSize5() {
    // Arrange
    GameGui gameGui = new GameGui();

    ArrayList<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
    Section section = new Section(tiles, SectionType.A);

    ArrayList<Section> sections = new ArrayList<>();
    sections.add(section);

    // Act
    gameGui.calculatePreferredSize(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));

    // Assert
    assertEquals(-25, gameGui.getOffsetX());
    assertEquals(-79, gameGui.getOffsetY());
  }

  /**
   * Method under test: {@link GameGui#calculatePreferredSize(Board)}
   */
  @Test
  void testCalculatePreferredSize6() {
    // Arrange
    GameGui gameGui = new GameGui();

    ArrayList<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(Integer.MAX_VALUE, Integer.MAX_VALUE, TileType.Machete, Integer.MAX_VALUE, true));
    tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
    Section section = new Section(tiles, SectionType.A);

    ArrayList<Section> sections = new ArrayList<>();
    sections.add(section);

    // Act
    gameGui.calculatePreferredSize(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));

    // Assert
    assertEquals(-25, gameGui.getOffsetX());
    assertEquals(-79, gameGui.getOffsetY());
  }

  /**
   * Method under test: {@link GameGui#calculatePreferredSize(Board)}
   */
  @Test
  void testCalculatePreferredSize7() {
    // Arrange
    GameGui gameGui = new GameGui();

    ArrayList<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
    Section section = new Section(tiles, SectionType.A);

    ArrayList<Section> sections = new ArrayList<>();
    sections.add(section);

    // Act
    gameGui.calculatePreferredSize(new Board(sections, Path.HillsOfGold, false, new ArrayList<>()));

    // Assert
    assertEquals(-25, gameGui.getOffsetY());
    assertEquals(-79, gameGui.getOffsetX());
  }

  /**
   * Method under test: {@link GameGui#setupGame()}
   */
  @Test
  void testSetupGame() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new CartographerEffectPhase(resource, new Player("Name")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setupGame();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setupGame()}
   */
  @Test
  void testSetupGame2() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(false);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new CartographerEffectPhase(resource, new Player("Name")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setupGame();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setupGame()}
   */
  @Test
  void testSetupGame3() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(null);
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new CartographerEffectPhase(resource, new Player("Name")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setupGame();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setupGame()}
   */
  @Test
  void testSetupGame4() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new ScientistEffectPhase(resource, new Player("Center")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setupGame();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(445.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setupGame()}
   */
  @Test
  void testSetupGame5() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(new HashMap<>());
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);
    when(effectPhase.getResource()).thenReturn(new Card(CardType.Kundeschafter));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    when(phase.getEffectPhase()).thenReturn(effectPhase);
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setupGame();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(effectPhase).allMandatoryStepsCompleted();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getResource();
    verify(effectPhase).getSteps();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(395.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setupGame()}
   */
  @Test
  void testSetupGame6() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));

    HashMap<EventType, EffectStep> eventTypeEffectStepMap = new HashMap<>();
    eventTypeEffectStepMap.put(EventType.StartGame, new EffectStep(true, true, 1, "Center", "Center"));
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.getCurrentStep()).thenReturn(EventType.StartGame);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(eventTypeEffectStepMap);
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);
    when(effectPhase.getResource()).thenReturn(new Card(CardType.Kundeschafter));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    when(phase.getEffectPhase()).thenReturn(effectPhase);
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setupGame();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(effectPhase).allMandatoryStepsCompleted();
    verify(effectPhase).getCurrentStep();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getResource();
    verify(effectPhase).getSteps();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#addBoard()}
   */
  @Test
  void testAddBoard() {
    // Arrange
    GameGui gameGui = new GameGui();

    // Act
    gameGui.addBoard();

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#addBoard()}
   */
  @Test
  void testAddBoard2() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.setGame(new Game());

    // Act
    gameGui.addBoard();

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#addBoard()}
   */
  @Test
  void testAddBoard3() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.addVetoableChangeListener(mock(VetoableChangeListener.class));

    // Act
    gameGui.addBoard();

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#addMarket()}
   */
  @Test
  void testAddMarket() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.setGame(new Game());

    // Act
    gameGui.addMarket();

    // Assert
    Component expectedMarketViewScrollPane = gameGui.getComponents()[0];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
  }

  /**
   * Method under test: {@link GameGui#addPlayerSection()}
   */
  @Test
  void testAddPlayerSection() {
    // Arrange
    Phase phase = new Phase();
    phase.setActionMessage(null);

    Player player = new Player("Name");
    player.setColor(PlayerColor.Red);

    ArrayList<Player> players = new ArrayList<>();
    players.add(player);

    Game game = new Game();
    game.setPhase(phase);
    game.setPlayers(players);

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.addPlayerSection();

    // Assert
    assertEquals(410.0d, gameGui.getPreferredSize().getHeight());
    Component expectedPlayerDeck = gameGui.getComponents()[0];
    assertSame(expectedPlayerDeck, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#addPlayerSection()}
   */
  @Test
  void testAddPlayerSection2() {
    // Arrange
    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new CartographerEffectPhase(resource, new Player("Make Move")));
    phase.setActionMessage(null);

    ArrayList<Player> players = new ArrayList<>();
    players.add(new Player("Name"));

    Game game = new Game();
    game.setPhase(phase);
    game.setPlayers(players);

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.addPlayerSection();

    // Assert
    assertEquals(430.0d, gameGui.getPreferredSize().getHeight());
    Component expectedPlayerDeck = gameGui.getComponents()[0];
    assertSame(expectedPlayerDeck, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#addPlayerSection()}
   */
  @Test
  void testAddPlayerSection3() {
    // Arrange
    Phase phase = new Phase();
    phase.setActionMessage(new ValidationResult(true, "Not all who wander are lost"));

    Player player = new Player("Name");
    player.setColor(PlayerColor.Red);

    ArrayList<Player> players = new ArrayList<>();
    players.add(player);

    Game game = new Game();
    game.setPhase(phase);
    game.setPlayers(players);

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.addPlayerSection();

    // Assert
    assertEquals(410.0d, gameGui.getPreferredSize().getHeight());
    Component expectedPlayerDeck = gameGui.getComponents()[0];
    assertSame(expectedPlayerDeck, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#addPlayerSection()}
   */
  @Test
  void testAddPlayerSection4() {
    // Arrange
    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new ScientistEffectPhase(resource, new Player("Done")));
    phase.setActionMessage(null);

    ArrayList<Player> players = new ArrayList<>();
    players.add(new Player("Name"));

    Game game = new Game();
    game.setPhase(phase);
    game.setPlayers(players);

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.addPlayerSection();

    // Assert
    assertEquals(455.0d, gameGui.getPreferredSize().getHeight());
    Component expectedPlayerDeck = gameGui.getComponents()[0];
    assertSame(expectedPlayerDeck, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setStageStart()}
   */
  @Test
  void testSetStageStart() {
    // Arrange
    GameGui gameGui = new GameGui();

    // Act
    gameGui.setStageStart();

    // Assert
    assertEquals("Welcome To El Dorado Game.", ((GameStart) gameGui.getComponents()[0]).welcomeString);
  }

  /**
   * Method under test: {@link GameGui#setStageStart()}
   */
  @Test
  void testSetStageStart2() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.addVetoableChangeListener(mock(VetoableChangeListener.class));

    // Act
    gameGui.setStageStart();

    // Assert
    assertEquals("Welcome To El Dorado Game.", ((GameStart) gameGui.getComponents()[0]).welcomeString);
  }

  /**
   * Method under test: {@link GameGui#setPlayerSetup()}
   */
  @Test
  void testSetPlayerSetup() {
    // Arrange
    GameGui gameGui = new GameGui();

    // Act
    gameGui.setPlayerSetup();

    // Assert
    assertEquals(4, ((PlayerForm) gameGui.getComponents()[0]).textFields.size());
    assertEquals(4, ((PlayerForm) gameGui.getComponents()[0]).playerAmount);
  }

  /**
   * Method under test: {@link GameGui#setPlayerSetup()}
   */
  @Test
  void testSetPlayerSetup2() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.addVetoableChangeListener(mock(VetoableChangeListener.class));

    // Act
    gameGui.setPlayerSetup();

    // Assert
    assertEquals(4, ((PlayerForm) gameGui.getComponents()[0]).textFields.size());
    assertEquals(4, ((PlayerForm) gameGui.getComponents()[0]).playerAmount);
  }

  /**
   * Method under test: {@link GameGui#setupBoardForm()}
   */
  @Test
  void testSetupBoardForm() {
    // Arrange
    GameGui gameGui = new GameGui();

    // Act
    gameGui.setupBoardForm();

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#setupBoardForm()}
   */
  @Test
  void testSetupBoardForm2() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.addVetoableChangeListener(mock(VetoableChangeListener.class));

    // Act
    gameGui.setupBoardForm();

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#setBoardSetup()}
   */
  @Test
  void testSetBoardSetup() {
    // Arrange
    GameGui gameGui = new GameGui();

    // Act
    gameGui.setBoardSetup();

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#setBoardSetup()}
   */
  @Test
  void testSetBoardSetup2() {
    // Arrange
    GameGui gameGui = new GameGui();
    gameGui.addVetoableChangeListener(mock(VetoableChangeListener.class));

    // Act
    gameGui.setBoardSetup();

    // Assert that nothing has changed
    assertEquals(0, gameGui.getOffsetX());
    assertEquals(0, gameGui.getOffsetY());
    assertFalse(gameGui.getEndTurnButtonEnabled());
  }

  /**
   * Method under test: {@link GameGui#setGameStage()}
   */
  @Test
  void testSetGameStage() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new CartographerEffectPhase(resource, new Player("Name")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setGameStage();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setGameStage()}
   */
  @Test
  void testSetGameStage2() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(false);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new CartographerEffectPhase(resource, new Player("Name")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setGameStage();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setGameStage()}
   */
  @Test
  void testSetGameStage3() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(null);
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new CartographerEffectPhase(resource, new Player("Name")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setGameStage();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setGameStage()}
   */
  @Test
  void testSetGameStage4() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new ScientistEffectPhase(resource, new Player("Center")));
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setGameStage();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(445.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setGameStage()}
   */
  @Test
  void testSetGameStage5() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(new HashMap<>());
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);
    when(effectPhase.getResource()).thenReturn(new Card(CardType.Kundeschafter));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    when(phase.getEffectPhase()).thenReturn(effectPhase);
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setGameStage();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(effectPhase).allMandatoryStepsCompleted();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getResource();
    verify(effectPhase).getSteps();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(395.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }

  /**
   * Method under test: {@link GameGui#setGameStage()}
   */
  @Test
  void testSetGameStage6() {
    // Arrange
    Tile tile = mock(Tile.class);
    when(tile.getQ()).thenReturn(1);
    when(tile.getR()).thenReturn(1);

    ArrayList<Tile> tileList = new ArrayList<>();
    tileList.add(tile);
    Board board = mock(Board.class);
    when(board.isFlatTop()).thenReturn(true);
    when(board.getBlockades()).thenReturn(new ArrayList<>());
    when(board.getSections()).thenReturn(new ArrayList<>());
    when(board.getStartingTiles()).thenReturn(tileList);
    when(board.getTileOfPlayer(Mockito.<Player>any())).thenReturn(new Tile(1, 1, TileType.Machete, 1, true));

    HashMap<EventType, EffectStep> eventTypeEffectStepMap = new HashMap<>();
    eventTypeEffectStepMap.put(EventType.StartGame, new EffectStep(true, true, 1, "Center", "Center"));
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.getCurrentStep()).thenReturn(EventType.StartGame);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(eventTypeEffectStepMap);
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);
    when(effectPhase.getResource()).thenReturn(new Card(CardType.Kundeschafter));
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getSelectedResources()).thenReturn(new Stack<>());
    when(phase.getEffectPhase()).thenReturn(effectPhase);
    Game game = mock(Game.class);
    when(game.getBoard()).thenReturn(board);
    when(game.getPhase()).thenReturn(phase);
    when(game.getMarket()).thenReturn(new Market());
    when(game.getCurrentPlayer()).thenReturn(new Player("Name"));

    GameGui gameGui = new GameGui();
    gameGui.setGame(game);

    // Act
    gameGui.setGameStage();

    // Assert
    verify(board, atLeast(1)).getBlockades();
    verify(board, atLeast(1)).getSections();
    verify(board).getStartingTiles();
    verify(board).getTileOfPlayer(isA(Player.class));
    verify(board, atLeast(1)).isFlatTop();
    verify(tile).getQ();
    verify(tile).getR();
    verify(effectPhase).allMandatoryStepsCompleted();
    verify(effectPhase).getCurrentStep();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getResource();
    verify(effectPhase).getSteps();
    verify(game, atLeast(1)).getBoard();
    verify(game, atLeast(1)).getCurrentPlayer();
    verify(game, atLeast(1)).getMarket();
    verify(game, atLeast(1)).getPhase();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(phase, atLeast(1)).getSelectedResources();
    Component[] components = gameGui.getComponents();
    Component component = components[2];
    assertEquals(420.0d, component.getPreferredSize().getHeight());
    assertEquals(50, gameGui.getOffsetX());
    assertEquals(50, gameGui.getOffsetY());
    Component expectedBoardViewScrollPane = components[0];
    assertSame(expectedBoardViewScrollPane, gameGui.getBoardViewScrollPane());
    Component expectedMarketViewScrollPane = components[1];
    assertSame(expectedMarketViewScrollPane, gameGui.getMarketViewScrollPane());
    assertSame(component, gameGui.getPlayerDeck());
  }


  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link GameGui}
   *   <li>{@link GameGui#setGame(Game)}
   *   <li>{@link GameGui#startScreen()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    GameGui actualGameGui = new GameGui();
    Game game = new Game();
    actualGameGui.setGame(game);
    actualGameGui.startScreen();

    // Assert that nothing has changed
    assertSame(game, actualGameGui.getGame());
  }
}
