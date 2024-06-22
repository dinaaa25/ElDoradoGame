package org.utwente.market.model;

import lombok.*;

@Getter
@Setter
public class Card implements Resource {
    private CardType cardType;
    private int consumedPower;
    private boolean selected = false;

    public Card(CardType cardType) {
        this.cardType = cardType;
        this.consumedPower = 0;
    }

    public void switchSelected() {
        this.selected = !this.selected;
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
                ", selected=" + selected +
                '}';
    }
}
