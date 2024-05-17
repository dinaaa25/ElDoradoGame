package org.utwente.market.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class Market {
    private int CURRENT_FULL_SIZE = 6;
    private Map<CardType, Integer> currentCards;
    private Map<CardType, Integer> reserveCards;

    public Market(List<CardTypeSpec> current, List<CardTypeSpec> reserve) {
        this.currentCards = new HashMap<>();
        this.reserveCards = new HashMap<>();

        for (CardTypeSpec currentCardTypeSpec : current) {
            this.currentCards.put(currentCardTypeSpec.getType(), currentCardTypeSpec.getQuantity());
        }

        for (CardTypeSpec reserveCardTypeSpec : reserve) {
            this.reserveCards.put(reserveCardTypeSpec.getType(), reserveCardTypeSpec.getQuantity());
        }
    }

    public Market() {
        this(MarketSetup.active.cardSpecification, MarketSetup.reserve.cardSpecification);
    }

    public boolean canBuy(Order order) {
        return ((reserveIsOpen() && reserveCards.containsKey(order.getCardToken()))
                || currentCards.containsKey(order.getCardToken()))
                && order.getMoney() >= order.getCardToken().purchaseValue;
    }

    public boolean reserveIsOpen() {
        return this.CURRENT_FULL_SIZE > this.currentCards.size();
    }

    public Card buy(Order order) {
        if (order != null && canBuy(order)) {
            removeCardIfExists(order.getCardToken());
            return new Card(order.getCardToken());
        }

        return null;
    }

    private void removeCardIfExists(CardType key) {
        Integer res = this.currentCards.get(key);
        this.currentCards.replace(key, --res);

        // remove card type that is empty.
        if (res <= 0) {
            this.currentCards.remove(key);
        }
    }

    public int getRemainingCardAmount() {
        int result = 0;
        for (int cardQ : currentCards.values()) {
            result += cardQ;
        }
        for (int cardQ : reserveCards.values()) {
            result += cardQ;
        }
        return result;
    }
}
