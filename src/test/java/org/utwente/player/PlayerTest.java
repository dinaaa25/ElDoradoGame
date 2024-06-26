package org.utwente.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade.*;
import org.utwente.player.model.Player;

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
}