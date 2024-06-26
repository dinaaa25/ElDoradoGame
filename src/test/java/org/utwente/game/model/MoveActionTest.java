package org.utwente.game.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveActionTest {

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
    @Disabled
    //todo: re do after Move-player branch gets merged
    public void testMovePlayerToTile() {
        List<Resource> resources = new ArrayList<>();
        Resource card = new Card(CardType.Entdecker);
        resources.add(card);
        Action move = new MoveAction(dina, resources, tileFrom, tileTo);

        assertFalse(tileFrom.isEmpty());
        assertTrue(tileTo.isEmpty());

        move.execute();

        assertEquals(1, tileTo.getPlayers().size());
        assertTrue(tileTo.getPlayers().stream().map(Player::getName).toList().contains("Dina"));
    }



    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    public void testIsPlayerOnTile() {
        List<Resource> resources = new ArrayList<>();
        Resource card = new Card(CardType.Entdecker);
        resources.add(card);
        MoveAction move = new MoveAction(dina, resources, tileFrom, tileTo);
        tileTo.placePlayer(mark);

        assertFalse(tileFrom.isEmpty());
        assertFalse(tileTo.isEmpty());

        move.execute();

        assertNotNull(tileTo.getPlayers());
        assertTrue(tileTo.getPlayers().stream().map(Player::getName).toList().contains("Dina"));
    }

    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    public void testResourceHasEnoughPowerTrue() {
        List<Resource> resources = new ArrayList<>();
        Resource card = new Card(CardType.Entdecker);
        resources.add(card);
        MoveAction move = new MoveAction(dina, resources, tileFrom, tileTo);
        assertTrue(move.resourceHasEnoughPower());
    }

    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    public void testResourceHasEnoughPowerFalse() {
        List<Resource> resources = new ArrayList<>();
        Resource card = new Card(CardType.Tausendsassa);
        resources.add(card);
        MoveAction move = new MoveAction(dina, resources, tileFrom, tileTo);
        assertFalse(move.resourceHasEnoughPower());
    }

    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    public void testIsCardMatchingTileTrue() {
        List<CardType> macheteCards = List.of(CardType.Kundeschafter, CardType.Forscher,
                CardType.Entdecker, CardType.Pionier, CardType.MachtigeMachete);

        for (CardType macheteCard : macheteCards) {
            List<Resource> resources = new ArrayList<>();
            Resource card = new Card(macheteCard);
            resources.add(card);
            MoveAction move = new MoveAction(dina, resources, tileFrom, tileTo);
            assertTrue(move.isCardMatchingTile());
        }
    }

    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    public void testIsCardMatchingTileFalse() {
        List<CardType> nonMacheteCards = List.of(CardType.Kapitan, CardType.Matrose,
                CardType.Reisende, CardType.Fotografin, CardType.Schatztruhe,
                CardType.Millionarin, CardType.Journalistin, CardType.Journalistin,
                CardType.Kartograph, CardType.Kompass, CardType.Wissenschaftlerin,
                CardType.Ureinwohner, CardType.Fernsprechgerat, CardType.Reisetagebuch);

        for (CardType nonMacheteCard : nonMacheteCards) {
            List<Resource> resources = new ArrayList<>();
            Resource card = new Card(nonMacheteCard);
            resources.add(card);
            MoveAction move = new MoveAction(dina, resources, tileFrom, tileTo);
            assertFalse(move.isCardMatchingTile());
        }
    }

    /**
     * Method under test: {@link MoveAction#execute()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testExecute() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        MoveAction moveAction = new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true));

        // Act
        moveAction.execute();

        // Assert
        assertFalse(moveAction.isNoPlayerOnToTile());
        assertTrue(moveAction.getTileFrom().isEmpty());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testValidate() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).validate());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testValidate2() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).validate());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testValidate3() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));

        // Act and Assert
        assertFalse(new MoveAction(player, resources, null, new Tile(1, 1, TileType.Machete, 1, true)).validate());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testValidate4() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Paddle, 1, true)).validate());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testValidate5() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 6, true)).validate());
    }

    /**
     * Method under test: {@link MoveAction#isTileToNeighbour()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testIsTileToNeighbour() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).isTileToNeighbour());
    }

    /**
     * Method under test: {@link MoveAction#isTileToNeighbour()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testIsTileToNeighbour2() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).isTileToNeighbour());
    }

    /**
     * Method under test: {@link MoveAction#isTileToNeighbour()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testIsTileToNeighbour3() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));

        // Act and Assert
        assertFalse(new MoveAction(player, resources, null, new Tile(1, 1, TileType.Machete, 1, true)).isTileToNeighbour());
    }

    /**
     * Method under test: {@link MoveAction#isTileToMountain()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testGetTileType() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        MoveAction moveAction = new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true));

        // Act and Assert
        //assertEquals(TileType.Mountain, moveAction.getTileType(new Tile(1, 1, TileType.Machete, 1, true)));
        assertEquals(TileType.Mountain, moveAction.isTileToMountain());
    }

    /**
     * Method under test: {@link MoveAction#isNoPlayerOnToTile()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testIsNoPlayerOnToTile() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).isNoPlayerOnToTile());
    }

    /**
     * Method under test: {@link MoveAction#resourceHasEnoughPower()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testResourceHasEnoughPower() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).resourceHasEnoughPower());
    }

    /**
     * Method under test: {@link MoveAction#resourceHasEnoughPower()}
     */
    @Test
    void testResourceHasEnoughPower2() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        resource.setConsumedPower(2);
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).resourceHasEnoughPower());
    }

    /**
     * Method under test: {@link MoveAction#isCardMatchingTile()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testIsCardMatchingTile() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Machete, 1, true)).isCardMatchingTile());
    }

    /**
     * Method under test: {@link MoveAction#isCardMatchingTile()}
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testIsCardMatchingTile2() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(new MoveAction(player, resources, from, new Tile(1, 1, TileType.Paddle, 1, true)).isCardMatchingTile());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link MoveAction#MoveAction(Player, List<Resource>, Tile, Tile)}
     *   <li>{@link MoveAction#getTileFrom()}
     *   <li>{@link MoveAction#getTileTo()}
     * </ul>
     */
    @Test
    @Disabled
    //todo: re do after Move-player branch gets merged
    void testGettersAndSetters() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        Tile resultTo = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        MoveAction actualMoveAction = new MoveAction(player, resources, from, resultTo);
        Tile actualTileFrom = actualMoveAction.getTileFrom();
        Tile actualTileTo = actualMoveAction.getTileTo();

        // Assert
        Player player2 = actualMoveAction.getPlayer();
        assertEquals("Name", player2.getName());
        assertNull(player2.getMaxBlockade());
        assertNull(player2.getColor());
        assertNull(player2.getOutOfGamePile());
        assertEquals(0, player2.getBlockadeCount());
        assertSame(from, actualTileFrom);
        assertSame(resultTo, actualTileTo);
    }
}