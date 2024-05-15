package org.utwente.market.model;

import lombok.*;

@Getter
@Setter
public class Card implements Resource {
    private CardType cardType;
    private int consumedPower = 0;

    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * computes power without already utilized power from card.
     * 
     * @return power of the card as a number.
     */
    public int remainingPower() {
        return 0;
    }
}
