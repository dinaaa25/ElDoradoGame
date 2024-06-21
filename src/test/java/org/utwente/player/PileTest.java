package org.utwente.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.player.model.Pile;
import org.utwente.player.model.PileBuilder;
import org.utwente.player.model.PileType;

public class PileTest {
  Pile pile;
  PileBuilder builder;

  @BeforeEach
  public void setup() {
    builder = new PileBuilder();
    pile = builder.addCard(CardType.Entdecker).build();
  }

  @Test
  public void testAdd() {
    Card card = new Card(CardType.Fotografin);
    pile.add(card);
    assertTrue(pile.getResources().contains(card));
  }

  @Test
  public void testRemove() {
    Card card = new Card(CardType.Fotografin);
    pile.add(card);
    assertTrue(pile.getResources().contains(card));
    pile.remove(card);
    assertFalse(pile.getResources().contains(card));
  }

  @Test
  public void testUnion() {
    Pile otherPile = builder.addCard(CardType.Kompass).build();
    assertEquals(1, otherPile.getResources().size());
    Pile thirdPile = null;

    thirdPile = otherPile.union(pile);

    assertNotNull(thirdPile);
    assertEquals(2, thirdPile.getResources().size());
    List<CardType> cardTypes = thirdPile.getResources().stream().map(e -> e.getCardType()).toList();
    assertTrue(cardTypes.contains(CardType.Kompass));
    assertTrue(cardTypes.contains(CardType.Entdecker));
  }

  @Test
  public void testDifferentPiles() {
    Pile discard = new Pile();
    discard.setPileType(PileType.Discard);

    assertEquals(discard.getPileType(), PileType.Discard);

    Pile outOfGame = new Pile();
    outOfGame.setPileType(PileType.OutOfGame);
    assertEquals(outOfGame.getPileType(), PileType.OutOfGame);

    Pile play = new Pile();
    play.setPileType(PileType.Play);
    assertEquals(play.getPileType(), PileType.Play);

    Pile draw = new Pile();
    draw.setPileType(PileType.Draw);
    assertEquals(draw.getPileType(), PileType.Draw);
  }

  @Test
  public void testShuffle() {
    pile.shuffle();
    assertEquals(1, pile.getResources().size());
  }

  @Test
  public void testSetCards() {
    pile.setResources(List.of(new Card(CardType.Entdecker), new Card(CardType.Kompass)));
    assertEquals(2, pile.getResources().size());
  }

  @Test
  public void testHashCode() {
    assertNotNull(pile.hashCode());
  }

}
