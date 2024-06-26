package org.utwente.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ch.qos.logback.core.util.COWArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade.*;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.CoinPile;
import org.utwente.player.model.PileType;
import org.utwente.player.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlayerTest {

  Player player;
  Blockade mockBlockade1;
  Blockade mockBlockade2;

  @BeforeEach
  void setUp() {
    mockBlockade1 = mock(Blockade.class);
    mockBlockade2 = mock(Blockade.class);
    player = new Player("TestPlayer");
  }

  @Test
  void testConstructor() {
    assertNotNull(player);
    assertEquals("TestPlayer", player.getName());
    assertEquals(0, player.getBlockadeCount());
  }

  @Test
  void testGetName() {
    assertEquals("TestPlayer", player.getName());
  }

  @Test
  void testAddBlockade() {
    player.addBlockade(mockBlockade1);
    assertEquals(1, player.getBlockadeCount());
    player.addBlockade(mockBlockade2);
    assertEquals(2, player.getBlockadeCount());
  }

  @Test
  void testGetBlockadeCount() {
    assertEquals(0, player.getBlockadeCount());
    player.addBlockade(mockBlockade1);
    assertEquals(1, player.getBlockadeCount());
  }

  @Test
  void testEquals() {
    Player anotherPlayer = new Player("TestPlayer");
    Player differentPlayer = new Player("DifferentPlayer");

      assertEquals(player, anotherPlayer);
      assertNotEquals(player, differentPlayer);
      assertNotEquals(null, player);
      assertNotEquals("SomeString", player);
  }

  @Test
  void testGetMaxBlockade() {
    when(mockBlockade1.getPower()).thenReturn(5);
    when(mockBlockade2.getPower()).thenReturn(10);

    player.addBlockade(mockBlockade1);
    player.addBlockade(mockBlockade2);

    Blockade maxBlockade = player.getMaxBlockade();
    assertEquals(mockBlockade2, maxBlockade);

    when(mockBlockade1.getPower()).thenReturn(15);
    maxBlockade = player.getMaxBlockade();
    assertEquals(mockBlockade1, maxBlockade);
  }
  /**
   * Method under test: {@link Player#clearFaceUpDiscardPile()}
   */
  @Test
  void testClearFaceUpDiscardPile() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.clearFaceUpDiscardPile();

    // Assert
    CardPile faceUpDiscardPile = player.getFaceUpDiscardPile();
    assertEquals(PileType.FaceUpDiscard, faceUpDiscardPile.getPileType());
    assertTrue(faceUpDiscardPile.isEmpty());
    assertSame(player, faceUpDiscardPile.getPlayer());
  }

  /**
   * Method under test: {@link Player#discardResource(Resource)}
   */
  @Test
  void testDiscardResource() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.discardResource(new Card(CardType.Kundeschafter));

    // Assert
    assertFalse(player.getFaceUpDiscardPile().isEmpty());
    assertFalse(player.getPlayPile().isEmpty());
  }

  /**
   * Method under test: {@link Player#discardResource(Resource)}
   */
  @Test
  void testDiscardResource2() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.discardResource(new CaveCoin(1, CaveCoinType.Machete));

    // Assert
    assertFalse(player.getCaveCoinPile().isEmpty());
  }

  /**
   * Method under test: {@link Player#discardResource(Resource)}
   */
  @Test
  void testDiscardResource3() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.discardResource(null);

    // Assert that nothing has changed
    assertEquals("Name", player.getName());
    assertEquals(0, player.getBlockadeCount());
    assertEquals(PileType.Discard, player.getDiscardPile().getPileType());
    assertEquals(PileType.FaceUpDiscard, player.getFaceUpDiscardPile().getPileType());
    assertEquals(PileType.OutOfGame, player.getOutOfGamePile().getPileType());
    assertEquals(PileType.Play, player.getPlayPile().getPileType());
  }

  /**
   * Method under test: {@link Player#drawPlayCards()}
   */
  @Test
  void testDrawPlayCards() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.drawPlayCards();

    // Assert that nothing has changed
    assertEquals("Name", player.getName());
    assertEquals(0, player.getBlockadeCount());
    assertEquals(PileType.Discard, player.getDiscardPile().getPileType());
    assertEquals(PileType.FaceUpDiscard, player.getFaceUpDiscardPile().getPileType());
    assertEquals(PileType.OutOfGame, player.getOutOfGamePile().getPileType());
    assertEquals(PileType.Play, player.getPlayPile().getPileType());
  }

  /**
   * Method under test: {@link Player#drawPlayCards()}
   */
  @Test
  void testDrawPlayCards2() {
    // Arrange
    Player player = new Player("Name");
    player.setPlayPile(new CardPile());

    // Act
    player.drawPlayCards();

    // Assert
    assertFalse(player.getPlayPile().isEmpty());
    assertTrue(player.getDrawPile().isEmpty());
  }

  /**
   * Method under test: {@link Player#drawPlayCards()}
   */
  @Test
  void testDrawPlayCards3() {
    // Arrange
    Player player = new Player("Name");
    player.setDrawPile(new CardPile());
    player.setPlayPile(new CardPile());

    // Act
    player.drawPlayCards();

    // Assert
    assertTrue(player.getDrawPile().isEmpty());
    assertTrue(player.getPlayPile().isEmpty());
  }

  /**
   * Method under test: {@link Player#removeResourceFromGame(Resource)}
   */
  @Test
  void testRemoveCardFromGame() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.removeResourceFromGame(new Card(CardType.Kundeschafter));

    // Assert
    assertFalse(player.getOutOfGamePile().isEmpty());
    assertFalse(player.getPlayPile().isEmpty());
  }

  /**
   * Method under test: {@link Player#discardCoin(CaveCoin)}
   */
  @Test
  void testDiscardCoin() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.discardCoin(new CaveCoin(1, CaveCoinType.Machete));

    // Assert
    assertFalse(player.getCaveCoinPile().isEmpty());
  }

  /**
   * Method under test: {@link Player#xRayEyes()}
   */
  @Test
  void testXRayEyes() {
    // Arrange
    Player player = new Player("Name");

    // Act
    player.xRayEyes();

    // Assert that nothing has changed
    assertEquals("Name", player.getName());
    assertEquals(0, player.getBlockadeCount());
    assertEquals(PileType.Discard, player.getDiscardPile().getPileType());
    assertEquals(PileType.FaceUpDiscard, player.getFaceUpDiscardPile().getPileType());
    assertEquals(PileType.OutOfGame, player.getOutOfGamePile().getPileType());
    assertEquals(PileType.Play, player.getPlayPile().getPileType());
  }

  /**
   * Method under test: {@link Player#xRayEyes()}
   */
  @Test
  void testXRayEyes2() {
    // Arrange
    Player player = new Player("Name");
    player.setDrawPile(null);
    player.setDiscardPile(null);
    player.setOutOfGameCoinsPile(null);
    player.setCaveCoinPile(null);
    player.setFaceUpDiscardPile(null);
    player.setOutOfGamePile(null);
    player.setPlayPile(null);

    // Act
    player.xRayEyes();

    // Assert that nothing has changed
    assertEquals("Name", player.getName());
    assertEquals(0, player.getBlockadeCount());
  }

  /**
   * Method under test: {@link Player#xRayEyes()}
   */
  @Test
  void testXRayEyes3() {
    // Arrange
    CoinPile outOfGameCoinsPile = new CoinPile();
    outOfGameCoinsPile.setResources(null);

    Player player = new Player("Name");
    player.setDrawPile(null);
    player.setDiscardPile(null);
    player.setOutOfGameCoinsPile(outOfGameCoinsPile);
    player.setCaveCoinPile(null);
    player.setFaceUpDiscardPile(null);
    player.setOutOfGamePile(null);
    player.setPlayPile(null);

    // Act
    player.xRayEyes();

    // Assert that nothing has changed
    assertEquals("Name", player.getName());
    assertEquals(0, player.getBlockadeCount());
  }

  /**
   * Method under test: {@link Player#xRayEyes()}
   */
  @Test
  void testXRayEyes4() {
    // Arrange
    CardPile outOfGamePile = new CardPile();
    outOfGamePile.setResources(null);

    Player player = new Player("Name");
    player.setDrawPile(null);
    player.setDiscardPile(null);
    player.setOutOfGameCoinsPile(null);
    player.setCaveCoinPile(null);
    player.setFaceUpDiscardPile(null);
    player.setOutOfGamePile(outOfGamePile);
    player.setPlayPile(null);

    // Act
    player.xRayEyes();

    // Assert that nothing has changed
    assertEquals("Name", player.getName());
    assertEquals(0, player.getBlockadeCount());
  }

  /**
   * Method under test: {@link Player#xRayEyes()}
   */
  @Test
  void testXRayEyes5() {
    // Arrange
    COWArrayList<CaveCoin> resources = mock(COWArrayList.class);

    ArrayList<CaveCoin> caveCoinList = new ArrayList<>();
    Stream<CaveCoin> streamResult = caveCoinList.stream();
    when(resources.stream()).thenReturn(streamResult);

    CoinPile outOfGameCoinsPile = new CoinPile();
    outOfGameCoinsPile.setResources(resources);

    Player player = new Player("Name");
    player.setDrawPile(null);
    player.setDiscardPile(null);
    player.setOutOfGameCoinsPile(outOfGameCoinsPile);
    player.setCaveCoinPile(null);
    player.setFaceUpDiscardPile(null);
    player.setOutOfGamePile(null);
    player.setPlayPile(null);

    // Act
    player.xRayEyes();

    // Assert that nothing has changed
    verify(resources).stream();
    assertEquals("Name", player.getName());
    assertEquals(0, player.getBlockadeCount());
  }

  /**
   * Method under test: {@link Player#equals(Object)}
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Player player = new Player("Finn");
    Player player2 = new Player("Finn");

    // Act and Assert
    assertEquals(player, player2);
    int notExpectedHashCodeResult = player.hashCode();
    assertEquals(notExpectedHashCodeResult, player2.hashCode());
  }

  /**
   * Method under test: {@link Player#equals(Object)}
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    Player player = new Player("Stijn");
    player.addBlockade(mock(Blockade.class));
    Player player2 = new Player("Stijn");

    // Act and Assert
    assertEquals(player, player2);
    int notExpectedHashCodeResult = player.hashCode();
    assertEquals(notExpectedHashCodeResult, player2.hashCode());
  }

  /**
   * Method under test: {@link Player#equals(Object)}
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Player player = new Player("Name");

    // Act and Assert
    assertEquals(player, player);
    int expectedHashCodeResult = player.hashCode();
    assertEquals(expectedHashCodeResult, player.hashCode());
  }

  /**
   * Method under test: {@link Player#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Player player = new Player("org.utwente.CaveCoin.CaveCoin");

    // Act and Assert
    assertNotEquals(player, new Player("Name"));
  }

  /**
   * Method under test: {@link Player#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Player("Name"), null);
  }

  /**
   * Method under test: {@link Player#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Player("Name"), "Different type to Player");
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Player#getFaceUpDiscardPile()}
   *   <li>{@link Player#getName()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    Player player = new Player("Name");

    // Act
    CardPile actualFaceUpDiscardPile = player.getFaceUpDiscardPile();

    // Assert
    assertEquals("Name", player.getName());
    Player player2 = actualFaceUpDiscardPile.getPlayer();
    assertNull(player2.getMaxBlockade());
    assertNull(player2.getColor());
    CoinPile caveCoinPile = player2.getCaveCoinPile();
    assertNull(caveCoinPile.getPileType());
    CardPile drawPile = player2.getDrawPile();
    assertNull(drawPile.getPileType());
    CoinPile outOfGameCoinsPile = player2.getOutOfGameCoinsPile();
    assertNull(outOfGameCoinsPile.getPileType());
    List<Card> resources = drawPile.getResources();
    assertEquals(4, resources.size());
    Card getResult = resources.get(0);
    assertEquals(0, getResult.getConsumedPower());
    Card getResult2 = resources.get(1);
    assertEquals(0, getResult2.getConsumedPower());
    Card getResult3 = resources.get(2);
    assertEquals(0, getResult3.getConsumedPower());
    Card getResult4 = resources.get(3);
    assertEquals(0, getResult4.getConsumedPower());
    CardPile playPile = player2.getPlayPile();
    List<Card> resources2 = playPile.getResources();
    assertEquals(4, resources2.size());
    Card getResult5 = resources2.get(0);
    assertEquals(0, getResult5.getConsumedPower());
    Card getResult6 = resources2.get(1);
    assertEquals(0, getResult6.getConsumedPower());
    Card getResult7 = resources2.get(2);
    assertEquals(0, getResult7.getConsumedPower());
    Card getResult8 = resources2.get(3);
    assertEquals(0, getResult8.getConsumedPower());
    assertEquals(0, player2.getBlockadeCount());
    assertEquals(2, caveCoinPile.getResources().size());
    assertEquals(1, getResult.getPower());
    assertEquals(1, getResult2.getPower());
    assertEquals(1, getResult3.getPower());
    assertEquals(1, getResult4.getPower());
    assertEquals(1, getResult5.getPower());
    assertEquals(1, getResult6.getPower());
    assertEquals(1, getResult7.getPower());
    assertEquals(1, getResult8.getPower());
    CardPile discardPile = player2.getDiscardPile();
    assertEquals(PileType.Discard, discardPile.getPileType());
    assertEquals(PileType.FaceUpDiscard, actualFaceUpDiscardPile.getPileType());
    CardPile outOfGamePile = player2.getOutOfGamePile();
    assertEquals(PileType.OutOfGame, outOfGamePile.getPileType());
    assertEquals(PileType.Play, playPile.getPileType());
    assertFalse(caveCoinPile.isEmpty());
    assertFalse(drawPile.isEmpty());
    assertFalse(playPile.isEmpty());
    List<Card> cards = actualFaceUpDiscardPile.getCards();
    assertTrue(cards.isEmpty());
    List<Card> resources3 = discardPile.getResources();
    assertTrue(resources3.isEmpty());
    assertTrue(outOfGameCoinsPile.getResources().isEmpty());
    List<Card> resources4 = outOfGamePile.getResources();
    assertTrue(resources4.isEmpty());
    assertTrue(player2.getBlockades().isEmpty());
    assertTrue(discardPile.isEmpty());
    assertTrue(actualFaceUpDiscardPile.isEmpty());
    assertTrue(outOfGameCoinsPile.isEmpty());
    assertTrue(outOfGamePile.isEmpty());
    assertSame(player, caveCoinPile.getPlayer());
    assertSame(player, discardPile.getPlayer());
    assertSame(player, drawPile.getPlayer());
    assertSame(player, player2);
    assertSame(player, outOfGameCoinsPile.getPlayer());
    assertSame(player, outOfGamePile.getPlayer());
    assertSame(player, playPile.getPlayer());
    assertSame(cards, actualFaceUpDiscardPile.getResources());
    assertSame(resources3, discardPile.getCards());
    assertSame(resources, drawPile.getCards());
    assertSame(resources4, outOfGamePile.getCards());
    assertSame(resources2, playPile.getCards());
  }

  /**
   * Method under test: {@link Player#Player(String)}
   */
  @Test
  void testNewPlayer() {
    // Arrange and Act
    Player actualPlayer = new Player("Dina");

    // Assert
    assertEquals("Dina", actualPlayer.getName());
    CoinPile caveCoinPile = actualPlayer.getCaveCoinPile();
    assertNull(caveCoinPile.getPileType());
    CardPile drawPile = actualPlayer.getDrawPile();
    assertNull(drawPile.getPileType());
    CoinPile outOfGameCoinsPile = actualPlayer.getOutOfGameCoinsPile();
    assertNull(outOfGameCoinsPile.getPileType());
    List<Card> resources = drawPile.getResources();
    Card getResult = resources.get(0);
    assertEquals(0, getResult.getConsumedPower());
    Card getResult2 = resources.get(1);
    assertEquals(0, getResult2.getConsumedPower());
    Card getResult3 = resources.get(2);
    assertEquals(0, getResult3.getConsumedPower());
    Card getResult4 = resources.get(3);
    assertEquals(0, getResult4.getConsumedPower());
    CardPile playPile = actualPlayer.getPlayPile();
    List<Card> resources2 = playPile.getResources();
    Card getResult5 = resources2.get(0);
    assertEquals(0, getResult5.getConsumedPower());
    Card getResult6 = resources2.get(1);
    assertEquals(0, getResult6.getConsumedPower());
    Card getResult7 = resources2.get(2);
    assertEquals(0, getResult7.getConsumedPower());
    Card getResult8 = resources2.get(3);
    assertEquals(0, getResult8.getConsumedPower());
    assertEquals(0, actualPlayer.getBlockadeCount());
    assertEquals(1, getResult.getPower());
    assertEquals(1, getResult2.getPower());
    assertEquals(1, getResult3.getPower());
    assertEquals(1, getResult4.getPower());
    assertEquals(1, getResult5.getPower());
    assertEquals(1, getResult6.getPower());
    assertEquals(1, getResult7.getPower());
    assertEquals(1, getResult8.getPower());
    CardPile discardPile = actualPlayer.getDiscardPile();
    assertEquals(PileType.Discard, discardPile.getPileType());
    CardPile faceUpDiscardPile = actualPlayer.getFaceUpDiscardPile();
    assertEquals(PileType.FaceUpDiscard, faceUpDiscardPile.getPileType());
    CardPile outOfGamePile = actualPlayer.getOutOfGamePile();
    assertEquals(PileType.OutOfGame, outOfGamePile.getPileType());
    assertEquals(PileType.Play, playPile.getPileType());
    assertFalse(caveCoinPile.isEmpty());
    assertFalse(drawPile.isEmpty());
    assertFalse(playPile.isEmpty());
    assertTrue(discardPile.isEmpty());
    assertTrue(faceUpDiscardPile.isEmpty());
    assertTrue(outOfGameCoinsPile.isEmpty());
    assertTrue(outOfGamePile.isEmpty());
    assertSame(actualPlayer, caveCoinPile.getPlayer());
    assertSame(actualPlayer, discardPile.getPlayer());
    assertSame(actualPlayer, drawPile.getPlayer());
    assertSame(actualPlayer, faceUpDiscardPile.getPlayer());
    assertSame(actualPlayer, outOfGameCoinsPile.getPlayer());
    assertSame(actualPlayer, outOfGamePile.getPlayer());
    assertSame(actualPlayer, playPile.getPlayer());
    assertSame(resources2, playPile.getCards());
  }
}


