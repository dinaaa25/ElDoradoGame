package org.utwente.market.model;

import lombok.*;

@Getter
@Setter
public class Card implements Resource {
    private CardType cardType;
    private int consumedPower;

    public Card(CardType cardType) {
        this.cardType = cardType;
        this.consumedPower = 0;
    }

    @Override
    public PowerType getType() {
        return this.cardType.powerType;
    }

    /**
     * computes power without already utilized power from card.
     * 
     * @return power of the card as a number.
     */
    public int remainingPower() {
        return cardType.power - consumedPower;
    }

    public void removePower(int power) throws CardPowerException {
        if (power > remainingPower()) {
            throw new CardPowerException("Not enough power to remove.");
        }
        this.consumedPower += power;
    }

    @Override
    public int getPower() {
        return remainingPower();
    }
}
