package org.utwente.game;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MoveActionTest {

    Tile tileTo;
    Tile tileFrom;
    Player dina;
    Player mark;


    @BeforeEach
    public void setUp() {
        tileTo = new Tile(0, 0, TileType.Machete, 1, new ArrayList<>());
        tileFrom = new Tile(0, 0, TileType.Paddle, 1, new ArrayList<>());
        dina = new Player("Dina");
        mark = new Player("Mark");
    }

    @Test
    public void testMovePlayerToTile() {
        tileFrom.placePlayer(dina);
        Resource card = new Card(CardType.Entdecker);
        Action move = new MoveAction(dina, card, tileFrom, tileTo);

        assertEquals(tileFrom.isEmpty(), true);
        assertNotNull(tileTo.getPlayers());
        assertTrue(tileTo.getPlayers().stream().map(player -> player.getName()).toList().contains("Dina"));
    }

    @Test
    public void testIsTileToNeighbour() {

    }

    @Test
    public void testIsPlayerOnTile() {
        tileFrom.placePlayer(dina);
        Resource card = new Card(CardType.Entdecker);
        Action move = new MoveAction(dina, card, tileFrom, tileTo);

        assertEquals(tileFrom.isEmpty(), true);
        assertNotNull(tileTo.getPlayers());
        assertTrue(tileTo.getPlayers().stream().map(player -> player.getName()).toList().contains("Dina"));
    }

    @Test
    public void testGetTileType() {

    }

    @Test
    public void testIsTileToFilled() {

    }

    @Test
    public void testResourceHasEnoughPower() {

    }

    @Test
    public void testIsCardMatchingTile() {

    }

}
