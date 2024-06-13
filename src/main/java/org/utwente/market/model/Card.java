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
    public double getValue() {
        if(this.cardType.powerType == PowerType.Coin) {
            return this.getPower();
        }
        return 0.5;
    }

    @Override
    public int getPower() {
        return remainingPower();
    }

    public CardType getCardType() {
        return this.cardType;
    }

    @Override
    public String toString() {
        return String.format("Card[cardType=%s, consumedPower=%d]", this.cardType, this.consumedPower);
    }
}
