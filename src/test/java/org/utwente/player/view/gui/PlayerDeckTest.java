package org.utwente.player.view.gui;

import java.awt.*;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import javax.swing.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.utwente.game.model.PhaseType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Pile;
import org.utwente.player.model.Player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class PlayerDeckTest {
    /**
     * Method under test: {@link PlayerDeck#addDrawPile()}
     */
    @Test
    void testAddDrawPile() {
        // Arrange and Act
        (new PlayerDeck(new Player("Name"), PhaseType.BUYING_AND_PLAYING_PHASE)).addDrawPile();
    }

    /**
     * Method under test: {@link PlayerDeck#addDrawPile()}
     */
    @Test
    void testAddDrawPile2() {
        // Arrange
        PlayerDeck playerDeck = new PlayerDeck(new Player("Name"), PhaseType.BUYING_AND_PLAYING_PHASE);
        playerDeck.addVetoableChangeListener(mock(VetoableChangeListener.class));

        // Act
        playerDeck.addDrawPile();
    }

    /**
     * Method under test: {@link PlayerDeck#addDiscardPile()}
     */
    @Test
    void testAddDiscardPile() {
        // Arrange and Act
        (new PlayerDeck(new Player("Name"), PhaseType.BUYING_AND_PLAYING_PHASE)).addDiscardPile();
    }

    /**
     * Method under test: {@link PlayerDeck#addDiscardPile()}
     */
    @Test
    void testAddDiscardPile2() {
        // Arrange
        PlayerDeck playerDeck = new PlayerDeck(new Player("Name"), PhaseType.BUYING_AND_PLAYING_PHASE);
        playerDeck.addVetoableChangeListener(mock(VetoableChangeListener.class));

        // Act
        playerDeck.addDiscardPile();
    }

    /**
     * Method under test: {@link PlayerDeck#PlayerDeck(Player, PhaseType)}
     */
    @Test
    void testNewPlayerDeck() {
        // Arrange, Act and Assert
        Locale locale = (new PlayerDeck(new Player("Name"), PhaseType.BUYING_AND_PLAYING_PHASE)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link PlayerDeck#PlayerDeck(Player, PhaseType)}
     */
    @Test
    void testNewPlayerDeck2() {
        // Arrange
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));

        Pile caveCoinPile = new Pile();
        caveCoinPile.setResources(resources);

        ArrayList<Resource> resources2 = new ArrayList<>();
        resources2.add(new Card(CardType.Kundeschafter));

        Pile playPile = new Pile();
        playPile.setResources(resources2);

        Player player = new Player("Name");
        player.setCaveCoinPile(caveCoinPile);
        player.setPlayPile(playPile);

        // Act and Assert
        Locale locale = (new PlayerDeck(player, PhaseType.BUYING_AND_PLAYING_PHASE)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link PlayerDeck#PlayerDeck(Player, PhaseType)}
     */
    @Test
    void testNewPlayerDeck3() {
        // Arrange, Act and Assert
        Locale locale = (new PlayerDeck(new Player("Name"), PhaseType.DISCARD_PHASE)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link PlayerDeck#PlayerDeck(Player, PhaseType)}
     */
    @Test
    void testNewPlayerDeck4() {
        // Arrange, Act and Assert
        Locale locale = (new PlayerDeck(new Player("Name"), PhaseType.DRAW_PHASE)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link PlayerDeck#PlayerDeck(Player, PhaseType)}
     */
    @Test
    void testNewPlayerDeck5() {
        // Arrange
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));

        Pile caveCoinPile = new Pile();
        caveCoinPile.setResources(resources);

        ArrayList<Resource> resources2 = new ArrayList<>();
        resources2.add(new Card(CardType.Forscher));
        resources2.add(new Card(CardType.Kundeschafter));

        Pile playPile = new Pile();
        playPile.setResources(resources2);

        Player player = new Player("Name");
        player.setCaveCoinPile(caveCoinPile);
        player.setPlayPile(playPile);

        // Act and Assert
        Locale locale = (new PlayerDeck(player, PhaseType.BUYING_AND_PLAYING_PHASE)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }

    /**
     * Method under test: {@link PlayerDeck#PlayerDeck(Player, PhaseType)}
     */
    @Test
    void testNewPlayerDeck6() {
        // Arrange
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));

        Pile caveCoinPile = new Pile();
        caveCoinPile.setResources(resources);

        ArrayList<Resource> resources2 = new ArrayList<>();
        resources2.add(null);

        Pile playPile = new Pile();
        playPile.setResources(resources2);

        Player player = new Player("Name");
        player.setCaveCoinPile(caveCoinPile);
        player.setPlayPile(playPile);

        // Act and Assert
        Locale locale = (new PlayerDeck(player, PhaseType.BUYING_AND_PLAYING_PHASE)).getLocale();
        Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
        assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
    }
}