package org.utwente.market;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.utwente.market.model.*;

public class CardTypeTest {

    @Test
    public void testCardTypeAttributes() {
        // Test each CardType with expected values I found on the scanned cards on
        // https://drive.google.com/drive/folders/1XUhFt-iu4-_TY6tXFjhBaUG60NhlDX8J?usp=drive_link
        assertCardType(CardType.Kundeschafter, 2, 1, PowerType.Machete, false);
        assertCardType(CardType.Forscher, 1, null, PowerType.Machete, false);
        assertCardType(CardType.Entdecker, 3, 3, PowerType.Machete, false);
        assertCardType(CardType.Pionier, 5, 5, PowerType.Machete, false);
        assertCardType(CardType.MachtigeMachete, 6, 3, PowerType.Machete, true);
        assertCardType(CardType.Kapitan, 3, 2, PowerType.Paddle, false);
        assertCardType(CardType.Matrose, 1, null, PowerType.Paddle, false);
        assertCardType(CardType.Reisende, 1, null, PowerType.Coin, false);
        assertCardType(CardType.Fotografin, 2, 2, PowerType.Coin, false);
        assertCardType(CardType.Schatztruhe, 4, 3, PowerType.Coin, true);
        assertCardType(CardType.Millionarin, 4, 5, PowerType.Coin, false);
        assertCardType(CardType.Journalistin, 3, 3, PowerType.Coin, false);
        assertCardType(CardType.Tausendsassa, 1, 2, PowerType.Wild, false);
        assertCardType(CardType.Abenteurerin, 2, 4, PowerType.Wild, false);
        assertCardType(CardType.Propellerflugzeug, 4, 4, PowerType.Wild, true);
        assertCardType(CardType.Kartograph, 2, 4, PowerType.Effect, false);
        assertCardType(CardType.Kompass, 3, 2, PowerType.Effect, true);
        assertCardType(CardType.Wissenschaftlerin, 1, 4, PowerType.Effect, false);
        assertCardType(CardType.Ureinwohner, 1, 5, PowerType.Effect, false);
        assertCardType(CardType.Fernsprechgerat, 1, 4, PowerType.Effect, true);
        assertCardType(CardType.Reisetagebuch, 2, 3, PowerType.Effect, true);
        assertCardType(CardType.Kundeschafter, 2, 1, PowerType.Machete, false);
        assertCardType(CardType.Forscher, 1, null, PowerType.Machete, false);
        assertCardType(CardType.Entdecker, 3, 3, PowerType.Machete, false);
        assertCardType(CardType.Pionier, 5, 5, PowerType.Machete, false);
    }

    private void assertCardType(CardType cardType, Integer expectedPower, Integer expectedPurchaseValue,
            PowerType expectedPowerType, boolean expectedOneTimeUse) {
        assertEquals(expectedPower, cardType.power, "Power should match for " + cardType);
        assertEquals(expectedPurchaseValue, cardType.purchaseValue, "Purchase value should match for " + cardType);
        assertEquals(expectedPowerType, cardType.powerType, "PowerType should match for " + cardType);
        assertEquals(expectedOneTimeUse, cardType.oneTimeUse, "OneTimeUse should match for " + cardType);
        assertEquals(expectedOneTimeUse, cardType.oneTimeUse, "OneTimeUse should match for " + cardType);
    }
}
