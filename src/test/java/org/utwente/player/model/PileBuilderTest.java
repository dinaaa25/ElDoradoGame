package org.utwente.player.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;

class PileBuilderTest {
    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Kundeschafter));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard2() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Forscher));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard3() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Entdecker));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard4() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Pionier));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard5() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.MachtigeMachete));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard6() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Kapitan));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard7() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Matrose));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard8() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Reisende));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard9() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Fotografin));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard10() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Schatztruhe));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard11() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Millionarin));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard12() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Journalistin));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard13() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Tausendsassa));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard14() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Abenteurerin));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType)}
     */
    @Test
    void testAddCard15() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Propellerflugzeug));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType, int)}
     */
    @Test
    void testAddCard16() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Kundeschafter, 10));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType, int)}
     */
    @Test
    void testAddCard17() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Forscher, 10));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType, int)}
     */
    @Test
    void testAddCard18() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Entdecker, 10));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType, int)}
     */
    @Test
    void testAddCard19() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Pionier, 10));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType, int)}
     */
    @Test
    void testAddCard20() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.MachtigeMachete, 10));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType, int)}
     */
    @Test
    void testAddCard21() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Kapitan, 10));
    }

    /**
     * Method under test: {@link PileBuilder#addCard(CardType, int)}
     */
    @Test
    void testAddCard22() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addCard(CardType.Matrose, 10));
    }

    /**
     * Method under test: {@link PileBuilder#addStartingCards()}
     */
    @Test
    void testAddStartingCards() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.addStartingCards());
    }

    /**
     * Method under test: {@link PileBuilder#setPlayer(Player)}
     */
    @Test
    void testSetPlayer() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();
        Player player = new Player("Name");

        // Act
        PileBuilder actualSetPlayerResult = pileBuilder.setPlayer(player);

        // Assert
        assertSame(player, pileBuilder.getPlayer());
        CardPile playPile = actualSetPlayerResult.getPlayer().getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }

    /**
     * Method under test: {@link PileBuilder#reset()}
     */
    @Test
    void testReset() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act
        pileBuilder.reset();

        // Assert
        CardPile pile = pileBuilder.getPile();
        assertNull(pile.getPileType());
        assertTrue(pile.isEmpty());
    }

    /**
     * Method under test: {@link PileBuilder#setType(PileType)}
     */
    @Test
    void testSetType() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.setType(PileType.Discard));
    }

    /**
     * Method under test: {@link PileBuilder#setType(PileType)}
     */
    @Test
    void testSetType2() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.setType(PileType.Play));
    }

    /**
     * Method under test: {@link PileBuilder#setType(PileType)}
     */
    @Test
    void testSetType3() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.setType(PileType.OutOfGame));
    }

    /**
     * Method under test: {@link PileBuilder#setType(PileType)}
     */
    @Test
    void testSetType4() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.setType(PileType.Draw));
    }

    /**
     * Method under test: {@link PileBuilder#setType(PileType)}
     */
    @Test
    void testSetType5() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.setType(PileType.CaveCoin));
    }

    /**
     * Method under test: {@link PileBuilder#setType(PileType)}
     */
    @Test
    void testSetType6() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act and Assert
        assertSame(pileBuilder, pileBuilder.setType(PileType.FaceUpDiscard));
    }

    /**
     * Method under test: {@link PileBuilder#buildDiscardPile()}
     */
    @Test
    void testBuildDiscardPile() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act
        CardPile actualBuildDiscardPileResult = pileBuilder.buildDiscardPile();

        // Assert
        assertNull(actualBuildDiscardPileResult.getPlayer());
        assertEquals(PileType.Discard, actualBuildDiscardPileResult.getPileType());
        assertTrue(actualBuildDiscardPileResult.isEmpty());
        assertSame(actualBuildDiscardPileResult, pileBuilder.getPile());
    }

    /**
     * Method under test: {@link PileBuilder#buildOutOfGamePile()}
     */
    @Test
    void testBuildOutOfGamePile() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act
        CardPile actualBuildOutOfGamePileResult = pileBuilder.buildOutOfGamePile();

        // Assert
        assertNull(actualBuildOutOfGamePileResult.getPlayer());
        assertEquals(PileType.OutOfGame, actualBuildOutOfGamePileResult.getPileType());
        assertTrue(actualBuildOutOfGamePileResult.isEmpty());
        assertSame(actualBuildOutOfGamePileResult, pileBuilder.getPile());
    }

    /**
     * Method under test: {@link PileBuilder#buildOutOfGameCoinPile()}
     */
    @Test
    void testBuildOutOfGameCoinPile() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act
        CoinPile actualBuildOutOfGameCoinPileResult = pileBuilder.buildOutOfGameCoinPile();

        // Assert
        assertNull(actualBuildOutOfGameCoinPileResult.getPlayer());
        CardPile pile = pileBuilder.getPile();
        assertNull(pile.getPlayer());
        assertEquals(PileType.OutOfGame, pile.getPileType());
        assertTrue(actualBuildOutOfGameCoinPileResult.isEmpty());
        assertTrue(pile.isEmpty());
        assertSame(actualBuildOutOfGameCoinPileResult, pileBuilder.getCoinPile());
    }

    /**
     * Method under test: {@link PileBuilder#buildDrawPile()}
     */
    @Test
    void testBuildDrawPile() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act
        CardPile actualBuildDrawPileResult = pileBuilder.buildDrawPile();

        // Assert
        assertNull(actualBuildDrawPileResult.getPlayer());
        assertEquals(PileType.Draw, actualBuildDrawPileResult.getPileType());
        assertTrue(actualBuildDrawPileResult.isEmpty());
        assertSame(actualBuildDrawPileResult, pileBuilder.getPile());
    }

    /**
     * Method under test: {@link PileBuilder#buildStartPile()}
     */
    @Test
    void testBuildStartPile() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act
        CardPile actualBuildStartPileResult = pileBuilder.buildStartPile();

        // Assert
        assertNull(actualBuildStartPileResult.getPlayer());
        List<Card> cards = actualBuildStartPileResult.getCards();
        Card getResult = cards.get(0);
        assertEquals(0, getResult.getConsumedPower());
        Card getResult2 = cards.get(1);
        assertEquals(0, getResult2.getConsumedPower());
        Card getResult3 = cards.get(2);
        assertEquals(0, getResult3.getConsumedPower());
        Card getResult4 = cards.get(5);
        assertEquals(0, getResult4.getConsumedPower());
        Card getResult5 = cards.get(6);
        assertEquals(0, getResult5.getConsumedPower());
        Card getResult6 = cards.get(7);
        assertEquals(0, getResult6.getConsumedPower());
        assertEquals(1, getResult.getPower());
        assertEquals(1, getResult2.getPower());
        assertEquals(1, getResult3.getPower());
        assertEquals(1, getResult4.getPower());
        assertEquals(1, getResult5.getPower());
        assertEquals(1, getResult6.getPower());
        assertEquals(CardType.Forscher, getResult2.getCardType());
        assertEquals(CardType.Forscher, getResult3.getCardType());
        assertEquals(CardType.Matrose, getResult.getCardType());
        assertEquals(CardType.Reisende, getResult4.getCardType());
        assertEquals(CardType.Reisende, getResult5.getCardType());
        assertEquals(CardType.Reisende, getResult6.getCardType());
        assertFalse(actualBuildStartPileResult.isEmpty());
        assertSame(actualBuildStartPileResult, pileBuilder.getPile());
    }

    /**
     * Method under test: {@link PileBuilder#buildCaveCoinPile()}
     */
    @Test
    void testBuildCaveCoinPile() {
        // Arrange
        PileBuilder pileBuilder = new PileBuilder();

        // Act
        CoinPile actualBuildCaveCoinPileResult = pileBuilder.buildCaveCoinPile();

        // Assert
        assertNull(actualBuildCaveCoinPileResult.getPlayer());
        CardPile pile = pileBuilder.getPile();
        assertNull(pile.getPlayer());
        assertEquals(PileType.CaveCoin, pile.getPileType());
        assertFalse(actualBuildCaveCoinPileResult.isEmpty());
        assertTrue(pile.isEmpty());
        assertSame(actualBuildCaveCoinPileResult, pileBuilder.getCoinPile());
    }

    /**
     * Method under test: {@link PileBuilder#build()}
     */
    @Test
    void testBuild() {
        // Arrange and Act
        CardPile actualBuildResult = (new PileBuilder()).setType(PileType.Discard).build();

        // Assert
        assertNull(actualBuildResult.getPlayer());
        assertEquals(PileType.Discard, actualBuildResult.getPileType());
        List<Card> cards = actualBuildResult.getCards();
        assertTrue(cards.isEmpty());
        assertTrue(actualBuildResult.isEmpty());
        assertSame(cards, actualBuildResult.getResources());
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link PileBuilder}
     */
    @Test
    void testNewPileBuilder() {
        // Arrange, Act and Assert
        CardPile pile = (new PileBuilder()).getPile();
        assertNull(pile.getPileType());
        assertTrue(pile.isEmpty());
    }
}
