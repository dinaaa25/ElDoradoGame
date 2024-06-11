package org.utwente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.game.model.Action;
import org.utwente.game.model.MoveAction;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveActionTest {

    Tile tileTo;
    Tile tileFrom;
    Player dina;
    Player mark;

    @BeforeEach
    public void setUp() {
        tileTo = new Tile(0, 1, TileType.Machete, 2, new ArrayList<>(), false);
        tileFrom = new Tile(0, 0, TileType.Paddle, 2, new ArrayList<>(), false);
        dina = new Player("Dina");
        mark = new Player("Mark");
        tileFrom.placePlayer(dina);
    }

    @Test
    public void testMovePlayerToTile() {
        Resource card = new Card(CardType.Entdecker);
        Action move = new MoveAction(dina, card, tileFrom, tileTo);

        assertEquals(tileFrom.isEmpty(), false);
        assertEquals(tileTo.isEmpty(), true);

        move.execute();

        assertEquals(1, tileTo.getPlayers().size());
        assertTrue(tileTo.getPlayers().stream().map(player -> player.getName()).toList().contains("Dina"));
    }

    @Test
    public void testIsTileToNeighbour() {
        Resource card = new Card(CardType.Entdecker);
        MoveAction move = new MoveAction(dina, card, tileFrom, tileTo);
        assertEquals(move.isTileToNeighbour(), true);
    }

    @Test
    public void testIsPlayerOnTile() {
        Resource card = new Card(CardType.Entdecker);
        Action move = new MoveAction(dina, card, tileFrom, tileTo);
        tileTo.placePlayer(mark);

        assertFalse(tileFrom.isEmpty());
        assertFalse(tileTo.isEmpty());

        move.execute();

        assertNotNull(tileTo.getPlayers());
        assertTrue(tileTo.getPlayers().stream().map(player -> player.getName()).toList().contains("Dina"));
    }

    @Test
    public void testResourceHasEnoughPowerTrue() {
        Resource card = new Card(CardType.Entdecker);
        MoveAction move = new MoveAction(dina, card, tileFrom, tileTo);
        assertTrue(move.resourceHasEnoughPower());
    }

    @Test
    public void testResourceHasEnoughPowerFalse() {
        Resource card = new Card(CardType.Tausendsassa);
        MoveAction move = new MoveAction(dina, card, tileFrom, tileTo);
        assertFalse(move.resourceHasEnoughPower());
    }

    @Test
    public void testIsCardMatchingTileTrue() {
        List<CardType> macheteCards = List.of(CardType.Kundeschafter, CardType.Forscher,
                CardType.Entdecker, CardType.Pionier, CardType.MachtigeMachete);

        for (CardType macheteCard : macheteCards) {
            Resource card = new Card(macheteCard);
            MoveAction move = new MoveAction(dina, card, tileFrom, tileTo);
            assertTrue(move.isCardMatchingTile());
        }
    }

    @Test
    public void testIsCardMatchingTileFalse() {
        List<CardType> macheteCards = List.of(CardType.Kapitan, CardType.Matrose,
                CardType.Reisende, CardType.Fotografin, CardType.Schatztruhe,
                CardType.Millionarin, CardType.Journalistin, CardType.Journalistin,
                CardType.Kartograph, CardType.Kompass, CardType.Wissenschaftlerin,
                CardType.Ureinwohner, CardType.Fernsprechgerat, CardType.Reisetagebuch);

        for (CardType macheteCard : macheteCards) {
            Resource card = new Card(macheteCard);
            MoveAction move = new MoveAction(dina, card, tileFrom, tileTo);
            assertFalse(move.isCardMatchingTile());
        }
    }

}
