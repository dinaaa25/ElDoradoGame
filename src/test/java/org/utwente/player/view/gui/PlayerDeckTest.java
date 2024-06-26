package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;
import javax.swing.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.utwente.game.model.CartographerEffectPhase;
import org.utwente.game.model.EffectPhase;
import org.utwente.game.model.EffectPhaseEnum;
import org.utwente.game.model.EffectStep;
import org.utwente.game.model.Phase;
import org.utwente.game.model.ScientistEffectPhase;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.player.PlayerColor;
import org.utwente.player.model.CardPile;
import org.utwente.player.PlayerColor;
import org.utwente.player.model.CoinPile;
import org.utwente.player.model.PileType;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.EventType;

class PlayerDeckTest {
  /**
   * Method under test: {@link PlayerDeck#addEffectPhaseText(JPanel)}
   */
  @Test
  void testAddEffectPhaseText() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new CartographerEffectPhase(resource, new Player("EffectPhase: %s")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectPhaseText(new JPanel(true));

    // Assert
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectPhaseText(JPanel)}
   */
  @Test
  void testAddEffectPhaseText2() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    CartographerEffectPhase effectPhase = mock(CartographerEffectPhase.class);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(new HashMap<>());
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);

    Phase phase = new Phase();
    phase.setEffectPhase(effectPhase);
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectPhaseText(new JPanel(true));

    // Assert
    verify(effectPhase).allMandatoryStepsCompleted();
    verify(effectPhase, atLeast(1)).getEffectPhaseEnum();
    verify(effectPhase).getSteps();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectCardPhase(JPanel)}
   */
  @Test
  void testAddEffectCardPhase() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new CartographerEffectPhase(resource, new Player("Name")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectCardPhase(new JPanel(true));

    // Assert that nothing has changed
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectCardPhase(JPanel)}
   */
  @Test
  void testAddEffectCardPhase2() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new ScientistEffectPhase(resource, new Player("Done")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectCardPhase(new JPanel(true));

    // Assert that nothing has changed
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectCardPhase(JPanel)}
   */
  @Test
  void testAddEffectCardPhase3() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(new HashMap<>());
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);

    Phase phase = new Phase();
    phase.setEffectPhase(effectPhase);
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectCardPhase(new JPanel(true));

    // Assert that nothing has changed
    verify(effectPhase, atLeast(1)).allMandatoryStepsCompleted();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getSteps();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectCardPhase(JPanel)}
   */
  @Test
  void testAddEffectCardPhase4() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    HashMap<EventType, EffectStep> eventTypeEffectStepMap = new HashMap<>();
    eventTypeEffectStepMap.put(EventType.StartGame, new EffectStep(true, true, 1, "Done", "Done"));
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.getCurrentStep()).thenReturn(EventType.StartGame);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(eventTypeEffectStepMap);
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);

    Phase phase = new Phase();
    phase.setEffectPhase(effectPhase);
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectCardPhase(new JPanel(true));

    // Assert that nothing has changed
    verify(effectPhase, atLeast(1)).allMandatoryStepsCompleted();
    verify(effectPhase, atLeast(1)).getCurrentStep();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getSteps();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectPhaseDoneButton(JPanel)}
   */
  @Test
  void testAddEffectPhaseDoneButton() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new CartographerEffectPhase(resource, new Player("Done")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectPhaseDoneButton(new JPanel(true));

    // Assert that nothing has changed
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectPhaseDoneButton(JPanel)}
   */
  @Test
  void testAddEffectPhaseDoneButton2() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new ScientistEffectPhase(resource, new Player("Done")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectPhaseDoneButton(new JPanel(true));

    // Assert that nothing has changed
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectPhaseDoneButton(JPanel)}
   */
  @Test
  void testAddEffectPhaseDoneButton3() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    CartographerEffectPhase effectPhase = mock(CartographerEffectPhase.class);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(new HashMap<>());
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);

    Phase phase = new Phase();
    phase.setEffectPhase(effectPhase);
    PlayerDeck playerDeck = new PlayerDeck(player, phase);

    // Act
    playerDeck.addEffectPhaseDoneButton(new JPanel(true));

    // Assert that nothing has changed
    verify(effectPhase, atLeast(1)).allMandatoryStepsCompleted();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase).getSteps();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addDrawPile(Player)}
   */
  @Test
  void testAddDrawPile() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    when(player.getName()).thenReturn("Name");
    when(player.getColor()).thenReturn(PlayerColor.Red);
    PlayerDeck playerDeck = new PlayerDeck(player, new Phase());

    // Act
    playerDeck.addDrawPile(new Player("Name"));

    // Assert
    verify(player).getCaveCoinPile();
    verify(player).getColor();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getName();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addDrawPile(Player)}
   */
  @Test
  void testAddDrawPile2() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    when(player.getName()).thenReturn("Name");
    when(player.getColor()).thenReturn(PlayerColor.Red);
    PlayerDeck playerDeck = new PlayerDeck(player, new Phase());
    Player player2 = mock(Player.class);
    when(player2.getDrawPile()).thenReturn(new CardPile());

    // Act
    playerDeck.addDrawPile(player2);

    // Assert
    verify(player).getCaveCoinPile();
    verify(player).getColor();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player2).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getName();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addDiscardPanel(Player)}
   */
  @Test
  void testAddDiscardPanel() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    when(player.getName()).thenReturn("Name");
    when(player.getColor()).thenReturn(PlayerColor.Red);
    PlayerDeck playerDeck = new PlayerDeck(player, new Phase());

    // Act
    playerDeck.addDiscardPanel(new Player("Name"));

    // Assert
    verify(player).getCaveCoinPile();
    verify(player).getColor();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getName();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addDiscardPanel(Player)}
   */
  @Test
  void testAddDiscardPanel2() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    when(player.getName()).thenReturn("Name");
    when(player.getColor()).thenReturn(PlayerColor.Red);
    PlayerDeck playerDeck = new PlayerDeck(player, new Phase());
    Player player2 = mock(Player.class);
    when(player2.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player2.getDiscardPile()).thenReturn(new CardPile());

    // Act
    playerDeck.addDiscardPanel(player2);

    // Assert
    verify(player).getCaveCoinPile();
    verify(player).getColor();
    verify(player).getDiscardPile();
    verify(player2).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player2).getFaceUpDiscardPile();
    verify(player).getName();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addDiscardPanel(Player)}
   */
  @Test
  void testAddDiscardPanel3() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    when(player.getName()).thenReturn("Name");
    when(player.getColor()).thenReturn(PlayerColor.Red);
    PlayerDeck playerDeck = new PlayerDeck(player, new Phase());

    CardPile cardPile = new CardPile();
    cardPile.add(new Card(CardType.Kundeschafter));
    Player player2 = mock(Player.class);
    when(player2.getFaceUpDiscardPile()).thenReturn(cardPile);
    when(player2.getDiscardPile()).thenReturn(new CardPile());

    // Act
    playerDeck.addDiscardPanel(player2);

    // Assert
    verify(player).getCaveCoinPile();
    verify(player).getColor();
    verify(player).getDiscardPile();
    verify(player2).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player2).getFaceUpDiscardPile();
    verify(player).getName();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addDiscardPanel(Player)}
   */
  @Test
  void testAddDiscardPanel4() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    when(player.getName()).thenReturn("Name");
    when(player.getColor()).thenReturn(PlayerColor.Red);
    PlayerDeck playerDeck = new PlayerDeck(player, new Phase());

    CardPile cardPile = new CardPile();
    cardPile.add(new Card(CardType.Kundeschafter));
    Player player2 = mock(Player.class);
    when(player2.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player2.getDiscardPile()).thenReturn(cardPile);

    // Act
    playerDeck.addDiscardPanel(player2);

    // Assert
    verify(player).getCaveCoinPile();
    verify(player).getColor();
    verify(player).getDiscardPile();
    verify(player2).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player2).getFaceUpDiscardPile();
    verify(player).getName();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#addDiscardPanel(Player)}
   */
  @Test
  void testAddDiscardPanel5() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    when(player.getName()).thenReturn("Name");
    when(player.getColor()).thenReturn(PlayerColor.Red);
    PlayerDeck playerDeck = new PlayerDeck(player, new Phase());
    Card card = mock(Card.class);
    when(card.getValue()).thenReturn(10.0d);
    when(card.getConsumedPower()).thenReturn(1);
    when(card.remainingPower()).thenReturn(1);
    when(card.getCardType()).thenReturn(CardType.Kundeschafter);

    CardPile cardPile = new CardPile();
    cardPile.add(card);
    Player player2 = mock(Player.class);
    when(player2.getFaceUpDiscardPile()).thenReturn(cardPile);
    when(player2.getDiscardPile()).thenReturn(new CardPile());

    // Act
    playerDeck.addDiscardPanel(player2);

    // Assert
    verify(card, atLeast(1)).getCardType();
//    verify(card).getConsumedPower();
    verify(card).getValue();
    verify(card).remainingPower();
    verify(player).getCaveCoinPile();
    verify(player).getColor();
    verify(player).getDiscardPile();
    verify(player2).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player2).getFaceUpDiscardPile();
    verify(player).getName();
    verify(player).getPlayPile();
  }

  /**
   * Method under test: {@link PlayerDeck#PlayerDeck(Player, Phase)}
   */
  @Test
  void testNewPlayerDeck() {
    // Arrange
    Player player = new Player("Name");
    player.setColor(PlayerColor.Red);

    // Act and Assert
    Locale locale = (new PlayerDeck(player, new Phase())).getLocale();
    Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
    assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
  }

  /**
   * Method under test: {@link PlayerDeck#PlayerDeck(Player, Phase)}
   */
  @Test
  void testNewPlayerDeck2() {
    // Arrange
    Player player = new Player("Name");

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new CartographerEffectPhase(resource, new Player("Make Move")));

    // Act and Assert
    Locale locale = (new PlayerDeck(player, phase)).getLocale();
    Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
    assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
  }

  /**
   * Method under test: {@link PlayerDeck#PlayerDeck(Player, Phase)}
   */
  @Test
  void testNewPlayerDeck3() {
    // Arrange
    Player player = new Player("Name");
    player.setColor(PlayerColor.Red);

    Phase phase = new Phase();
    phase.setActionMessage(new ValidationResult(true, "Not all who wander are lost"));

    // Act and Assert
    Locale locale = (new PlayerDeck(player, phase)).getLocale();
    Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
    assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
  }

  /**
   * Method under test: {@link PlayerDeck#PlayerDeck(Player, Phase)}
   */
  @Test
  void testNewPlayerDeck4() {
    // Arrange
    Player player = new Player("Name");

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new ScientistEffectPhase(resource, new Player("Done")));

    // Act and Assert
    Locale locale = (new PlayerDeck(player, phase)).getLocale();
    Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
    assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
  }

  /**
   * Method under test: {@link PlayerDeck#setNotification(ValidationResult)}
   */
  void testSetNotification() {
    // Arrange
    Player player = new Player("Name");
    player.setColor(PlayerColor.Red); // Ensure that the PlayerColor is set
    Phase phase = new Phase(); // Assuming a default constructor exists
    PlayerDeck playerDeck = new PlayerDeck(player, phase);
    ValidationResult notification = new ValidationResult(true, "Test notification");

    // Act
    playerDeck.setNotification(notification);

    // Assert
    // Check if the notification message is set correctly in the UI component
    boolean found = false;
    for (java.awt.Component component : playerDeck.getComponents()) {
      if (component instanceof JLabel) {
        JLabel label = (JLabel) component;
        if (label.getText().equals("Test notification")) {
          found = true;
          break;
        }
      }
    }
    assertTrue(found, "Notification message should be set in the PlayerDeck component.");
  }

  @Test
  void testAddPlayerRow() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new CartographerEffectPhase(resource, new Player("Name")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);
    Player player2 = new Player("Name");

    // Act
    playerDeck.addPlayerRow(player2);

    // Assert that nothing has changed
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals("Name", player2.getName());
    assertEquals(0, player2.getBlockadeCount());
    assertEquals(0, playerDeck.col);
    assertEquals(PileType.Discard, player2.getDiscardPile().getPileType());
    assertEquals(PileType.FaceUpDiscard, player2.getFaceUpDiscardPile().getPileType());
    assertEquals(PileType.OutOfGame, player2.getOutOfGamePile().getPileType());
    assertEquals(PileType.Play, player2.getPlayPile().getPileType());
  }

  /**
   * Method under test: {@link PlayerDeck#addPlayerRow(Player)}
   */
  @Test
  void testAddPlayerRow2() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    Card resource = new Card(CardType.Kundeschafter);
    when(phase.getEffectPhase()).thenReturn(new ScientistEffectPhase(resource, new Player("Done")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);
    Player player2 = new Player("Name");

    // Act
    playerDeck.addPlayerRow(player2);

    // Assert that nothing has changed
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals("Name", player2.getName());
    assertEquals(0, player2.getBlockadeCount());
    assertEquals(0, playerDeck.col);
    assertEquals(PileType.Discard, player2.getDiscardPile().getPileType());
    assertEquals(PileType.FaceUpDiscard, player2.getFaceUpDiscardPile().getPileType());
    assertEquals(PileType.OutOfGame, player2.getOutOfGamePile().getPileType());
    assertEquals(PileType.Play, player2.getPlayPile().getPileType());
  }

  /**
   * Method under test: {@link PlayerDeck#addPlayerRow(Player)}
   */
  @Test
  void testAddPlayerRow3() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(new HashMap<>());
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getEffectPhase()).thenReturn(effectPhase);
    PlayerDeck playerDeck = new PlayerDeck(player, phase);
    Player player2 = new Player("Name");

    // Act
    playerDeck.addPlayerRow(player2);

    // Assert that nothing has changed
    verify(effectPhase, atLeast(1)).allMandatoryStepsCompleted();
    verify(effectPhase, atLeast(1)).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getSteps();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals("Name", player2.getName());
    assertEquals(0, player2.getBlockadeCount());
    assertEquals(0, playerDeck.col);
    assertEquals(PileType.Discard, player2.getDiscardPile().getPileType());
    assertEquals(PileType.FaceUpDiscard, player2.getFaceUpDiscardPile().getPileType());
    assertEquals(PileType.OutOfGame, player2.getOutOfGamePile().getPileType());
    assertEquals(PileType.Play, player2.getPlayPile().getPileType());
  }

  /**
   * Method under test: {@link PlayerDeck#addPlayerRow(Player)}
   */
  @Test
  void testAddPlayerRow4() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    HashMap<EventType, EffectStep> eventTypeEffectStepMap = new HashMap<>();
    eventTypeEffectStepMap.put(EventType.StartGame, new EffectStep(true, true, 1, "Done", "Done"));
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.getCurrentStep()).thenReturn(EventType.StartGame);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(eventTypeEffectStepMap);
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);
    Phase phase = mock(Phase.class);
    when(phase.getActionMessage()).thenReturn(new ValidationResult(true, "Not all who wander are lost"));
    when(phase.getEffectPhase()).thenReturn(effectPhase);
    PlayerDeck playerDeck = new PlayerDeck(player, phase);
    Player player2 = new Player("Name");

    // Act
    playerDeck.addPlayerRow(player2);

    // Assert that nothing has changed
    verify(effectPhase, atLeast(1)).allMandatoryStepsCompleted();
    verify(effectPhase, atLeast(1)).getCurrentStep();
    verify(effectPhase, atLeast(1)).getEffectPhaseEnum();
    verify(effectPhase, atLeast(1)).getSteps();
    verify(phase, atLeast(1)).getActionMessage();
    verify(phase, atLeast(1)).getEffectPhase();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals("Name", player2.getName());
    assertEquals(0, player2.getBlockadeCount());
    assertEquals(0, playerDeck.col);
    assertEquals(PileType.Discard, player2.getDiscardPile().getPileType());
    assertEquals(PileType.FaceUpDiscard, player2.getFaceUpDiscardPile().getPileType());
    assertEquals(PileType.OutOfGame, player2.getOutOfGamePile().getPileType());
    assertEquals(PileType.Play, player2.getPlayPile().getPileType());
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectRow(JPanel, Map.Entry)}
   */
  @Test
  void testAddEffectRow() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());

    Phase phase = new Phase();
    Card resource = new Card(CardType.Kundeschafter);
    phase.setEffectPhase(new CartographerEffectPhase(resource, new Player("Name")));
    PlayerDeck playerDeck = new PlayerDeck(player, phase);
    JPanel cardPanel = new JPanel(true);

    // Act
    playerDeck.addEffectRow(cardPanel,
            new AbstractMap.SimpleEntry<>(EventType.StartGame, new EffectStep(true, true, 1, "Button Text", "Step Label")));

    // Assert that nothing has changed
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }

  /**
   * Method under test: {@link PlayerDeck#addEffectRow(JPanel, Map.Entry)}
   */
  @Test
  void testAddEffectRow2() {
    // Arrange
    Player player = mock(Player.class);
    when(player.getFaceUpDiscardPile()).thenReturn(new CardPile());
    when(player.getDiscardPile()).thenReturn(new CardPile());
    when(player.getDrawPile()).thenReturn(new CardPile());
    when(player.getCaveCoinPile()).thenReturn(new CoinPile());
    when(player.getPlayPile()).thenReturn(new CardPile());
    EffectPhase effectPhase = mock(EffectPhase.class);
    when(effectPhase.allMandatoryStepsCompleted()).thenReturn(true);
    when(effectPhase.getSteps()).thenReturn(new HashMap<>());
    when(effectPhase.getEffectPhaseEnum()).thenReturn(EffectPhaseEnum.Scientist);
    when(effectPhase.getCurrentStep()).thenReturn(EventType.StartGame);

    Phase phase = new Phase();
    phase.setEffectPhase(effectPhase);
    PlayerDeck playerDeck = new PlayerDeck(player, phase);
    JPanel cardPanel = new JPanel(true);

    // Act
    playerDeck.addEffectRow(cardPanel,
            new AbstractMap.SimpleEntry<>(EventType.StartGame, new EffectStep(true, true, 1, "Button Text", "Step Label")));

    // Assert that nothing has changed
    verify(effectPhase).allMandatoryStepsCompleted();
    verify(effectPhase).getCurrentStep();
    verify(effectPhase).getEffectPhaseEnum();
    verify(effectPhase).getSteps();
    verify(player).getCaveCoinPile();
    verify(player).getDiscardPile();
    verify(player).getDrawPile();
    verify(player).getFaceUpDiscardPile();
    verify(player).getPlayPile();
    assertEquals(0, playerDeck.col);
  }
}
