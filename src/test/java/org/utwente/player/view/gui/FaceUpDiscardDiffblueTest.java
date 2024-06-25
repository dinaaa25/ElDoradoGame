package org.utwente.player.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.utwente.player.model.CardPile;

class FaceUpDiscardDiffblueTest {
  /**
   * Method under test: {@link FaceUpDiscard#FaceUpDiscard(CardPile)}
   */
  @Test
  void testNewFaceUpDiscard() {
    // Arrange, Act and Assert
    Locale locale = (new FaceUpDiscard(new CardPile())).getLocale();
    Set<String> expectedUnicodeLocaleAttributes = locale.getUnicodeLocaleKeys();
    assertSame(expectedUnicodeLocaleAttributes, locale.getUnicodeLocaleAttributes());
  }
}
