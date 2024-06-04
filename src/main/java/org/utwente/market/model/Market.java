package org.utwente.market.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.utwente.market.exceptions.BuyException;

import java.util.ArrayList;
import lombok.Getter;

@Getter
public class Market {
    private static final int CURRENT_FULL_SIZE = 6;
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

    public ValidationResult canBuy(Order order) {
        if (!cardInReserve(order.getCardToken()) && !cardInCurrent(order.getCardToken())) {
            return new ValidationResult(false, "Card is not part of the market.");
        }
        if (!reserveIsOpen() && cardInReserve(order.getCardToken())) {
            return new ValidationResult(false, "Card is in reserve and reserve is closed.");
        }
        if (order.getMoney() < order.getCardToken().purchaseValue) {
            return new ValidationResult(false, "Cannot buy card with the given coins");
        }

        return new ValidationResult(true, null);
    }

    public boolean reserveIsOpen() {
        return Market.CURRENT_FULL_SIZE > this.currentCards.size();
    }

    public record ValidationResult(boolean status, String message) {

    }

    public Card buy(Order order) throws BuyException {
        if (order == null) {
            throw new BuyException("Order is empty");
        }
        ValidationResult validationResult = canBuy(order);

        if (validationResult.status == false) {
            throw new BuyException(validationResult.message);
        }

        removeCardFromMarket(order.getCardToken());
        return new Card(order.getCardToken());
    }

    public boolean cardInReserve(CardType type) {
        return this.reserveCards.containsKey(type);
    }

    public boolean cardInCurrent(CardType type) {
        return this.currentCards.containsKey(type);
    }

    public void removeCardFromMarket(CardType key) {
        if (cardInReserve(key)) {
            // move card to current cards.
            Integer res = this.reserveCards.get(key);
            this.currentCards.put(key, --res);
            this.reserveCards.remove(key);
        } else if (cardInCurrent(key)) {
            Integer res = this.currentCards.get(key);
            // card is given to the player
            this.currentCards.replace(key, --res);
            // remove card type that is empty.
            if (res <= 0) {
                this.currentCards.remove(key);
            }
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

    public List<CardType> getCurrent() {
        return new ArrayList<>(this.currentCards.keySet());
    }

    public List<CardType> getReserve() {
        return new ArrayList<>(this.reserveCards.keySet());
    }
}
