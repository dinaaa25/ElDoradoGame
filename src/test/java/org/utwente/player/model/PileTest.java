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
    Pile<Object> pile = new Pile<>();

    // Act
    Pile<Object> actualUnionResult = pile.union(new Pile<>());

    // Assert
    assertNull(actualUnionResult.getPileType());
    assertNull(actualUnionResult.getPlayer());
    assertTrue(actualUnionResult.isEmpty());
  }

  /**
   * Method under test: {@link Pile#addAll(Pile)}
   */
  @Test
  void testAddAll() {
    // Arrange
    Pile<Object> pile = new Pile<>();

    // Act
    pile.addAll(new Pile<>());

    // Assert
    assertTrue(pile.isEmpty());
  }

  /**
   * Method under test: {@link Pile#remove(Object)}
   */
  @Test
  void testRemove() {
    // Arrange
    Pile<Object> pile = new Pile<>();

    // Act and Assert
    assertFalse(pile.remove("Card"));
    assertTrue(pile.isEmpty());
  }

  /**
   * Method under test: {@link Pile#remove(Object)}
   */
  @Test
  void testRemove2() {
    // Arrange
    Pile<Object> pile = new Pile<>();
    pile.add("Card");

    // Act
    boolean actualRemoveResult = pile.remove("Card");

    // Assert
    assertTrue(pile.isEmpty());
    assertTrue(actualRemoveResult);
  }

  /**
   * Method under test: {@link Pile#add(Object)}
   */
  @Test
  void testAdd() {
    // Arrange
    Pile<Object> pile = new Pile<>();

    // Act
    boolean actualAddResult = pile.add("Card");

    // Assert
    assertFalse(pile.isEmpty());
    assertTrue(actualAddResult);
  }

  /**
   * Method under test: {@link Pile#shuffle()}
   */
  @Test
  void testShuffle() {
    // Arrange
    Pile<Object> pile = new Pile<>();

    // Act
    pile.shuffle();

    // Assert
    assertTrue(pile.isEmpty());
  }

  /**
   * Method under test: {@link Pile#isEmpty()}
   */
  @Test
  void testIsEmpty() {
    // Arrange
    Pile<Object> pile = new Pile<>();

    // Act and Assert
    assertTrue(pile.isEmpty());
  }

  /**
   * Method under test: {@link Pile#isEmpty()}
   */
  @Test
  void testIsEmpty2() {
    // Arrange
    Pile<Object> pile = new Pile<>();
    pile.add("Card");

    // Act and Assert
    assertFalse(pile.isEmpty());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Pile#Pile()}
   *   <li>{@link Pile#toString()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    Pile<Object> actualPile = new Pile<>();

    // Assert
    assertEquals("Pile null (Optional.empty)", actualPile.toString());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Pile#Pile(List, Player, PileType)}
   *   <li>{@link Pile#toString()}
   * </ul>
   */
  @Test
  void testGettersAndSetters2() {
    // Arrange
    ArrayList<Object> resources = new ArrayList<>();

    // Act
    Pile<Object> actualPile = new Pile<>(resources, new Player("Name"), PileType.Discard);

    // Assert
    assertEquals("Pile Discard (Optional.empty)", actualPile.toString());
  }
}
