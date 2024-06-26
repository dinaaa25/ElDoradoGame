package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

class BuyActionTest {
    /**
     * Method under test: {@link BuyAction#execute()}
     */
    @Test
    void testExecute() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();
        BuyAction buyAction = new BuyAction(player, resources, CardType.Kundeschafter, new Market());

        // Act
        buyAction.execute();

        // Assert
        CardPile playPile = buyAction.player.getPlayPile();
        Card getResult = playPile.getResources().get(4);
        assertEquals(0, getResult.getConsumedPower());
        assertEquals(2, getResult.getPower());
        assertEquals(CardType.Kundeschafter, getResult.getCardType());
        assertFalse(playPile.isEmpty());
    }

    /**
     * Method under test: {@link BuyAction#execute()}
     */
    @Test
    void testExecute2() {
        // Arrange
        Player player = new Player("src");
        ArrayList<Resource> resources = new ArrayList<>();
        BuyAction buyAction = new BuyAction(player, resources, CardType.Kundeschafter, new Market());

        // Act
        buyAction.execute();

        // Assert
        CardPile playPile = buyAction.player.getPlayPile();
        Card getResult = playPile.getResources().get(4);
        assertEquals(0, getResult.getConsumedPower());
        assertEquals(2, getResult.getPower());
        assertEquals(CardType.Kundeschafter, getResult.getCardType());
        assertFalse(playPile.isEmpty());
    }

    /**
     * Method under test: {@link BuyAction#execute()}
     */
    @Test
    void testExecute3() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();
        BuyAction buyAction = new BuyAction(player, resources, CardType.Forscher, new Market());

        // Act
        buyAction.execute();

        // Assert
        CardPile playPile = buyAction.player.getPlayPile();
        Card getResult = playPile.getResources().get(4);
        assertEquals(0, getResult.getConsumedPower());
        assertEquals(1, getResult.getPower());
        assertEquals(CardType.Forscher, getResult.getCardType());
        assertFalse(playPile.isEmpty());
    }

    /**
     * Method under test: {@link BuyAction#execute()}
     */
    @Test
    void testExecute4() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();
        BuyAction buyAction = new BuyAction(player, resources, CardType.Pionier, new Market());

        // Act
        buyAction.execute();

        // Assert
        CardPile playPile = buyAction.player.getPlayPile();
        Card getResult = playPile.getResources().get(4);
        assertEquals(0, getResult.getConsumedPower());
        assertEquals(5, getResult.getPower());
        assertEquals(CardType.Pionier, getResult.getCardType());
        assertFalse(playPile.isEmpty());
    }

    /**
     * Method under test: {@link BuyAction#validate()}
     */
    @Test
    void testValidate() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act
        ValidationResult actualValidateResult = (new BuyAction(player, resources, CardType.Kundeschafter, new Market()))
                .validate();

        // Assert
        assertEquals("using free market configuration 💵", actualValidateResult.getMessage());
        assertTrue(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link BuyAction#discard()}
     */
    @Test
    void testDiscard() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();
        BuyAction buyAction = new BuyAction(player, resources, CardType.Kundeschafter, new Market());

        // Act
        buyAction.discard();

        // Assert that nothing has changed
        assertEquals(0, buyAction.player.getBlockadeCount());
    }

    /**
     * Method under test: {@link BuyAction#discard()}
     */
    @Test
    void testDiscard2() {
        // Arrange
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Player player = new Player("Name");
        BuyAction buyAction = new BuyAction(player, resources, CardType.Kundeschafter, new Market());

        // Act
        buyAction.discard();

        // Assert
        Player player2 = buyAction.player;
        assertFalse(player2.getFaceUpDiscardPile().isEmpty());
        assertFalse(player2.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link BuyAction#discard()}
     */
    @Test
    void testDiscard3() {
        // Arrange
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new CaveCoin(1, CaveCoinType.Machete));
        Player player = new Player("Name");
        BuyAction buyAction = new BuyAction(player, resources, CardType.Kundeschafter, new Market());

        // Act
        buyAction.discard();

        // Assert
        assertFalse(buyAction.player.getCaveCoinPile().isEmpty());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Kundeschafter, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction2() {
        // Arrange
        Player player = new Player("Name");

        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Kundeschafter, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction3() {
        // Arrange
        Player player = new Player("Name");

        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        resources.add(new Card(CardType.Kundeschafter));

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Kundeschafter, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction4() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Forscher, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction5() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Entdecker, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction6() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Pionier, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction7() {
        // Arrange
        Player player = new Player("Name");

        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new CaveCoin(2, CaveCoinType.Machete));

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Kundeschafter, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction8() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.MachtigeMachete, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction9() {
        // Arrange
        Player player = new Player("Name");

        Card card = new Card(CardType.Kundeschafter);
        card.setConsumedPower(2);

        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(card);

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Kundeschafter, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction10() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Kapitan, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction11() {
        // Arrange
        Player player = new Player("Name");

        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Reisende));

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Kundeschafter, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction12() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Matrose, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test:
     * {@link BuyAction#BuyAction(Player, List, CardType, Market)}
     */
    @Test
    void testNewBuyAction13() {
        // Arrange
        Player player = new Player("Name");
        ArrayList<Resource> resources = new ArrayList<>();

        // Act and Assert
        CardPile playPile = (new BuyAction(player, resources, CardType.Reisende, new Market())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }
}
