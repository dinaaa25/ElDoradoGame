package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Section.Section;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

class MoveActionTest {
    /**
     * Method under test: {@link MoveAction#execute()}
     */
    @Test
    void testExecute() {
        // Arrange
        Tile resultTo = new Tile(1, 1, TileType.Machete, 1, true);
        ArrayList<Section> sections = new ArrayList<>();
        resultTo.setBoard(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));
        Player player = new Player("foo");
        Card resource = new Card(CardType.Kundeschafter);
        MoveAction moveAction = new MoveAction(player, resource, new Tile(1, 1, TileType.Machete, 1, true), resultTo);

        // Act
        moveAction.execute();

        // Assert
        assertEquals(1, moveAction.getResource().getConsumedPower());
        assertFalse(moveAction.isNoPlayerOnToTile());
        assertTrue(moveAction.getTileFrom().isEmpty());
    }

    /**
     * Method under test: {@link MoveAction#execute()}
     */
    @Test
    void testExecute2() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        resource.setConsumedPower(2);
        Player player = new Player("foo");
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).execute());
    }

    /**
     * Method under test: {@link MoveAction#execute()}
     */
    @Test
    void testExecute3() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Tile resultTo = new Tile(1, 1, TileType.Machete, 1, true);
        resultTo.setBoard(board);
        Player player = new Player("foo");
        Card resource = new Card(CardType.Kundeschafter);
        MoveAction moveAction = new MoveAction(player, resource, new Tile(1, 1, TileType.Machete, 1, true), resultTo);

        // Act
        moveAction.execute();

        // Assert
        assertEquals(1, moveAction.getResource().getConsumedPower());
        assertFalse(moveAction.isNoPlayerOnToTile());
        assertTrue(moveAction.getTileFrom().isEmpty());
    }

    /**
     * Method under test: {@link MoveAction#execute()}
     */
    @Test
    void testExecute4() {
        // Arrange
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);
        from.setBlockade(new Blockade(TileType.Machete, 2, 2));

        Tile resultTo = new Tile(1, 1, TileType.Machete, 1, true);
        ArrayList<Section> sections = new ArrayList<>();
        resultTo.setBoard(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));
        Player player = new Player("foo");
        MoveAction moveAction = new MoveAction(player, new Card(CardType.Kundeschafter), from, resultTo);

        // Act
        moveAction.execute();

        // Assert
        assertEquals(1, moveAction.player.getBlockadeCount());
        assertEquals(2, moveAction.getResource().getConsumedPower());
        assertFalse(moveAction.getTileFrom().isBlockadeTile());
    }

    /**
     * Method under test: {@link MoveAction#execute()}
     */
    @Test
    void testExecute5() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(2, 2, TileType.Machete, 2, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Tile resultTo = new Tile(1, 1, TileType.Machete, 1, true);
        resultTo.setBoard(board);
        Player player = new Player("foo");
        Card resource = new Card(CardType.Kundeschafter);
        MoveAction moveAction = new MoveAction(player, resource, new Tile(1, 1, TileType.Machete, 1, true), resultTo);

        // Act
        moveAction.execute();

        // Assert
        assertEquals(1, moveAction.getResource().getConsumedPower());
        assertFalse(moveAction.isNoPlayerOnToTile());
        assertTrue(moveAction.getTileFrom().isEmpty());
    }

    /**
     * Method under test: {@link MoveAction#execute()}
     */
    @Test
    void testExecute6() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 2, TileType.Machete, 2, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Tile resultTo = new Tile(1, 1, TileType.Machete, 1, true);
        resultTo.setBoard(board);
        Player player = new Player("foo");
        Card resource = new Card(CardType.Kundeschafter);
        MoveAction moveAction = new MoveAction(player, resource, new Tile(1, 1, TileType.Machete, 1, true), resultTo);

        // Act
        moveAction.execute();

        // Assert
        assertEquals(1, moveAction.getResource().getConsumedPower());
        assertFalse(moveAction.isNoPlayerOnToTile());
        assertTrue(moveAction.getTileFrom().isEmpty());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    void testValidate() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        ValidationResult actualValidateResult = (new MoveAction(player, resource, from,
                new Tile(1, 1, TileType.Machete, 1, true))).validate();

        // Assert
        assertEquals("Destination tile is not a neighbor of the player's tile.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    void testValidate2() {
        // Arrange
        Player player = new Player("Name");
        CaveCoin resource = new CaveCoin(1, CaveCoinType.Machete);

        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        ValidationResult actualValidateResult = (new MoveAction(player, resource, from,
                new Tile(1, 1, TileType.Machete, 1, true))).validate();

        // Assert
        assertEquals("Destination tile is not a neighbor of the player's tile.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    void testValidate3() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act
        ValidationResult actualValidateResult = (new MoveAction(player, resource, from,
                new Tile(1, 1, TileType.Machete, 1, true))).validate();

        // Assert
        assertEquals("", actualValidateResult.getMessage());
        assertTrue(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    void testValidate4() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act
        ValidationResult actualValidateResult = (new MoveAction(player, resource, null,
                new Tile(1, 1, TileType.Machete, 1, true))).validate();

        // Assert
        assertEquals("Destination tile is not a neighbor of the player's tile.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    void testValidate5() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act
        ValidationResult actualValidateResult = (new MoveAction(player, resource, from,
                new Tile(1, 1, TileType.Paddle, 1, true))).validate();

        // Assert
        assertEquals("The power type of the card or coin does not match the tile.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    void testValidate6() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act
        ValidationResult actualValidateResult = (new MoveAction(player, resource, from,
                new Tile(1, 1, TileType.Machete, 5, true))).validate();

        // Assert
        assertEquals("Chosen card or coin does not have enough power.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link MoveAction#validate()}
     */
    @Test
    void testValidate7() {
        // Arrange
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);
        from.setBlockade(new Blockade(TileType.Machete, 1, 1));
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act
        ValidationResult actualValidateResult = (new MoveAction(player, resource, from,
                new Tile(1, 1, TileType.Machete, 1, true))).validate();

        // Assert
        assertEquals("", actualValidateResult.getMessage());
        assertTrue(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link MoveAction#isTileToMountain()}
     */
    @Test
    void testIsTileToMountain() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse((new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).isTileToMountain());
    }

    /**
     * Method under test: {@link MoveAction#isTileToMountain()}
     */
    @Test
    void testIsTileToMountain2() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue((new MoveAction(player, resource, from, new Tile(1, 1, TileType.Mountain, 1, true))).isTileToMountain());
    }

    /**
     * Method under test: {@link MoveAction#discard()}
     */
    @Test
    void testDiscard() {
        // Arrange
        Player player = new Player("Name");
        Card card = new Card(CardType.Kundeschafter);
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        MoveAction moveAction = new MoveAction(player, card, tile, new Tile(1, 1, TileType.Machete, 1, true));

        // Act
        moveAction.discard();

        // Assert that nothing has changed
        assertEquals(0, moveAction.player.getBlockadeCount());
        assertEquals(1, moveAction.resources.size());
        assertTrue(moveAction.isNoPlayerOnToTile());
    }

    /**
     * Method under test: {@link MoveAction#discard()}
     */
    @Test
    void testDiscard2() {
        // Arrange
        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(2);
        Player player = new Player("Name");
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        MoveAction moveAction = new MoveAction(player, card, tile, new Tile(1, 1, TileType.Machete, 1, true));

        // Act
        moveAction.discard();

        // Assert
        Player player2 = moveAction.player;
        assertFalse(player2.getFaceUpDiscardPile().isEmpty());
        assertFalse(player2.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link MoveAction#isTileToNeighbour()}
     */
    @Test
    void testIsTileToNeighbour() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).isTileToNeighbour());
    }

    /**
     * Method under test: {@link MoveAction#isTileToNeighbour()}
     */
    @Test
    void testIsTileToNeighbour2() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(2, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue((new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).isTileToNeighbour());
    }

    /**
     * Method under test: {@link MoveAction#isTileToNeighbour()}
     */
    @Test
    void testIsTileToNeighbour3() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertFalse(
                (new MoveAction(player, resource, null, new Tile(1, 1, TileType.Machete, 1, true))).isTileToNeighbour());
    }

    /**
     * Method under test: {@link MoveAction#isNoPlayerOnToTile()}
     */
    @Test
    void testIsNoPlayerOnToTile() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).isNoPlayerOnToTile());
    }

    /**
     * Method under test: {@link MoveAction#resourceHasEnoughPower()}
     */
    @Test
    void testResourceHasEnoughPower() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).resourceHasEnoughPower());
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
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).resourceHasEnoughPower());
    }

    /**
     * Method under test: {@link MoveAction#resourceHasEnoughPower()}
     */
    @Test
    void testResourceHasEnoughPower3() {
        // Arrange
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);
        from.setBlockade(new Blockade(TileType.Machete, 2, 2));
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertTrue(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).resourceHasEnoughPower());
    }

    /**
     * Method under test: {@link MoveAction#resourceHasEnoughPower()}
     */
    @Test
    void testResourceHasEnoughPower4() {
        // Arrange
        Blockade blockade = new Blockade(TileType.Machete, 2, 2);
        blockade.setRemoved(true);

        Tile from = new Tile(1, 1, TileType.Machete, 1, true);
        from.setBlockade(blockade);
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertTrue(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).resourceHasEnoughPower());
    }

    /**
     * Method under test: {@link MoveAction#isCardMatchingTile()}
     */
    @Test
    void testIsCardMatchingTile() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).isCardMatchingTile());
    }

    /**
     * Method under test: {@link MoveAction#isCardMatchingTile()}
     */
    @Test
    void testIsCardMatchingTile2() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Paddle, 1, true))).isCardMatchingTile());
    }

    /**
     * Method under test: {@link MoveAction#isCardMatchingTile()}
     */
    @Test
    void testIsCardMatchingTile3() {
        // Arrange
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);
        from.setBlockade(new Blockade(TileType.Machete, 8, 8));
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertTrue(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).isCardMatchingTile());
    }

    /**
     * Method under test: {@link MoveAction#isCardMatchingTile()}
     */
    @Test
    void testIsCardMatchingTile4() {
        // Arrange
        Blockade blockade = new Blockade(TileType.Machete, 8, 8);
        blockade.setRemoved(true);

        Tile from = new Tile(1, 1, TileType.Machete, 1, true);
        from.setBlockade(blockade);
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertTrue(
                (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).isCardMatchingTile());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link MoveAction#getTileFrom()}
     *   <li>{@link MoveAction#getTileTo()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        Tile resultTo = new Tile(1, 1, TileType.Machete, 1, true);

        MoveAction moveAction = new MoveAction(player, resource, from, resultTo);

        // Act
        Tile actualTileFrom = moveAction.getTileFrom();

        // Assert
        assertSame(from, actualTileFrom);
        assertSame(resultTo, moveAction.getTileTo());
    }

    /**
     * Method under test:
     * {@link MoveAction#MoveAction(Player, Resource, Tile, Tile)}
     */
    @Test
    void testNewMoveAction() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        Tile from = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        Color tileColor = (new MoveAction(player, resource, from, new Tile(1, 1, TileType.Machete, 1, true))).getTileTo()
                .getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }
}
