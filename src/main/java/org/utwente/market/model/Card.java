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
    @Override
    public int remainingPower() {
        return cardType.power - consumedPower;
    }

    public void removePower(int toBeRemoved) throws CardPowerException {
        if (toBeRemoved > remainingPower()) {
            throw new CardPowerException("Not enough power to remove.");
        }
        this.consumedPower += toBeRemoved;
    }

    @Override
    public double getValue() {
        if (this.cardType.powerType == PowerType.Coin) {
            return this.getPower();
        }
        return 0.5;
    }

    @Override
    public int getPower() {
        return remainingPower();
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardType=" + cardType +
                ", consumedPower=" + consumedPower +
                '}';
    }
}
