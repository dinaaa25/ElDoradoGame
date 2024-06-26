package org.utwente.player.model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.PowerType;
import org.utwente.market.model.Resource;



class PileTest {
  /**
   * Method under test: {@link Pile#union(Pile)}
   */
  @Test
  void testUnion() {
    // Arrange
    Pile pile = new Pile();

    // Act
    Pile actualUnionResult = pile.union(new Pile());

    // Assert
    assertNull(actualUnionResult.getPileType());
    assertNull(actualUnionResult.getPlayer());
    assertTrue(actualUnionResult.getResources().isEmpty());
  }
  /**
   * Method under test: {@link Pile#union(Pile)}
   */
  @Test
  void testUnionWithNullPile() {
    // Arrange
    Pile pile = new Pile();

    // Act & Assert
    assertThrows(NullPointerException.class, () -> pile.union(null));
  }
  /**
   * Method under test: {@link Pile#union(Pile)}
   */
  @Test
  void testUnionWithNonEmptyPile() {
    // Arrange
    List<Resource> resources = List.of(mock(Resource.class), mock(Resource.class));
    Pile pile1 = new Pile(resources, mock(Player.class), mock(PileType.class));
    Pile pile2 = new Pile(resources, mock(Player.class), mock(PileType.class));

    // Act
    Pile actualUnionResult = pile1.union(pile2);

    // Assert
    assertEquals(4, actualUnionResult.getResources().size());
    assertEquals(pile1.getPileType(), actualUnionResult.getPileType());
    assertEquals(pile1.getPlayer(), actualUnionResult.getPlayer());
  }
  /**
   * Method under test: {@link Pile#union(Pile)}
   */
  @Test
  void testUnionWithEmptyPile() {
    // Arrange
    Pile pile1 = new Pile(List.of(mock(Card.class)), mock(Player.class), mock(PileType.class));
    Pile pile2 = new Pile();

    // Act
    Pile actualUnionResult = pile1.union(pile2);

    // Assert
    assertEquals(1, actualUnionResult.getResources().size());
    assertEquals(pile1.getPileType(), actualUnionResult.getPileType());
    assertEquals(pile1.getPlayer(), actualUnionResult.getPlayer());
  }

  /**
   * Method under test: {@link Pile#addAll(Pile)}
   */
  @Test
  void testAddAll() {
    // Arrange
    Pile pile = new Pile();

    // Act
    pile.addAll(new Pile());

    // Assert
    assertTrue(pile.getResources().isEmpty());
  }

  /**
   * Method under test: {@link Pile#remove(Card)}
   */
  @Test
  void testRemove() {
    // Arrange
    Pile pile = new Pile();

    // Act and Assert
    assertFalse(pile.remove(new Card(CardType.Kundeschafter)));
    assertTrue(pile.getResources().isEmpty());
  }

  /**
   * Method under test: {@link Pile#add(Resource)}
   */
  @Test
  void testAdd() {
    // Arrange
    Pile pile = new Pile();

    // Act
    boolean actualAddResult = pile.add(new Card(CardType.Kundeschafter));

    // Assert
    assertEquals(1, pile.getResources().size());
    assertTrue(actualAddResult);
  }
  /**
   * Method under test: {@link Pile#addAll(Pile)}
   */
  @Test
  void testAddAllWithNullPile() {
    // Arrange
    Pile pile = new Pile();

    // Act & Assert
    assertThrows(NullPointerException.class, () -> pile.addAll(null));
  }

  /**
   * Method under test: {@link Pile#shuffle()}
   */
  @Test
  void testShuffle() {
    // Arrange
    Pile pile = new Pile();

    // Act
    pile.shuffle();

    // Assert
    assertTrue(pile.getResources().isEmpty());
  }

  /**
   * Method under test: {@link Pile#draw(int)}
   */
  @Test
  void testDraw() {
    // Arrange
    Pile pile = new Pile();

    // Act
    Pile actualDrawResult = pile.draw(1);

    // Assert
    assertNull(actualDrawResult.getPileType());
    assertNull(actualDrawResult.getPlayer());
    assertTrue(pile.getResources().isEmpty());
    assertTrue(actualDrawResult.getResources().isEmpty());
  }
  /**
   * Method under test: {@link Pile#draw(int)}
   */
  @Test
  void testDrawWithNegativeCardAmount() {
    // Arrange
    Pile pile = new Pile();

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> pile.draw(-1));
    assertEquals("Cannot draw a negative amount of cards", exception.getMessage());
  }
  /**
   * Method under test: {@link Pile#draw(int)}
   */
  @Test
  void testDrawWithEmptyPile() {
    // Arrange
    Pile pile = new Pile();

    // Act
    Pile actualDrawResult = pile.draw(5);

    // Assert
    assertTrue(actualDrawResult.getResources().isEmpty());
    assertTrue(pile.getResources().isEmpty());
  }


  /**
   * Method under test: {@link Pile#draw(int)}
   */
  @Test
  void testDraw2() {
    // Arrange
    ArrayList<Resource> resources = new ArrayList<>();
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));
    resources.add(new Card(CardType.Kundeschafter));

    Pile pile = new Pile();
    pile.setPileType(PileType.Draw);
    pile.setResources(resources);

    // Act
    Pile actualDrawResult = pile.draw(1);

    // Assert
    assertNull(actualDrawResult.getPlayer());
    assertEquals(1, actualDrawResult.getResources().size());
    assertEquals(17, pile.getResources().size());
    assertEquals(PileType.Draw, actualDrawResult.getPileType());
  }

  /**
   * Method under test: {@link Pile#draw(int)}
   */
  @Test
  void testDraw5() {
    // Arrange
    Pile pile = new Pile();
    pile.setPlayer(new Player("Name"));
    pile.setPileType(PileType.Draw);

    // Act
    Pile actualDrawResult = pile.draw(1);

    // Assert
    assertEquals(0, actualDrawResult.getPlayer().getBlockadeCount());
    assertEquals(PileType.Draw, actualDrawResult.getPileType());
    assertTrue(pile.getResources().isEmpty());
    assertTrue(actualDrawResult.getResources().isEmpty());
  }

  /**
   * Method under test: {@link Pile#Pile()}
   */
  @Test
  void testNewPile() {
    // Arrange and Act
    Pile actualPile = new Pile();

    // Assert
    assertNull(actualPile.getPileType());
    assertNull(actualPile.getPlayer());
    assertTrue(actualPile.getResources().isEmpty());
  }

}
